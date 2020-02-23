package plus.knowing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plus.knowing.annotation.Role;
import plus.knowing.constant.RoleEnum;
import plus.knowing.service.ITagService;
import plus.knowing.vo.blog.TagCategoryVO;
import plus.knowing.vo.blog.TagVO;
import plus.knowing.vo.generic.TreeNodeVO;
import plus.knowing.vo.sys.UserVO;

import java.util.List;

@RestController
@RequestMapping(path = "/tags")
public class TagController {

    @Autowired
    private ITagService iTagService;

    @Role(value = RoleEnum.Admin)
    @PostMapping(path = "/category")
    public void addCategory(@RequestBody @Validated TagCategoryVO categoryVO, @RequestAttribute UserVO user) {
        iTagService.addCategory(categoryVO, user);
    }

    @Role(value = RoleEnum.Admin)
    @GetMapping(path = "/category/{id}")
    public TagCategoryVO getCategory(@PathVariable Long id) {
        return iTagService.getCategory(id);
    }

    @Role(value = RoleEnum.Admin)
    @PutMapping(path = "/category/{id}")
    public void updateCategory(@PathVariable Long id, @RequestBody @Validated TagCategoryVO tagCategoryVO) {
        iTagService.updateCategory(id, tagCategoryVO);
    }

    @Role(value = RoleEnum.Admin)
    @GetMapping(path = "/category")
    public List<TagCategoryVO> listCategory() {
        return iTagService.listCategory();
    }

    @Role(value = RoleEnum.Author)
    @PostMapping(path = "")
    public void addTag(@RequestBody @Validated TagVO tagVO, @RequestAttribute UserVO user) {
        iTagService.addTag(tagVO, user);
    }

    @GetMapping(path = "")
    public List<TagVO> listTags(TagVO tagVO) {
        return iTagService.listTags(tagVO);
    }

    @GetMapping(path = "/popular")
    public List<TagVO> listPopularTags() {
        return iTagService.listPopularTags();
    }

    @GetMapping(path = "/{id}")
    public TagVO getTagInfo(@PathVariable Long id) {
        return iTagService.get(id);
    }

    @Role(value = RoleEnum.Admin)
    @PutMapping(path = "/{id}")
    public void update(@PathVariable Long id, @RequestBody @Validated TagVO tagVO) {
        iTagService.update(id, tagVO);
    }

    @GetMapping(path = "/tree")
    public List<TreeNodeVO<?>> listTagTree() {
        return iTagService.listTagTree();
    }

    @Role(value = RoleEnum.Admin)
    @GetMapping(path = "/all/tree")
    public List<TreeNodeVO<?>> listAllTagTree() {
        return iTagService.listAllTagTree();
    }
}
