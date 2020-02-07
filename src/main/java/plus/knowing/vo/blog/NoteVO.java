package plus.knowing.vo.blog;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plus.knowing.entity.BlogNote;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class NoteVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public NoteVO(BlogNote note) {
        this.id = note.getId();
        this.title = note.getTitle();
        this.content = note.getContent();
    }
}
