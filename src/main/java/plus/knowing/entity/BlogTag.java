package plus.knowing.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "blog_tag")
public class BlogTag {

    private Long id;

    private Long categoryId;

    private String name;

    private String intro;

    private String iconUrl;

    private LocalDateTime createTime;

    private Long createUserId;
}
