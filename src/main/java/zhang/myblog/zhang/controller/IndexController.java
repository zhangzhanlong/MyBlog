package zhang.myblog.zhang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    /**
     * 查询全部文章
     * @return
     */
    @RequestMapping(value = "/selectArticle")
    public AjaxResult selectArticle() {
        List<Map<String, Object>> ArticleList = indexService.queryAll();
        return AjaxResult.success(ArticleList);
    }

}
