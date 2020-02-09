package plus.knowing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.knowing.annotation.Role;
import plus.knowing.constant.RoleEnum;
import plus.knowing.service.ISpecialService;
import plus.knowing.vo.blog.SpecialQueryVO;
import plus.knowing.vo.blog.SpecialVO;
import plus.knowing.vo.generic.PageVO;
import plus.knowing.vo.sys.UserVO;

import java.util.List;

@RestController
@RequestMapping(path = "/specials")
public class SpecialController {

    @Autowired
    private ISpecialService iSpecialService;

    @PostMapping(path = "")
    @Role(value = RoleEnum.Author)
    public void add(@Validated @RequestBody SpecialVO specialVO, @RequestAttribute UserVO user) {
        iSpecialService.add(specialVO, user);
    }

    @GetMapping(path = "/my")
    @Role(value = RoleEnum.Author)
    public List<SpecialVO> listMy(SpecialQueryVO queryVO, @RequestAttribute UserVO user) {
        return iSpecialService.listMy(queryVO, user);
    }

    @GetMapping(path = "/paging")
    public PageVO<SpecialVO> pagingList(SpecialQueryVO queryVO) {
        return iSpecialService.pagingPopularList(queryVO);
    }

    @GetMapping(path = "/my/paging")
    @Role(value = RoleEnum.Author)
    public PageVO<SpecialVO> pagingMyList(SpecialQueryVO queryVO, @RequestAttribute UserVO user) {
        return iSpecialService.pagingMyList(queryVO, user);
    }

    @GetMapping(path = "/{id}")
    public SpecialVO get(@PathVariable Long id) {
        return iSpecialService.get(id);
    }

    @PutMapping(path = "/{id}")
    @Role(value = RoleEnum.Author)
    public void update(@PathVariable Long id, @Validated @RequestBody SpecialVO specialVO, @RequestAttribute UserVO user) {
        iSpecialService.update(id, specialVO, user);
    }
}
