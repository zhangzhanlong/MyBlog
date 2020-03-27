package zhang.myblog.zhang.controller;

import cn.hutool.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class DefaultView {


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


    public static void main(String[] args) {

    }

}