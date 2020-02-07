package plus.knowing.vo.blog;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plus.knowing.entity.BlogTag;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class TagVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @NotBlank
    private String name;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;

    @NotBlank
    private String description;

    public TagVO(BlogTag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
        this.categoryId = tag.getCategoryId();
        this.description = tag.getIntro();
    }
}
