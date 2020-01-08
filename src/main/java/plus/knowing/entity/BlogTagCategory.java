package plus.knowing.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "blog_tag_category")
public class BlogTagCategory {

    private Long id;

    private String name;

    private String description;
}
