package zhang.myblog.zhang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = true)
public class myblogArticle extends Model<myblogArticle> {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(value = "myblog_article_id", type = IdType.AUTO)
    private Integer myblogArticleId;
    /**
     * 文章标题
     */
    @TableField("myblog_article_title")
    private String myblogArticleTitle;

    /**
     * 文章内容
     */
    @TableField("myblog_article_contents")
    private String myblogArticleContents;

    /**
     * 文章内容warkdown
     */
    @TableField("myblog_article_contents_markdown")
    private String myblogArticleContentsMarkdown;

    /**
     * 文章创建时间
     */
    @TableField("myblog_article_creationtime")
    private Date myblogArticleCreationtime;

    /**
     * 文章标签
     */
    @TableField("myblog_label_id")
    private Integer myblogLabelId;

    /**
     * 用户id
     */
    @TableField("myblog_user_id")
    private Integer myblogUserId;

    /**
     * 是否可用
     */
    @TableLogic
    @TableField("DEL_FLAG")
    private Integer delFlag;

    /**
     * 是否置顶
     */
    @TableField("myblog_article_stick")
    private Integer myblogArticleStick;
    /**
     * 自定义
     */
    @TableField(exist = false)
    private String myblogUserName;

    @TableField(exist = false)
    private String myblogUserHeadportrait;

    @TableField(exist = false)
    private String myblogLabelName;
}
