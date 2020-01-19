package plus.knowing.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "blog_tag_category")
public class BlogTagCategory {

    private Long id;

    private String name;

    private Boolean shared;

    private LocalDateTime createTime;

    private Long createUserId;
}
