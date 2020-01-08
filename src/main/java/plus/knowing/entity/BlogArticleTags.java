package plus.knowing.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "blog_article_tag")
public class BlogArticleTags {

    @TableField(value = "article_id")
    private Long articleId;

    @TableField(value = "tag_id")
    private Long tagId;
}
