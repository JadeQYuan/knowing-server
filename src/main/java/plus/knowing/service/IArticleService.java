package plus.knowing.service;

import plus.knowing.vo.blog.ArticleQueryVO;
import plus.knowing.vo.blog.ArticleVO;
import plus.knowing.vo.generic.PageQueryVO;
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
     * 查询所有文章列表
     * @param queryVO
     * @return
     */
    PageVO<ArticleVO> listAllArticles(ArticleQueryVO queryVO);

    /**
     * 分页查询最新文章
     * @return
     */
    PageVO<ArticleVO> pagingNewestArticles(PageQueryVO queryVO);

    /**
     * 分页查询我的文章
     * @param queryVO
     * @param userVO
     * @return
     */
    PageVO<ArticleVO> pagingListMyArticles(ArticleQueryVO queryVO, UserVO userVO);

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
