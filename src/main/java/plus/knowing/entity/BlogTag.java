package plus.knowing.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import plus.knowing.vo.blog.TagVO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@TableName(value = "blog_tag")
public class BlogTag {

    private Long id;

    private Long categoryId;

    private String name;

    private String intro;

    private String iconUrl;

    private LocalDateTime createTime;

    private Long createUserId;

    public BlogTag(TagVO tagVO) {
        this.categoryId = tagVO.getCategoryId();
        this.name = tagVO.getName();
        this.intro = tagVO.getIntro();
    }
}
