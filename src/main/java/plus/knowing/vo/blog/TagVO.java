package plus.knowing.vo.blog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plus.knowing.entity.BlogTag;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class TagVO {

    private Long id;

    @NotBlank
    private String name;

    private Long categoryId;

    @NotBlank
    private String intro;

    public TagVO(BlogTag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
        this.categoryId = tag.getCategoryId();
        this.intro = tag.getIntro();
    }
}
