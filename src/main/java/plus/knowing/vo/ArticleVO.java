package plus.knowing.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import plus.knowing.entity.Article;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ArticleVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotEmpty
    @NonNull
    private List<TagVO> tags;

    public ArticleVO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
