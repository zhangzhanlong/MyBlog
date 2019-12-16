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
            DzOrLlCount = indexService.selectDzOrLl(Integer.valueOf(String.valueOf(map.get("myblogArticleId"))));
            map.put("DzOrLlCount", DzOrLlCount);
        }
        return AjaxResult.success(ArticleList);
    }

    /**
     * 新建文章or更新文章
     */
    @RequestMapping(value = "/AddOrUpdateArticleor")
    public AjaxResult AddOrUpdateArticleor(Integer myblogArticleId,String myblogArticleTitle, String myblogArticleContents, String myblogArticleContentshtml) {
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

               if(myblogArticleId==null){
                   try {
                       //创建时间
                       myblogArticle.setMyblogArticleCreationtime(DateUtil.date());
                       myblogArticleHtml.setMyblogArticleCreationtime(DateUtil.date());
                       indexDao.insert(myblogArticle);
                       indexhtmlDao.insert(myblogArticleHtml);
                   } catch (Exception e) {
                       return AjaxResult.fail("失败");
                   }
               }else{
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
        if (myblogArticleId!=null) {
            indexService.deleteArticle(myblogArticleId);
        }else{
            return AjaxResult.fail("参数异常");
        }
        return AjaxResult.success("删除成功");
    }
/*
    *//**
     * 设为离职|批量设为离职
     *//*

    @ApiOperation(value = "通讯录组织架构设为离职", notes = "通讯录组织架构设为离职")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = true),
    })
    @RequestMapping(value = "/leave", method = POST, produces = "application/json")
    @ResponseBody
    public AjaxResult leave (@RequestParam(name = "ids", required = true) String ids) {
        if(ids==null&&ids.equals("")){
            return AjaxResult.success("参数异常");
        }
        String id[] = ids.split(",");

        ArrayList<Integer> a = new ArrayList<Integer>(id.length);
        try {
            for (String pid : id) {
                a.add(Integer.valueOf(pid));
            }

            djUserService.leave(a);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return AjaxResult.fail("参数异常");
        }
        return AjaxResult.success("ok");
    }

    *//**
     * 导出
     *//*
    @ApiOperation(value = "通讯录组织架构导出", notes = "通讯录组织架构导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "id数据", required = true),
    })
    @RequestMapping(value = "/exportExcle", method =GET, produces = "application/json")
    @ResponseBody
    public AjaxResult exportExcle(String ids, HttpServletResponse response) {
        List<Map<String, Object>> export = null;
        if (null != ids && !ids.equals("")) {
            String id[] = ids.split(",");
            ArrayList<Integer> b = new ArrayList<Integer>(id.length);

            for (String pid : id) {
                b.add(Integer.valueOf(pid));
            }
            export = djUserService.export(b);
        }
        if (null == ids || ids.equals("")) {
            return null;
        }
        Map<String, String> title = new LinkedHashMap<>();
        title.put("姓名", "userName");
        title.put("帐号", "userZh");
        title.put("性别", "userSex");
        title.put("部门", "deptName");
        title.put("手机号", "userSjh");
        title.put("邮箱", "userEmail");
        title.put("微信号", "userEx");
        title.put("员工生日", "userSr");
        title.put("入职日期", "userRzrq");

        EasyPoiUtil.DownloadExcel(response, "员工信息表", export, title);

        return AjaxResult.success("导出成功");


    }

    *//**
     * 导入
     *//*
    @ApiOperation(value = "通讯录组织架构导入", notes = "通讯录组织架构导入")
    @RequestMapping(value = "/importExcel", method = POST, produces = "application/json")
    @ResponseBody
    public AjaxResult importExcel(MultipartFile file) {
        ImportParams params = new ImportParams();
        DjUser djUser = new DjUser();
        String[] title = {"姓名", "帐号", "性别", "部门", "手机号码", "邮箱", "微信号", "员工生日", "入职日期"};
        params.setTitleRows(0);
        params.setHeadRows(1);
        params.setReadSingleCell(true);
        params.setImportFields(title);
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        try {
            maps = ExcelImportUtil.importExcel(file.getInputStream(), Map.class, params);
        } catch (ExcelImportException e) {
            e.printStackTrace();
            return AjaxResult.fail(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (maps.size() != 0) {
            for (Map<String, Object> map : maps) {
                if( map.get("姓名")!=null&&!map.get("姓名").equals("")){
                    String userName = map.get("姓名").toString();
                    if (userName != null && !userName.equals("")) {
                        djUser.setUserName(userName);
                    }
                }else{
                    return AjaxResult.fail("第" + (maps.indexOf(map) + 1) + "行,姓名不能为空");
                }
                if( map.get("帐号")!=null&&!map.get("帐号").equals("")){
                    String userZh = map.get("帐号").toString();
                    DjUser zh=djUserDao.selectOne(new QueryWrapper<DjUser>().eq("user_zh",userZh));
                    if(zh!=null){
                        return AjaxResult.fail("该帐号已存在");
                    }
                    if (userZh != null && !userZh.equals("")) {
                        djUser.setUserZh(userZh);
                    }
                }else{
                    return AjaxResult.fail("第" + (maps.indexOf(map) + 1) + "行,帐号不能为空");
                }
                djUserService.add(djUser);
            }
            return AjaxResult.success("添加成功");
        } else {
            return AjaxResult.fail("添加的数据为空");
        }
    }*/





}
