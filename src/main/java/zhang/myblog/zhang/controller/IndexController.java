package zhang.myblog.zhang.controller;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zhang.myblog.zhang.dao.IndexDao;
import zhang.myblog.zhang.entity.myblogArticle;
import zhang.myblog.zhang.service.IndexService;
import zhang.myblog.zhang.util.AjaxResult;

import java.util.*;


@Controller
@ResponseBody
@RequestMapping("/index")
public class IndexController {
    @Autowired
    IndexService indexService;
    @Autowired
    IndexDao indexDao;
     /**
     * 查询置顶文章
     *
     * @return
     */
    @RequestMapping(value = "/selectArticleZd")
    public AjaxResult selectArticleZd(String myblogArticleTitle) {
        Map<String, Object> param = new HashMap<String, Object>();
        List<Map<String, Object>> DzOrLlCount = null;
        if (StringUtils.isNotBlank(myblogArticleTitle)) {
            param.put("myblogArticleTitle", myblogArticleTitle);
        }
        List<Map<String, Object>> ArticleList = indexService.queryAllZd(param);
        for (Map map : ArticleList) {
            if (map.get("myblogArticleId") != null) {
                DzOrLlCount = indexService.selectDzOrLl(Integer.valueOf(String.valueOf(map.get("myblogArticleId"))));
            }

            map.put("DzOrLlCount", DzOrLlCount);
        }
        return AjaxResult.success(ArticleList);
    }

    /**
     * 查询全部文章
     *
     * @return
     */
    @RequestMapping(value = "/selectArticle")
    public AjaxResult selectArticle(String myblogArticleTitle) {
        Map<String, Object> param = new HashMap<String, Object>();
        List<Map<String, Object>> DzOrLlCount = null;
        if (StringUtils.isNotBlank(myblogArticleTitle)) {
            param.put("myblogArticleTitle", myblogArticleTitle);
        }
        List<Map<String, Object>> ArticleList = indexService.queryAll(param);
        for (Map map : ArticleList) {
            if (map.get("myblogArticleId") != null) {
                DzOrLlCount = indexService.selectDzOrLl(Integer.valueOf(String.valueOf(map.get("myblogArticleId"))));
            }

            map.put("DzOrLlCount", DzOrLlCount);
        }
        return AjaxResult.success(ArticleList);
    }


    /**
     * 新建文章or更新文章
     */
    @RequestMapping(value = "/AddOrUpdateArticleor")
    public AjaxResult AddOrUpdateArticleor(Integer myblogArticleId, String myblogArticleTitle, String myblogArticleContents,String myblogArticleContentsMarkdown ) {
        //不带样式
        myblogArticle myblogArticle = new myblogArticle();
        if (StringUtils.isNotBlank(myblogArticleTitle) && StringUtils.isNotBlank(myblogArticleContents)) {
            //文章标题
            myblogArticle.setMyblogArticleTitle(myblogArticleTitle);

            //文章内容
            myblogArticle.setMyblogArticleContents(myblogArticleContents);

            //markdown格式
            myblogArticle.setMyblogArticleContentsMarkdown(myblogArticleContentsMarkdown);

            //标签id
            myblogArticle.setMyblogLabelId(1);
            //用户id
            myblogArticle.setMyblogUserId(1);

            myblogArticle.setDelFlag(0);

            if (myblogArticleId == null) {
                try {
                    //创建时间
                    myblogArticle.setMyblogArticleCreationtime(DateUtil.date());
                    indexDao.insert(myblogArticle);
                } catch (Exception e) {
                    return AjaxResult.fail("失败");
                }
            } else {
                myblogArticle.setMyblogArticleId(myblogArticleId);
                try {
                    indexDao.updateById(myblogArticle);
                } catch (Exception e) {
                    return AjaxResult.fail("失败");
                }
            }
        } else {
            return AjaxResult.fail("标题或内容不能为空");
        }
        return AjaxResult.success("成功");
    }

    /**
     * 查看文章
     */
    @RequestMapping(value = "/queryArticle")
    public AjaxResult queryArticle(Integer myblogArticleId) {
        myblogArticle list = null;

        if (myblogArticleId != null) {
            list = indexService.queryArticle(myblogArticleId);
        }
        return AjaxResult.success(list);

    }

    /**
     * 删除文章
     */
    @RequestMapping(value = "/deleteArticle")
    public AjaxResult deleteArticle(Integer myblogArticleId) {
        if (myblogArticleId != null) {
            indexService.deleteArticle(myblogArticleId);
        } else {
            return AjaxResult.fail("参数异常");
        }
        return AjaxResult.success("删除成功");
    }
}
