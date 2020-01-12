package plus.knowing.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "blog_article")
public class BlogArticle {

    private Long id;

    private String title;

    private String content;
}
