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
     * @param userVO
     * @return
     */
    List<SpecialVO> listMy(SpecialQueryVO queryVO, UserVO userVO);

    /**
     * 分页查询最新专栏
     * @param queryVO
     * @return
     */
    PageVO<SpecialVO> pagingPopularList(SpecialQueryVO queryVO);

    /**
     * 分页查询我的专栏
     * @param queryVO
     * @param userVO
     * @return
     */
    PageVO<SpecialVO> pagingMyList(SpecialQueryVO queryVO, UserVO userVO);

    /**
     * 分页查询所有专栏
     * @param queryVO
     * @return
     */
    PageVO<SpecialVO> pagingAllList(SpecialQueryVO queryVO);

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
