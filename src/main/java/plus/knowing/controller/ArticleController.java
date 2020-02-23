package plus.knowing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plus.knowing.annotation.Role;
import plus.knowing.constant.RoleEnum;
import plus.knowing.service.IArticleService;
import plus.knowing.vo.blog.ArticleQueryVO;
import plus.knowing.vo.blog.ArticleVO;
import plus.knowing.vo.generic.PageQueryVO;
import plus.knowing.vo.generic.PageVO;
import plus.knowing.vo.sys.UserVO;

@RestController
@RequestMapping(path = "/articles")
public class ArticleController {

    @Autowired
    private IArticleService iArticleService;

    @Role(value = RoleEnum.Author)
    @PostMapping(path = "")
    public void addArticle(@RequestBody @Validated ArticleVO articleVO, @RequestAttribute UserVO user) {
        iArticleService.addArticle(articleVO, user);
    }

    @Role(value = RoleEnum.Admin)
    @GetMapping(path = "/all/paging")
    public PageVO<ArticleVO> listAllArticles(ArticleQueryVO articleVO) {
        return iArticleService.listAllArticles(articleVO);
    }

    @GetMapping(path = "/newest/paging")
    public PageVO<ArticleVO> pagingNewestArticles(PageQueryVO queryVO) {
        return iArticleService.pagingNewestArticles(queryVO);
    }

    @Role(value = RoleEnum.Author)
    @GetMapping(path = "/my/paging")
    public PageVO<ArticleVO> pagingListMyArticles(ArticleQueryVO queryVO, @RequestAttribute UserVO user) {
        return iArticleService.pagingListMyArticles(queryVO, user);
    }

    @GetMapping(path = "/{id}")
    public ArticleVO getInfo(@PathVariable Long id) {
        return iArticleService.get(id);
    }

    @Role(value = RoleEnum.Author)
    @PutMapping(path = "/{id}")
    public void update(@PathVariable Long id, @RequestBody @Validated ArticleVO articleVO, @RequestAttribute UserVO user) {
        iArticleService.update(id, articleVO, user);
    }

    @GetMapping(path = "/underSpecial")
    public PageVO<ArticleVO> pagingArticlesUnderSpecial(ArticleQueryVO queryVO) {
        return iArticleService.pagingArticlesUnderSpecial(queryVO);
    }

    @GetMapping(path = "/underTag")
    public PageVO<ArticleVO> pagingArticlesUnderTag(ArticleQueryVO queryVO) {
        return iArticleService.pagingArticlesUnderTag(queryVO);
    }
}
