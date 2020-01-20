package plus.knowing.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import plus.knowing.vo.blog.SpecialVO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@TableName(value = "blog_special")
public class BlogSpecial {

    private Long id;

    private String name;

    private String intro;

    private Boolean shared;

    private LocalDateTime createTime;

    private Long createUserId;

    public BlogSpecial(SpecialVO specialVO) {
        this.name = specialVO.getName();
        this.intro= specialVO.getIntro();
        this.shared = specialVO.getShared();
    }
}
