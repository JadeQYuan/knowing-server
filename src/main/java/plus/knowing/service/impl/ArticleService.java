package plus.knowing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import plus.knowing.dao.ArticleDao;
import plus.knowing.dao.ArticleTagsDao;
import plus.knowing.entity.Article;
import plus.knowing.entity.ArticleTags;
import plus.knowing.service.IArticleService;
import plus.knowing.service.ITagService;
import plus.knowing.vo.ArticleVO;
import plus.knowing.vo.TagVO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleService implements IArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleTagsDao articleTagsDao;

    @Autowired
    private ITagService iTagService;

    @Override
    @Transactional
    public void addArticle(ArticleVO articleVO) {
        Article article = new Article();
        article.setTitle(articleVO.getTitle());
        article.setContent(articleVO.getContent());
        articleDao.insert(article);
        articleVO.getTags().forEach(tagVO -> {
            ArticleTags articleTags = new ArticleTags();
            articleTags.setArticleId(article.getId());
            articleTags.setTagId(tagVO.getId());
            articleTagsDao.insert(articleTags);
        });
    }

    @Override
    public List<ArticleVO> listArticles(ArticleVO articleVO) {
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(articleVO.getTitle())) {
            articleQueryWrapper.like("title", articleVO.getTitle());
        }
        List<Article> articleList = articleDao.selectList(articleQueryWrapper);
        List<ArticleVO> articleVOList = articleList.stream().map(ArticleVO::new).collect(Collectors.toList());
        articleVOList.forEach(vo -> {
            List<ArticleTags> articleTagsList = articleTagsDao.selectList(new QueryWrapper<ArticleTags>().eq("article_id", vo.getId()));
            List<TagVO> tags = articleTagsList.stream().map(articleTags -> iTagService.get(articleTags.getTagId())).collect(Collectors.toList());
            vo.setTags(tags);
        });
        return articleVOList;
    }

    @Override
    public ArticleVO get(Long id) {
        Article article = articleDao.selectById(id);
        ArticleVO articleVO = new ArticleVO(article);
        List<ArticleTags> articleTagsList = articleTagsDao.selectList(new QueryWrapper<ArticleTags>().eq("article_id", id));
        List<TagVO> tags = articleTagsList.stream().map(articleTags -> iTagService.get(articleTags.getTagId())).collect(Collectors.toList());
        articleVO.setTags(tags);
        return articleVO;
    }

    @Override
    public void update(Long id, ArticleVO articleVO) {
        Article article = articleDao.selectById(id);
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
            articleDao.updateById(article);
        }
    }
}
