package plus.knowing.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plus.knowing.service.ITagService;
import plus.knowing.vo.PageVO;
import plus.knowing.vo.TagQueryVO;
import plus.knowing.vo.TagVO;

import java.util.List;

@RestController
@RequestMapping(path = "/tag")
public class TagController {

    @Autowired
    private ITagService iTagService;

    @PostMapping(path = "")
    public void addTag(@RequestBody @Validated TagVO tagVO) {
        iTagService.addTag(tagVO);
    }

    @GetMapping(path = "")
    public List<TagVO> listTags(TagVO tagVO) {
        return iTagService.listTags(tagVO);
    }

    @GetMapping(path = "/paging")
    public PageVO<TagVO> pagingListTags(TagQueryVO queryVO) {
        return iTagService.pagingListTags(queryVO);
    }

    @GetMapping(path = "/{id}")
    public TagVO getTagInfo(@PathVariable Long id) {
        return iTagService.get(id);
    }

    @PutMapping(path = "/{id}")
    public void update(@PathVariable Long id, @RequestBody @Validated TagVO tagVO) {
        iTagService.update(id, tagVO);
    }
}
