package plus.knowing.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "blog_article")
public class BlogArticle {

    private Long id;

    private String title;

    private String content;

    private Long specialId;

    private LocalDateTime createTime;

    private Long createUserId;
}
