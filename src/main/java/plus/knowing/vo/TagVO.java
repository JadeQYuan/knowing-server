package plus.knowing.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plus.knowing.entity.Tag;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class TagVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    public TagVO(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
        this.description = tag.getDescription();
    }
}
