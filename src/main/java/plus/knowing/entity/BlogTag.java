package plus.knowing.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "blog_tag")
public class BlogTag {

    private Long id;

    private String name;

    private String description;
}
