package plus.knowing.vo.blog;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import plus.knowing.entity.BlogArticle;
import plus.knowing.util.ConvertUtil;

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
        this(article, false);
    }

    public ArticleVO(BlogArticle article, boolean full) {
        this.id = article.getId();
        this.title = article.getTitle();
        if (full) {
            this.content = article.getContent();
        } else {
            this.content = ConvertUtil.simpleMarkdown(article.getContent());
        }
        this.specialId = article.getSpecialId();
        this.createTime = article.getCreateTime();
    }
}
