package plus.knowing.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import plus.knowing.vo.blog.ArticleVO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@TableName(value = "blog_article")
public class BlogArticle {

    private Long id;

    private String title;

    private String content;

    private Long specialId;

    private LocalDateTime createTime;

    private Long createUserId;

    public BlogArticle(ArticleVO articleVO) {
        this.title = articleVO.getTitle();
        this.content = articleVO.getContent();
        this.specialId = articleVO.getSpecialId();
    }
}
