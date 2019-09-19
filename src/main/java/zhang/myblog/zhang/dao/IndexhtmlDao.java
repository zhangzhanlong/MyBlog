package zhang.myblog.zhang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import zhang.myblog.zhang.entity.myblogArticleHtml;
@Mapper
@Repository
public interface IndexhtmlDao extends BaseMapper<myblogArticleHtml> {

}
