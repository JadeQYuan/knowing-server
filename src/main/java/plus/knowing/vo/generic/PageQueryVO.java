package plus.knowing.vo.generic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageQueryVO {

    private Integer pageNum = 1;

    private Integer pageSize = 10;
}
