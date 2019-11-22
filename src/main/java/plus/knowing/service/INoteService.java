package plus.knowing.service;

import plus.knowing.vo.NoteVO;

import java.util.List;

public interface INoteService {

    /**
     * 添加笔记
     * @param noteVO
     */
    void addNote(NoteVO noteVO);

    /**
     * 查询笔记列表
     * @param noteVO
     * @return
     */
    List<NoteVO> listNotes(NoteVO noteVO);

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
