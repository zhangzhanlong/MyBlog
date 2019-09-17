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
public class myblogDz extends Model<myblogDz> {
    private static final long serialVersionUID = 1L;
    //id
    @TableId(value = "myblog_dz_id", type = IdType.AUTO)
    private Integer myblogDzId;

    //文章id
    @TableField("myblog_article_id")
    private Integer myblogArticleId;

    //用户id
    @TableField("myblog_user_id")
    private Integer myblogUserId;

    //点赞时间
    @TableField("myblog_dz_time")
    private Date myblogDzTime;

}
