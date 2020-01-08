package plus.knowing.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import plus.knowing.entity.BlogArticle;

@Repository
public interface BlogArticleDao extends BaseMapper<BlogArticle> {
}
