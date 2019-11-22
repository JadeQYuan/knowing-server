package plus.knowing.service;

import plus.knowing.vo.ArticleVO;
import plus.knowing.vo.TagVO;

import java.util.List;

public interface IArticleService {

    /**
     * 添加文章
     * @param articleVO
     */
    void addArticle(ArticleVO articleVO);

    /**
     * 查询文章列表
     * @param articleVO
     * @return
     */
    List<ArticleVO> listArticles(ArticleVO articleVO);

    /**
     * 获取文章
     * @param id
     * @return
     */
    ArticleVO get(Long id);

    /**
     * 更新文章
     * @param id
     * @param articleVO
     */
    void update(Long id, ArticleVO articleVO);
}
