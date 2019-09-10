package zhang.myblog.zhang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import zhang.myblog.zhang.entity.myblogArticle;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface IndexDao extends BaseMapper<myblogArticle> {
    List<Map<String, Object>> queryAll();
}
