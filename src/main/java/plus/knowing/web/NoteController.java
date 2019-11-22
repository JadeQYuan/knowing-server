package plus.knowing.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plus.knowing.service.INoteService;
import plus.knowing.vo.NoteVO;

import java.util.List;

@RestController
@RequestMapping(path = "/note")
public class NoteController {

    @Autowired
    private INoteService iNoteService;

    @PostMapping(path = "")
    public void addNote(@RequestBody @Validated NoteVO noteVO) {
        iNoteService.addNote(noteVO);
    }

    @GetMapping(path = "")
    public List<NoteVO> listNotes(NoteVO noteVO) {
        return iNoteService.listNotes(noteVO);
    }

    @GetMapping(path = "/{id}")
    public NoteVO getInfo(@PathVariable Long id) {
        return iNoteService.get(id);
    }

    @PutMapping(path = "/{id}")
    public void update(@PathVariable Long id, @RequestBody @Validated NoteVO noteVO) {
        iNoteService.update(id, noteVO);
    }
}
