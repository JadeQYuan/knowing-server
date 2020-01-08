package plus.knowing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import plus.knowing.dao.BlogArticleDao;
import plus.knowing.dao.BlogArticleTagsDao;
import plus.knowing.entity.BlogArticle;
import plus.knowing.entity.BlogArticleTags;
import plus.knowing.service.IArticleService;
import plus.knowing.service.ITagService;
import plus.knowing.vo.ArticleQueryVO;
import plus.knowing.vo.ArticleVO;
import plus.knowing.vo.PageVO;
import plus.knowing.vo.TagVO;

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

    @Override
    @Transactional
    public void addArticle(ArticleVO articleVO) {
        BlogArticle article = new BlogArticle();
        article.setTitle(articleVO.getTitle());
        article.setContent(articleVO.getContent());
        blogArticleDao.insert(article);
        articleVO.getTags().forEach(tagVO -> {
            BlogArticleTags articleTags = new BlogArticleTags();
            articleTags.setArticleId(article.getId());
            articleTags.setTagId(tagVO.getId());
            blogArticleTagsDao.insert(articleTags);
        });
    }

    @Override
    public List<ArticleVO> listArticles(ArticleVO articleVO) {
        QueryWrapper<BlogArticle> articleQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(articleVO.getTitle())) {
            articleQueryWrapper.like("title", articleVO.getTitle());
        }
        List<BlogArticle> articleList = blogArticleDao.selectList(articleQueryWrapper);
        List<ArticleVO> articleVOList = articleList.stream().map(ArticleVO::new).collect(Collectors.toList());
        articleVOList.forEach(vo -> {
            List<BlogArticleTags> articleTagsList = blogArticleTagsDao.selectList(new QueryWrapper<BlogArticleTags>().eq("article_id", vo.getId()));
            List<TagVO> tags = articleTagsList.stream().map(articleTags -> iTagService.get(articleTags.getTagId())).collect(Collectors.toList());
            vo.setTags(tags);
        });
        return articleVOList;
    }

    @Override
    public PageVO<ArticleVO> pagingListTags(ArticleQueryVO queryVO) {
        QueryWrapper<BlogArticle> tagQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(queryVO.getTitle())) {
            tagQueryWrapper.like("title", queryVO.getTitle());
        }
        IPage<BlogArticle> page = blogArticleDao.selectPage(new Page<>(queryVO.getPageNum(), queryVO.getPageSize()), tagQueryWrapper);
        List<ArticleVO> voList = page.getRecords().stream().map(ArticleVO::new).collect(Collectors.toList());
        voList.forEach(vo -> {
            List<BlogArticleTags> articleTagsList = blogArticleTagsDao.selectList(new QueryWrapper<BlogArticleTags>().eq("article_id", vo.getId()));
            List<TagVO> tags = articleTagsList.stream().map(articleTags -> iTagService.get(articleTags.getTagId())).collect(Collectors.toList());
            vo.setTags(tags);
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
    public void update(Long id, ArticleVO articleVO) {
        BlogArticle article = blogArticleDao.selectById(id);
        if (Objects.isNull(article)) {
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
}
