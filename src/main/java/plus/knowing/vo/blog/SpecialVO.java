package plus.knowing.vo.blog;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plus.knowing.entity.BlogSpecial;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class SpecialVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String intro;

    @NotNull
    private Boolean shared;

    public SpecialVO(BlogSpecial special) {
        this.id = special.getId();
        this.name = special.getName();
        this.intro = special.getIntro();
        this.shared = special.getShared();
    }
}
