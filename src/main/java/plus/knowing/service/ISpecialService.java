package plus.knowing.service;

import plus.knowing.vo.generic.PageVO;
import plus.knowing.vo.blog.SpecialQueryVO;
import plus.knowing.vo.blog.SpecialVO;
import plus.knowing.vo.sys.UserVO;

import java.util.List;

public interface ISpecialService {

    /**
     * 添加专栏
     * @param specialVO
     * @param userVO
     */
    void add(SpecialVO specialVO, UserVO userVO);

    /**
     * 查询专栏列表
     * @param queryVO
     * @return
     */
    List<SpecialVO> list(SpecialQueryVO queryVO);

    /**
     * 分页查询专栏
     * @param queryVO
     * @return
     */
    PageVO<SpecialVO> pagingList(SpecialQueryVO queryVO);

    /**
     * 获取专栏
     * @param id
     * @return
     */
    SpecialVO get(Long id);

    /**
     * 更新专栏
     * @param id
     * @param specialVO
     * @param userVO
     */
    void update(Long id, SpecialVO specialVO, UserVO userVO);
}
