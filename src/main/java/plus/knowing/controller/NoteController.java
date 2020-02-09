package plus.knowing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plus.knowing.annotation.Role;
import plus.knowing.constant.RoleEnum;
import plus.knowing.service.INoteService;
import plus.knowing.vo.blog.NoteQueryVO;
import plus.knowing.vo.blog.NoteVO;
import plus.knowing.vo.generic.PageVO;
import plus.knowing.vo.sys.UserVO;

@RestController
@RequestMapping(path = "/notes")
public class NoteController {

    @Autowired
    private INoteService iNoteService;

    @Role(value = {RoleEnum.Author})
    @PostMapping(path = "")
    public void addNote(@RequestBody @Validated NoteVO noteVO, @RequestAttribute UserVO user) {
        iNoteService.addNote(noteVO, user);
    }

    @Role(value = {RoleEnum.Author})
    @GetMapping(path = "/my/paging")
    public PageVO<NoteVO> pagingListTags(NoteQueryVO queryVO, @RequestAttribute UserVO user) {
        return iNoteService.pagingListNotes(queryVO, user);
    }

    @Role(value = {RoleEnum.Admin})
    @GetMapping(path = "/all/paging")
    public PageVO<NoteVO> pagingAllList(NoteQueryVO queryVO) {
        return iNoteService.pagingAllList(queryVO);
    }

    @Role(value = {RoleEnum.Author})
    @GetMapping(path = "/{id}")
    public NoteVO getInfo(@PathVariable Long id) {
        return iNoteService.get(id);
    }

    @Role(value = {RoleEnum.Author})
    @PutMapping(path = "/{id}")
    public void update(@PathVariable Long id, @RequestBody @Validated NoteVO noteVO) {
        iNoteService.update(id, noteVO);
    }
}
