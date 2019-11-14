package plus.knowing.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "article_tag")
public class ArticleTags {

    @TableField(value = "article_id")
    private Long articleId;

    @TableField(value = "tag_id")
    private Long tagId;
}
