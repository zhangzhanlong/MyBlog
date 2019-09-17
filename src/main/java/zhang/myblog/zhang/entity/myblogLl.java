package zhang.myblog.zhang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class myblogLl extends Model<myblogLl> {
    private static final long serialVersionUID = 1L;
    //id
    @TableId(value = "myblog_ll_id", type = IdType.AUTO)
    private Integer myblogLlId;

    //文章id
    @TableField("myblog_article_id")
    private Integer myblogArticleId;

    //用户id
    @TableField("myblog_user_id")
    private Integer myblogUserId;

    //浏览时间
    @TableField("myblog_ll_time")
    private Date myblogLlTime;

}
