package zhang.myblog.zhang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhang.myblog.zhang.dao.IndexDao;
import zhang.myblog.zhang.entity.myblogArticle;
import zhang.myblog.zhang.service.IndexService;

import java.util.List;
import java.util.Map;

@Service
public class IndexServiceImpl extends ServiceImpl<IndexDao, myblogArticle> implements IndexService {
@Autowired
IndexDao indexDao;
    @Override
    public List<Map<String, Object>> queryAll(Map<String, Object> param) {
        return indexDao.queryAll(param);
    }

    @Override
    public List<Map<String, Object>> selectDzOrLl(Integer myblogArticleId) {
        return indexDao.selectDzOrLl(myblogArticleId);
    }

    @Override
    public void deleteArticle(Integer myblogArticleId) {
       indexDao.deleteArticle(myblogArticleId);
    }
}
