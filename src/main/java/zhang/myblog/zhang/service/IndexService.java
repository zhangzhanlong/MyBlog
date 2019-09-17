package zhang.myblog.zhang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zhang.myblog.zhang.entity.myblogArticle;

import java.util.List;
import java.util.Map;

public interface IndexService extends IService<myblogArticle> {
    List<Map<String, Object>> queryAll();

}
