package zhang.myblog.zhang.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.exception.excel.ExcelImportException;
import cn.hutool.core.date.DateUtil;
import com.sun.javafx.collections.MappingChange;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import zhang.myblog.MyblogApplication;
import zhang.myblog.zhang.dao.IndexDao;
import zhang.myblog.zhang.dao.IndexhtmlDao;
import zhang.myblog.zhang.entity.myblogArticle;
import zhang.myblog.zhang.entity.myblogArticleHtml;
import zhang.myblog.zhang.service.IndexService;
import zhang.myblog.zhang.service.IndexhtmlService;
import zhang.myblog.zhang.util.AjaxResult;
import zhang.myblog.zhang.util.EasyPoiUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    @Autowired
    IndexhtmlService indexhtmlService;

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
    public AjaxResult AddOrUpdateArticleor(Integer myblogArticleId, String myblogArticleTitle, String myblogArticleContents, String myblogArticleContentshtml) {
        //不带样式
        myblogArticle myblogArticle = new myblogArticle();
        //带html样式
        myblogArticleHtml myblogArticleHtml = new myblogArticleHtml();
        if (StringUtils.isNotBlank(myblogArticleTitle) && StringUtils.isNotBlank(myblogArticleContents)) {
            //文章标题
            myblogArticle.setMyblogArticleTitle(myblogArticleTitle);
            myblogArticleHtml.setMyblogArticleTitle(myblogArticleTitle);
            //文章内容
            myblogArticle.setMyblogArticleContents(myblogArticleContents);
            myblogArticleHtml.setMyblogArticleContents(myblogArticleContentshtml);

            //标签id
            myblogArticle.setMyblogLabelId(1);
            myblogArticleHtml.setMyblogLabelId(1);
            //用户id
            myblogArticle.setMyblogUserId(1);
            myblogArticleHtml.setMyblogUserId(1);

            myblogArticle.setDelFlag(0);
            myblogArticleHtml.setDelFlag(0);

            if (myblogArticleId == null) {
                try {
                    //创建时间
                    myblogArticle.setMyblogArticleCreationtime(DateUtil.date());
                    myblogArticleHtml.setMyblogArticleCreationtime(DateUtil.date());
                    indexDao.insert(myblogArticle);
                    indexhtmlDao.insert(myblogArticleHtml);
                } catch (Exception e) {
                    return AjaxResult.fail("失败");
                }
            } else {
                myblogArticle.setMyblogArticleId(myblogArticleId);
                myblogArticleHtml.setMyblogArticleId(myblogArticleId);
                try {
                    indexDao.updateById(myblogArticle);
                    indexhtmlDao.updateById(myblogArticleHtml);
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
        List<Map<String, Object>> list = null;

        if (myblogArticleId != null) {
            list = indexhtmlService.queryArticle(myblogArticleId);
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
