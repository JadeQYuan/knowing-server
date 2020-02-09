package plus.knowing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import plus.knowing.dao.BlogSpecialDao;
import plus.knowing.entity.BlogSpecial;
import plus.knowing.service.ISpecialService;
import plus.knowing.util.ConvertUtil;
import plus.knowing.vo.generic.PageVO;
import plus.knowing.vo.blog.SpecialQueryVO;
import plus.knowing.vo.blog.SpecialVO;
import plus.knowing.vo.sys.UserVO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class SpecialService implements ISpecialService {

    @Autowired
    private BlogSpecialDao blogSpecialDao;

    @Override
    public void add(SpecialVO specialVO, UserVO userVO) {
        BlogSpecial blogSpecial = new BlogSpecial(specialVO);
        blogSpecial.setCreateTime(LocalDateTime.now());
        blogSpecial.setCreateUserId(userVO.getId());
        blogSpecialDao.insert(blogSpecial);
    }

    @Override
    public List<SpecialVO> listMy(SpecialQueryVO queryVO, UserVO userVO) {
        QueryWrapper<BlogSpecial> specialWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(queryVO.getName())) {
            specialWrapper.eq("name", queryVO.getName());
        }
        specialWrapper.eq("create_id", userVO.getId());
        List<BlogSpecial> specialList = blogSpecialDao.selectList(specialWrapper);
        return ConvertUtil.convert(specialList, SpecialVO.class);
    }

    @Override
    public PageVO<SpecialVO> pagingPopularList(SpecialQueryVO queryVO) {
        QueryWrapper<BlogSpecial> specialWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(queryVO.getName())) {
            specialWrapper.eq("name", queryVO.getName());
        }
        specialWrapper.eq("shared", true);
        IPage<BlogSpecial> page = blogSpecialDao.selectPage(new Page<>(queryVO.getPageNum(), queryVO.getPageSize()), specialWrapper);
        return PageVO.build(page, SpecialVO.class);
    }

    @Override
    public PageVO<SpecialVO> pagingMyList(SpecialQueryVO queryVO, UserVO userVO) {
        QueryWrapper<BlogSpecial> specialWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(queryVO.getName())) {
            specialWrapper.eq("name", queryVO.getName());
        }
        specialWrapper.eq("create_user_id", userVO.getId());
        IPage<BlogSpecial> page = blogSpecialDao.selectPage(new Page<>(queryVO.getPageNum(), queryVO.getPageSize()), specialWrapper);
        return PageVO.build(page, SpecialVO.class);
    }

    @Override
    public SpecialVO get(Long id) {
        BlogSpecial special = blogSpecialDao.selectById(id);
        return new SpecialVO(special);
    }

    @Override
    public void update(Long id, SpecialVO specialVO, UserVO userVO) {
        BlogSpecial special = blogSpecialDao.selectById(id);
        if (!Objects.equals(userVO.getId(), special.getCreateUserId())) {
            return;
        }
        special.setName(specialVO.getName());
        special.setIntro(specialVO.getIntro());
        special.setShared(specialVO.getShared());
        blogSpecialDao.updateById(special);
    }
}
