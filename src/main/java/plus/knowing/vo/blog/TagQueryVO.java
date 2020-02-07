package plus.knowing.vo.blog;

import lombok.Getter;
import lombok.Setter;
import plus.knowing.vo.generic.PageQueryVO;

@Getter
@Setter
public class TagQueryVO extends PageQueryVO {

    private String name;
}
