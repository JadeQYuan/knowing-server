package plus.knowing.vo.blog;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import plus.knowing.entity.BlogArticle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
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

    private Long specialId;

    @NotEmpty
    @NonNull
    private List<TagVO> tags;

    private LocalDateTime createTime;

    private String specialName;

    public ArticleVO(BlogArticle article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.specialId = article.getSpecialId();
        this.createTime = article.getCreateTime();
    }
}
