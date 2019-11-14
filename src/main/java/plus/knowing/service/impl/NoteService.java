package plus.knowing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import plus.knowing.dao.NoteDao;
import plus.knowing.entity.Note;
import plus.knowing.service.INoteService;
import plus.knowing.vo.NoteVO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService implements INoteService {

    @Autowired
    private NoteDao noteDao;

    @Override
    public void addNote(NoteVO noteVO) {
        Note note = new Note();
        note.setTitle(noteVO.getTitle());
        note.setContent(noteVO.getContent());
        noteDao.insert(note);
    }

    @Override
    public List<NoteVO> listNotes(NoteVO noteVO) {
        QueryWrapper<Note> noteQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(noteVO.getTitle())) {
            noteQueryWrapper.like("title", noteVO.getTitle());
        }
        List<Note> noteList = noteDao.selectList(noteQueryWrapper);
        return noteList.stream().map(NoteVO::new).collect(Collectors.toList());
    }

    @Override
    public NoteVO get(Long id) {
        Note note = noteDao.selectById(id);
        return new NoteVO(note);
    }
}
