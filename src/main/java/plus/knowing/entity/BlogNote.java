package plus.knowing.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "blog_note")
public class BlogNote {

    private Long id;

    private String title;

    private String content;
}
