package plus.knowing.service;

import plus.knowing.vo.blog.ArticleQueryVO;
import plus.knowing.vo.blog.ArticleVO;
import plus.knowing.vo.generic.PageVO;
import plus.knowing.vo.sys.UserVO;

import java.util.List;

public interface IArticleService {

    /**
     * 添加文章
     * @param articleVO
     * @param userVO
     */
    void addArticle(ArticleVO articleVO, UserVO userVO);

    /**
     * 查询文章列表
     * @param articleVO
     * @return
     */
    List<ArticleVO> listArticles(ArticleVO articleVO);

    /**
     * 分页查询笔记
     * @param queryVO
     * @return
     */
    PageVO<ArticleVO> pagingListTags(ArticleQueryVO queryVO);

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
     * @param userVO
     */
    void update(Long id, ArticleVO articleVO, UserVO userVO);
}
