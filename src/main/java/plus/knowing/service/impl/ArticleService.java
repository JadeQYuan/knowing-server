package plus.knowing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import plus.knowing.dao.BlogArticleDao;
import plus.knowing.dao.BlogArticleTagsDao;
import plus.knowing.entity.BlogArticle;
import plus.knowing.entity.BlogArticleTags;
import plus.knowing.service.IArticleService;
import plus.knowing.service.ISpecialService;
import plus.knowing.service.ITagService;
import plus.knowing.vo.blog.ArticleQueryVO;
import plus.knowing.vo.blog.ArticleVO;
import plus.knowing.vo.blog.SpecialVO;
import plus.knowing.vo.generic.PageQueryVO;
import plus.knowing.vo.generic.PageVO;
import plus.knowing.vo.blog.TagVO;
import plus.knowing.vo.sys.UserVO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleService implements IArticleService {

    @Autowired
    private BlogArticleDao blogArticleDao;

    @Autowired
    private BlogArticleTagsDao blogArticleTagsDao;

    @Autowired
    private ITagService iTagService;

    @Autowired
    private ISpecialService iSpecialService;

    @Override
    @Transactional
    public void addArticle(ArticleVO articleVO, UserVO userVO) {
        BlogArticle article = new BlogArticle(articleVO);
        article.setCreateTime(LocalDateTime.now());
        article.setCreateUserId(userVO.getId());
        blogArticleDao.insert(article);
        articleVO.getTags().forEach(tagVO -> {
            BlogArticleTags articleTags = new BlogArticleTags();
            articleTags.setArticleId(article.getId());
            articleTags.setTagId(tagVO.getId());
            blogArticleTagsDao.insert(articleTags);
        });
    }

    @Override
    public PageVO<ArticleVO> listAllArticles(ArticleQueryVO queryVO) {
        QueryWrapper<BlogArticle> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.orderByDesc(" create_time ");
        IPage<BlogArticle> page = blogArticleDao.selectPage(new Page<>(queryVO.getPageNum(), queryVO.getPageSize()), articleQueryWrapper);
        List<ArticleVO> voList = page.getRecords().stream().map(ArticleVO::new).collect(Collectors.toList());
        voList.forEach(vo -> {
            List<BlogArticleTags> articleTagsList = blogArticleTagsDao.selectList(new QueryWrapper<BlogArticleTags>().eq("article_id", vo.getId()));
            List<TagVO> tags = articleTagsList.stream().map(articleTags -> iTagService.get(articleTags.getTagId())).collect(Collectors.toList());
            vo.setTags(tags);
            if (Objects.nonNull(vo.getSpecialId())) {
                SpecialVO specialVO = iSpecialService.get(vo.getSpecialId());
                vo.setSpecialName(specialVO.getName());
            }
        });
        return new PageVO<>(page, voList);
    }

    @Override
    public PageVO<ArticleVO> pagingNewestArticles(PageQueryVO queryVO) {
        QueryWrapper<BlogArticle> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.orderByDesc(" create_time ");
        IPage<BlogArticle> page = blogArticleDao.selectPage(new Page<>(queryVO.getPageNum(), queryVO.getPageSize()), articleQueryWrapper);
        List<ArticleVO> voList = page.getRecords().stream().map(ArticleVO::new).collect(Collectors.toList());
        voList.forEach(vo -> {
            List<BlogArticleTags> articleTagsList = blogArticleTagsDao.selectList(new QueryWrapper<BlogArticleTags>().eq("article_id", vo.getId()));
            List<TagVO> tags = articleTagsList.stream().map(articleTags -> iTagService.get(articleTags.getTagId())).collect(Collectors.toList());
            vo.setTags(tags);
            if (Objects.nonNull(vo.getSpecialId())) {
                SpecialVO specialVO = iSpecialService.get(vo.getSpecialId());
                vo.setSpecialName(specialVO.getName());
            }
        });
        return new PageVO<>(page, voList);
    }

    @Override
    public PageVO<ArticleVO> pagingListMyArticles(ArticleQueryVO queryVO, UserVO userVO) {
        QueryWrapper<BlogArticle> articleQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(queryVO.getTitle())) {
            articleQueryWrapper.like("title", queryVO.getTitle());
        }
        articleQueryWrapper.eq("create_user_id", userVO.getId());
        articleQueryWrapper.orderByDesc(" create_time ");
        IPage<BlogArticle> page = blogArticleDao.selectPage(new Page<>(queryVO.getPageNum(), queryVO.getPageSize()), articleQueryWrapper);
        List<ArticleVO> voList = page.getRecords().stream().map(ArticleVO::new).collect(Collectors.toList());
        voList.forEach(vo -> {
            List<BlogArticleTags> articleTagsList = blogArticleTagsDao.selectList(new QueryWrapper<BlogArticleTags>().eq("article_id", vo.getId()));
            List<TagVO> tags = articleTagsList.stream().map(articleTags -> iTagService.get(articleTags.getTagId())).collect(Collectors.toList());
            vo.setTags(tags);
            if (Objects.nonNull(vo.getSpecialId())) {
                SpecialVO specialVO = iSpecialService.get(vo.getSpecialId());
                vo.setSpecialName(specialVO.getName());
            }
        });
        return new PageVO<>(page, voList);
    }


    @Override
    public ArticleVO get(Long id) {
        BlogArticle article = blogArticleDao.selectById(id);
        ArticleVO articleVO = new ArticleVO(article);
        List<BlogArticleTags> articleTagsList = blogArticleTagsDao.selectList(new QueryWrapper<BlogArticleTags>().eq("article_id", id));
        List<TagVO> tags = articleTagsList.stream().map(articleTags -> iTagService.get(articleTags.getTagId())).collect(Collectors.toList());
        articleVO.setTags(tags);
        return articleVO;
    }

    @Override
    public void update(Long id, ArticleVO articleVO, UserVO userVO) {
        BlogArticle article = blogArticleDao.selectById(id);
        if (Objects.isNull(article)) {
            return;
        }
        if (!article.getCreateUserId().equals(userVO.getId())) {
            return;
        }
        boolean flag = false;
        if (!Objects.equals(article.getTitle(), articleVO.getTitle())) {
            article.setTitle(articleVO.getTitle());
            flag = true;
        }
        if (!Objects.equals(article.getContent(), articleVO.getContent())) {
            article.setContent(articleVO.getContent());
            flag = true;
        }
        if (flag) {
            blogArticleDao.updateById(article);
        }
    }

    @Override
    public PageVO<ArticleVO> pagingArticlesUnderSpecial(ArticleQueryVO queryVO) {
        QueryWrapper<BlogArticle> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("special_id", queryVO.getSpecialId());
        IPage<BlogArticle> page = blogArticleDao.selectPage(new Page<>(queryVO.getPageNum(), queryVO.getPageSize()), articleQueryWrapper);
        List<ArticleVO> voList = page.getRecords().stream().map(ArticleVO::new).collect(Collectors.toList());
        voList.forEach(vo -> {
            List<BlogArticleTags> articleTagsList = blogArticleTagsDao.selectList(new QueryWrapper<BlogArticleTags>().eq("article_id", vo.getId()));
            List<TagVO> tags = articleTagsList.stream().map(articleTags -> iTagService.get(articleTags.getTagId())).collect(Collectors.toList());
            vo.setTags(tags);
        });
        return new PageVO<>(page, voList);
    }

    @Override
    public PageVO<ArticleVO> pagingArticlesUnderTag(ArticleQueryVO queryVO) {
        List<BlogArticleTags> list = blogArticleTagsDao.selectList(new QueryWrapper<BlogArticleTags>().eq("tag_id", queryVO.getTagId()));
        if (!CollectionUtils.isEmpty(list)) {
            QueryWrapper<BlogArticle> articleQueryWrapper = new QueryWrapper<>();
            articleQueryWrapper.in("id", list.parallelStream().map(BlogArticleTags::getArticleId).collect(Collectors.toList()));
            IPage<BlogArticle> page = blogArticleDao.selectPage(new Page<>(queryVO.getPageNum(), queryVO.getPageSize()), articleQueryWrapper);
            List<ArticleVO> voList = page.getRecords().stream().map(ArticleVO::new).collect(Collectors.toList());
            voList.forEach(vo -> {
                List<BlogArticleTags> articleTagsList = blogArticleTagsDao.selectList(new QueryWrapper<BlogArticleTags>().eq("article_id", vo.getId()));
                List<TagVO> tags = articleTagsList.stream().map(articleTags -> iTagService.get(articleTags.getTagId())).collect(Collectors.toList());
                vo.setTags(tags);
            });
            return new PageVO<>(page, voList);
        }
        return new PageVO<>();
    }
}
