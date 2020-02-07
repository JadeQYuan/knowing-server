package plus.knowing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import plus.knowing.dao.BlogTagDao;
import plus.knowing.entity.BlogTag;
import plus.knowing.service.ITagService;
import plus.knowing.vo.generic.PageVO;
import plus.knowing.vo.blog.TagQueryVO;
import plus.knowing.vo.blog.TagVO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TagService implements ITagService {

    @Autowired
    private BlogTagDao blogTagDao;

    @Override
    public void addTag(TagVO tagVO) {
        BlogTag tag = new BlogTag();
        tag.setName(tagVO.getName());
        tag.setIntro(tagVO.getDescription());
        blogTagDao.insert(tag);
    }

    @Override
    public List<TagVO> listTags(TagVO tagVO) {
        QueryWrapper<BlogTag> tagQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(tagVO.getName())) {
            tagQueryWrapper.like("name", tagVO.getName());
        }
        List<BlogTag> tagList = blogTagDao.selectList(tagQueryWrapper);
        return tagList.stream().map(TagVO::new).collect(Collectors.toList());
    }

    @Override
    public PageVO<TagVO> pagingListTags(TagQueryVO queryVO) {
        QueryWrapper<BlogTag> tagQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(queryVO.getName())) {
            tagQueryWrapper.like("name", queryVO.getName());
        }
        IPage<BlogTag> page = blogTagDao.selectPage(new Page<>(queryVO.getPageNum(), queryVO.getPageSize()), tagQueryWrapper);
        List<TagVO> voList = page.getRecords().stream().map(TagVO::new).collect(Collectors.toList());
        return new PageVO<>(page, voList);
    }

    @Override
    public TagVO get(Long id) {
        BlogTag tag = blogTagDao.selectById(id);
        return Objects.isNull(tag) ? null : new TagVO(tag);
    }

    @Override
    public void update(Long id, TagVO tagVO) {
        BlogTag tag = blogTagDao.selectById(id);
        if (Objects.isNull(tag)) {
            return;
        }
        boolean flag = false;
        if (!Objects.equals(tag.getName(), tagVO.getName())) {
            tag.setName(tagVO.getName());
            flag = true;
        }
        if (!Objects.equals(tag.getIntro(), tagVO.getDescription())) {
            tag.setIntro(tagVO.getDescription());
            flag = true;
        }
        if (flag) {
            blogTagDao.updateById(tag);
        }
    }
}
