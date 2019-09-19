package zhang.myblog.zhang.controller;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zhang.myblog.MyblogApplication;
import zhang.myblog.zhang.dao.IndexDao;
import zhang.myblog.zhang.dao.IndexhtmlDao;
import zhang.myblog.zhang.entity.myblogArticle;
import zhang.myblog.zhang.entity.myblogArticleHtml;
import zhang.myblog.zhang.service.IndexService;
import zhang.myblog.zhang.util.AjaxResult;

import java.util.List;
import java.util.Map;
@Controller
@ResponseBody
@RequestMapping("/index")
public class IndexController {
    @Autowired
    IndexService indexService;
    @Autowired
    IndexDao indexDao;
    @Autowired
    IndexhtmlDao indexhtmlDao;
    /**
     * 查询全部文章
     * @return
     */
    @RequestMapping(value = "/selectArticle")
    public AjaxResult selectArticle() {
        List<Map<String, Object>> DzOrLlCount=null;
        List<Map<String, Object>> ArticleList = indexService.queryAll();
        for(Map map:ArticleList){
            DzOrLlCount = indexService.selectDzOrLl(Integer.valueOf(String.valueOf(map.get("myblogArticleId"))));
            map.put("DzOrLlCount",DzOrLlCount);
        }
        return AjaxResult.success(ArticleList);
    }
    /**
     *新建文章
     */
    @RequestMapping(value = "/addArticle")
    public AjaxResult addArticle(String myblogArticleTitle,String myblogArticleContents,String myblogArticleContentshtml) {
        //不带样式
        myblogArticle myblogArticle=new myblogArticle();
        //带html样式
        myblogArticleHtml myblogArticleHtml=new myblogArticleHtml();
        if(StringUtils.isNotBlank(myblogArticleTitle)&&StringUtils.isNotBlank(myblogArticleContents)){
            //文章标题
            myblogArticle.setMyblogArticleTitle(myblogArticleTitle);
            myblogArticleHtml.setMyblogArticleTitle(myblogArticleTitle);
            //文章内容
            myblogArticle.setMyblogArticleContents(myblogArticleContents);
            myblogArticleHtml.setMyblogArticleContents(myblogArticleContentshtml);
            //创建时间
            myblogArticle.setMyblogArticleCreationtime(DateUtil.date());
            myblogArticleHtml.setMyblogArticleCreationtime(DateUtil.date());
            //标签id
            myblogArticle.setMyblogLabelId(1);
            myblogArticleHtml.setMyblogLabelId(1);
            //用户id
            myblogArticle.setMyblogUserId(1);
            myblogArticleHtml.setMyblogUserId(1);

            myblogArticle.setDelFlag(0);
            myblogArticleHtml.setDelFlag(0);
            try {
                indexDao.insert(myblogArticle);
                indexhtmlDao.insert(myblogArticleHtml);
            } catch (Exception e) {
                return AjaxResult.fail("失败");
            }
        }else{
            return AjaxResult.fail("标题或内容不能为空");
        }

        return AjaxResult.success("成功");
    }

}
