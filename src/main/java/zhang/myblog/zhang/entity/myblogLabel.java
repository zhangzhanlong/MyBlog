package zhang.myblog.zhang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class myblogLabel extends Model<myblogLabel> {
    private static final long serialVersionUID = 1L;
    //id
    @TableId(value = "myblog_label_id", type = IdType.AUTO)
    private Integer myblogLabelId;

    //标签名称
    @TableField("myblog_label_name")
    private String myblogLabelName;
}
