package zhang.myblog.zhang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zhang.myblog.zhang.entity.myblogArticle;
import zhang.myblog.zhang.entity.myblogArticleHtml;
import java.util.List;
import java.util.Map;

public interface IndexhtmlService extends IService<myblogArticleHtml> {

    List<Map<String, Object>> queryArticle(Integer myblogArticleId);

}
