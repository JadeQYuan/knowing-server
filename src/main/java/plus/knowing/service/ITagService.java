package plus.knowing.service;

import plus.knowing.vo.TagVO;

import java.util.List;

public interface ITagService {

    /**
     * 添加标签
     * @param tagVO
     */
    void addTag(TagVO tagVO);

    /**
     * 查询标签列表
     * @param tagVO
     * @return
     */
    List<TagVO> listTags(TagVO tagVO);

    /**
     * 获取标签信息
     * @param id
     * @return
     */
    TagVO get(Long id);

    /**
     * 更新标签信息
     * @param id
     * @param tagVO
     */
    void update(Long id, TagVO tagVO);
}
