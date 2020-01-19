package plus.knowing.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "blog_note")
public class BlogNote {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createTime;

    private Long createUserId;
}
