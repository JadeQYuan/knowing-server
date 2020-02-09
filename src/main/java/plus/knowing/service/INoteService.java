package plus.knowing.service;

import plus.knowing.vo.blog.NoteQueryVO;
import plus.knowing.vo.blog.NoteVO;
import plus.knowing.vo.generic.PageVO;
import plus.knowing.vo.sys.UserVO;

import java.util.List;

public interface INoteService {

    /**
     * 添加笔记
     * @param noteVO
     * @param userVO
     */
    void addNote(NoteVO noteVO, UserVO userVO);

    /**
     * 分页查询笔记
     * @param queryVO
     * @param userVO
     * @return
     */
    PageVO<NoteVO> pagingListNotes(NoteQueryVO queryVO, UserVO userVO);

    /**
     * 分页查询所有笔记
     * @param queryVO
     * @return
     */
    PageVO<NoteVO> pagingAllList(NoteQueryVO queryVO);

    /**
     * 获取笔记
     * @param id
     * @return
     */
    NoteVO get(Long id);

    /**
     * 更新笔记
     * @param id
     * @param noteVO
     */
    void update(Long id, NoteVO noteVO);
}
