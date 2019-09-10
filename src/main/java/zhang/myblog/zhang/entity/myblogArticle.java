package zhang.myblog.zhang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SysCr对象", description = "出入表")
public class myblogArticle extends Model<myblogArticle> {
    private static final long serialVersionUID = 1L;
    //id
    @TableId(value = "myblog_article_id", type = IdType.AUTO)
    private Integer myblogArticleId;
    //文章标题
    @TableField("myblog_article_title")
    private String myblogArticleTitle;
    //文章内容
    @TableField("myblog_article_contents")
    private String myblogArticleContents;
    //文章创建时间
    @TableField("myblog_article_creationtime")
    private Date myblogArticleCreationtime;
    //文章标签
    @TableField("myblog_label_id")
    private String myblogLabelId;
    //是否可用
    @TableLogic
    @TableField("DEL_FLAG")
    private Integer delFlag;
}
