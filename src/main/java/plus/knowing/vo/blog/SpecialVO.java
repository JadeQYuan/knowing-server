package plus.knowing.vo.blog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plus.knowing.entity.BlogSpecial;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SpecialVO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String intro;

    @NotNull
    private Boolean shared;

    private LocalDateTime createTime;

    public SpecialVO(BlogSpecial special) {
        this.id = special.getId();
        this.name = special.getName();
        this.intro = special.getIntro();
        this.shared = special.getShared();
        this.createTime = special.getCreateTime();
    }
}
