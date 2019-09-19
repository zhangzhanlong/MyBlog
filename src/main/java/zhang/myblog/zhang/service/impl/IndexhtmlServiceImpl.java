package zhang.myblog.zhang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhang.myblog.zhang.dao.IndexDao;
import zhang.myblog.zhang.dao.IndexhtmlDao;
import zhang.myblog.zhang.entity.myblogArticle;
import zhang.myblog.zhang.entity.myblogArticleHtml;
import zhang.myblog.zhang.service.IndexService;
import zhang.myblog.zhang.service.IndexhtmlService;

import java.util.List;
import java.util.Map;

@Service
public class IndexhtmlServiceImpl extends ServiceImpl<IndexhtmlDao, myblogArticleHtml> implements IndexhtmlService {

}
