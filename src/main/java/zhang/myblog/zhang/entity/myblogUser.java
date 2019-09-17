package zhang.myblog.zhang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class myblogUser extends Model<myblogUser> {
    private static final long serialVersionUID = 1L;
    //id
    @TableId(value = "myblog_user_id", type = IdType.AUTO)
    private Integer myblogUserId;

    //用户名称
    @TableField("myblog_user_name")
    private String myblogUserName;

    //用户头像
    @TableField("myblog_user_headportrait")
    private String myblogUserHeadportrait;

}
