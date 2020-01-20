package plus.knowing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import plus.knowing.dao.BlogNoteDao;
import plus.knowing.entity.BlogNote;
import plus.knowing.service.INoteService;
import plus.knowing.vo.blog.NoteQueryVO;
import plus.knowing.vo.blog.NoteVO;
import plus.knowing.vo.generic.PageVO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NoteService implements INoteService {

    @Autowired
    private BlogNoteDao blogNoteDao;

    @Override
    public void addNote(NoteVO noteVO) {
        BlogNote note = new BlogNote();
        note.setTitle(noteVO.getTitle());
        note.setContent(noteVO.getContent());
        blogNoteDao.insert(note);
    }

    @Override
    public List<NoteVO> listNotes(NoteVO noteVO) {
        QueryWrapper<BlogNote> noteQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(noteVO.getTitle())) {
            noteQueryWrapper.like("title", noteVO.getTitle());
        }
        List<BlogNote> noteList = blogNoteDao.selectList(noteQueryWrapper);
        return noteList.stream().map(NoteVO::new).collect(Collectors.toList());
    }

    @Override
    public PageVO<NoteVO> pagingListTags(NoteQueryVO queryVO) {
        QueryWrapper<BlogNote> tagQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(queryVO.getTitle())) {
            tagQueryWrapper.like("title", queryVO.getTitle());
        }
        IPage<BlogNote> page = blogNoteDao.selectPage(new Page<>(queryVO.getPageNum(), queryVO.getPageSize()), tagQueryWrapper);
        List<NoteVO> voList = page.getRecords().stream().map(NoteVO::new).collect(Collectors.toList());
        return new PageVO<>(page, voList);
    }

    @Override
    public NoteVO get(Long id) {
        BlogNote note = blogNoteDao.selectById(id);
        return new NoteVO(note);
    }

    @Override
    public void update(Long id, NoteVO noteVO) {
        BlogNote note = blogNoteDao.selectById(id);
        if (Objects.isNull(note)) {
            return;
        }
        boolean flag = false;
        if (!Objects.equals(note.getTitle(), noteVO.getTitle())) {
            note.setTitle(noteVO.getTitle());
            flag = true;
        }
        if (!Objects.equals(note.getContent(), noteVO.getContent())) {
            note.setContent(noteVO.getContent());
            flag = true;
        }
        if (flag) {
            blogNoteDao.updateById(note);
        }
    }
}
