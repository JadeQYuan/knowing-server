package plus.knowing.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "sys_user_oauth")
public class SysUserOAuth {

    private Long id;

    @TableField(value = "user_id")
    private Long userId;

    private String platform;

    @TableField(value = "open_id")
    private String openId;
}
