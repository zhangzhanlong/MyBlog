package zhang.myblog.zhang.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class DefaultView  {
@RequestMapping("/")
    public String  index(){
    return "index";
}

}