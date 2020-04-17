package zhang.myblog.zhang.controller;

import cn.hutool.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zhang.myblog.zhang.entity.myblogArticle;
import zhang.myblog.zhang.service.IndexService;
import zhang.myblog.zhang.util.FileInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class DefaultView {
    @Autowired
    IndexService indexService;
    /**
     * 上传图片
     * @param request
     * @param response
     * @param file
     */
    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${img.location}")
    private String folder;

    @ResponseBody
    @RequestMapping(value = "/file",method = RequestMethod.POST)
    public FileInfo upload(HttpServletRequest request, @RequestParam(value = "editormd-image-file", required = false) MultipartFile file) throws Exception {
        try {

            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String newFileName = new Date().getTime() + "." + suffix;

            File localFile = new File(folder, newFileName);
            String url=request.getRequestURL().substring(0,request.getRequestURL().lastIndexOf("/"))+"/upload/";
            File filePath = new File(folder);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            file.transferTo(localFile);
            return new FileInfo(1, "上传成功", url+newFileName);
        } catch (Exception e) {
            System.err.println(e);
            return new  FileInfo(0,"失败","");
        }
    }





    @RequestMapping("/")
    public String index() {
        return "index";
    }
    /**
     * 查看博客
     * @param myblogArticleId
     * @param modelMap
     * @return
     */
    @RequestMapping("/detail/{myblogArticleId}")
    public String detail(@PathVariable("myblogArticleId") int myblogArticleId, ModelMap modelMap) {
        modelMap.put("myblogArticleId",myblogArticleId);
        return "jie/detail";
    }
    /**
     * 新增博客
     * @return
     */
    @RequestMapping("/add")
    public String add() {
        return "jie/add";
    }

    /**
     * 编辑博客
     * @return
     */
    @GetMapping("/add/{myblogArticleId}")
    public String add(@PathVariable("myblogArticleId") int myblogArticleId, ModelMap modelMap) {
        modelMap.put("myblogArticleId",myblogArticleId);
        myblogArticle list = indexService.queryArticle(myblogArticleId);

        modelMap.put("list",list);
        return "jie/add";
    }


    public static void main(String[] args) {

    }

}