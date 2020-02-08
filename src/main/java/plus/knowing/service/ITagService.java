package plus.knowing.service;

import plus.knowing.vo.generic.PageVO;
import plus.knowing.vo.blog.TagQueryVO;
import plus.knowing.vo.blog.TagVO;

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
     * 查询最热标签列表（10个）
     * @return
     */
    List<TagVO> listPopularTags();

    /**
     * 分页查询标签列表
     * @param queryVO
     * @return
     */
    PageVO<TagVO> pagingListTags(TagQueryVO queryVO);

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
