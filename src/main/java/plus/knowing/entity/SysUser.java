package plus.knowing.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "sys_user")
public class SysUser {

    private Long id;

    private String nickname;

    private String avatarUrl;

    private LocalDateTime createTime;
}
