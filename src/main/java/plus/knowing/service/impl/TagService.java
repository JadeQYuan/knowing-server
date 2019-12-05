package plus.knowing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import plus.knowing.dao.TagDao;
import plus.knowing.entity.Tag;
import plus.knowing.service.ITagService;
import plus.knowing.vo.PageVO;
import plus.knowing.vo.TagQueryVO;
import plus.knowing.vo.TagVO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TagService implements ITagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public void addTag(TagVO tagVO) {
        Tag tag = new Tag();
        tag.setName(tagVO.getName());
        tag.setDescription(tagVO.getDescription());
        tagDao.insert(tag);
    }

    @Override
    public List<TagVO> listTags(TagVO tagVO) {
        QueryWrapper<Tag> tagQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(tagVO.getName())) {
            tagQueryWrapper.like("name", tagVO.getName());
        }
        List<Tag> tagList = tagDao.selectList(tagQueryWrapper);
        return tagList.stream().map(TagVO::new).collect(Collectors.toList());
    }

    @Override
    public PageVO<TagVO> pagingListTags(TagQueryVO queryVO) {
        QueryWrapper<Tag> tagQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(queryVO.getName())) {
            tagQueryWrapper.like("name", queryVO.getName());
        }
        IPage<Tag> page = tagDao.selectPage(new Page<>(queryVO.getPageNum(), queryVO.getPageSize()), tagQueryWrapper);
        List<TagVO> voList = page.getRecords().stream().map(TagVO::new).collect(Collectors.toList());
        return new PageVO<>(page, voList);
    }

    @Override
    public TagVO get(Long id) {
        Tag tag = tagDao.selectById(id);
        return Objects.isNull(tag) ? null : new TagVO(tag);
    }

    @Override
    public void update(Long id, TagVO tagVO) {
        Tag tag = tagDao.selectById(id);
        if (Objects.isNull(tag)) {
            return;
        }
        boolean flag = false;
        if (!Objects.equals(tag.getName(), tagVO.getName())) {
            tag.setName(tagVO.getName());
            flag = true;
        }
        if (!Objects.equals(tag.getDescription(), tagVO.getDescription())) {
            tag.setDescription(tagVO.getDescription());
            flag = true;
        }
        if (flag) {
            tagDao.updateById(tag);
        }
    }
}
