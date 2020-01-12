package plus.knowing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plus.knowing.service.IArticleService;
import plus.knowing.vo.ArticleQueryVO;
import plus.knowing.vo.ArticleVO;
import plus.knowing.vo.PageVO;

import java.util.List;

@RestController
@RequestMapping(path = "/article")
public class ArticleController {

    @Autowired
    private IArticleService iArticleService;

    @PostMapping(path = "")
    public void addArticle(@RequestBody @Validated ArticleVO articleVO) {
        iArticleService.addArticle(articleVO);
    }

    @GetMapping(path = "")
    public List<ArticleVO> listArticles(ArticleVO articleVO) {
        return iArticleService.listArticles(articleVO);
    }

    @GetMapping(path = "/paging")
    public PageVO<ArticleVO> pagingListTags(ArticleQueryVO queryVO) {
        return iArticleService.pagingListTags(queryVO);
    }

    @GetMapping(path = "/{id}")
    public ArticleVO getInfo(@PathVariable Long id) {
        return iArticleService.get(id);
    }

    @PutMapping(path = "/{id}")
    public void update(@PathVariable Long id, @RequestBody @Validated ArticleVO articleVO) {
        iArticleService.update(id, articleVO);
    }
}
