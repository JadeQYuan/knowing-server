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
import plus.knowing.vo.sys.UserVO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NoteService implements INoteService {

    @Autowired
    private BlogNoteDao blogNoteDao;

    @Override
    public void addNote(NoteVO noteVO, UserVO userVO) {
        BlogNote note = new BlogNote();
        note.setTitle(noteVO.getTitle());
        note.setContent(noteVO.getContent());
        note.setCreateUserId(userVO.getId());
        note.setCreateTime(LocalDateTime.now());
        blogNoteDao.insert(note);
    }

    @Override
    public PageVO<NoteVO> pagingListTags(NoteQueryVO queryVO, UserVO userVO) {
        QueryWrapper<BlogNote> noteQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(queryVO.getTitle())) {
            noteQueryWrapper.like("title", queryVO.getTitle());
        }
        noteQueryWrapper.eq("create_user_id", userVO.getId());
        IPage<BlogNote> page = blogNoteDao.selectPage(new Page<>(queryVO.getPageNum(), queryVO.getPageSize()), noteQueryWrapper);
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
