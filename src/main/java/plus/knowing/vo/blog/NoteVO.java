package plus.knowing.vo.blog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plus.knowing.entity.BlogNote;
import plus.knowing.util.ConvertUtil;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class NoteVO {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private LocalDateTime createTime;

    public NoteVO(BlogNote note) {
        this(note, false);
    }

    public NoteVO(BlogNote note, boolean full) {
        this.id = note.getId();
        this.title = note.getTitle();
        if (full) {
            this.content = note.getContent();
        } else {
            this.content = ConvertUtil.simpleMarkdown(note.getContent());
        }
        this.createTime = note.getCreateTime();
    }
}
