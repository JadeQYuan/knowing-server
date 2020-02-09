package plus.knowing.service;

import plus.knowing.vo.blog.TagCategoryVO;
import plus.knowing.vo.generic.PageVO;
import plus.knowing.vo.blog.TagQueryVO;
import plus.knowing.vo.blog.TagVO;
import plus.knowing.vo.generic.TreeNodeVO;
import plus.knowing.vo.sys.UserVO;

import java.util.List;

public interface ITagService {

    /**
     * 添加标签分类
     * @param categoryVO
     * @param userVO
     */
    void addCategory(TagCategoryVO categoryVO, UserVO userVO);

    /**
     * 获取标签分类信息
     * @param id
     * @return
     */
    TagCategoryVO getCategory(Long id);

    /**
     * 更新标签分类信息
     * @param id
     * @param tagCategoryVO
     */
    void updateCategory(Long id, TagCategoryVO tagCategoryVO);

    /**
     * 获取标签分类列表
     */
    List<TagCategoryVO> listCategory();

    /**
     * 添加标签
     * @param tagVO
     * @param userVO
     */
    void addTag(TagVO tagVO, UserVO userVO);

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

    /**
     * 查询标签列表
     * @return
     */
    List<TreeNodeVO<?>> listTagTree();
}
