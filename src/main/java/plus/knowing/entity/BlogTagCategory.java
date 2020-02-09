package plus.knowing.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import plus.knowing.vo.blog.TagCategoryVO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@TableName(value = "blog_tag_category")
public class BlogTagCategory {

    private Long id;

    private String name;

    private Boolean shared;

    private LocalDateTime createTime;

    private Long createUserId;

    public BlogTagCategory(TagCategoryVO tagCategoryVO) {
        this.name = tagCategoryVO.getName();
        this.shared = tagCategoryVO.getShared();
    }
}
