package plus.knowing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import plus.knowing.dao.BlogTagCategoryDao;
import plus.knowing.dao.BlogTagDao;
import plus.knowing.entity.BlogTag;
import plus.knowing.entity.BlogTagCategory;
import plus.knowing.service.ITagService;
import plus.knowing.vo.blog.TagCategoryVO;
import plus.knowing.vo.blog.TagVO;
import plus.knowing.vo.generic.TreeNodeVO;
import plus.knowing.vo.sys.UserVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TagService implements ITagService {

    @Autowired
    private BlogTagCategoryDao blogTagCategoryDao;

    @Autowired
    private BlogTagDao blogTagDao;

    @Override
    public void addCategory(TagCategoryVO categoryVO, UserVO userVO) {
        BlogTagCategory blogTagCategory = new BlogTagCategory(categoryVO);
        blogTagCategory.setCreateTime(LocalDateTime.now());
        blogTagCategory.setCreateUserId(userVO.getId());
        blogTagCategoryDao.insert(blogTagCategory);
    }

    @Override
    public TagCategoryVO getCategory(Long id) {
        BlogTagCategory blogTagCategory = blogTagCategoryDao.selectById(id);
        return new TagCategoryVO(blogTagCategory);
    }

    @Override
    public void updateCategory(Long id, TagCategoryVO tagCategoryVO) {
        BlogTagCategory blogTagCategory = blogTagCategoryDao.selectById(id);
        blogTagCategory.setName(tagCategoryVO.getName());
        blogTagCategory.setShared(tagCategoryVO.getShared());
        blogTagCategoryDao.updateById(blogTagCategory);
    }

    @Override
    public List<TagCategoryVO> listCategory() {
        List<BlogTagCategory> blogTagCategories = blogTagCategoryDao.selectList(null);
        return blogTagCategories.parallelStream().map(TagCategoryVO::new).collect(Collectors.toList());
    }

    @Override
    public void addTag(TagVO tagVO, UserVO userVO) {
        BlogTag tag = new BlogTag(tagVO);
        tag.setCreateTime(LocalDateTime.now());
        tag.setCreateUserId(userVO.getId());
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
    public List<TagVO> listPopularTags() {
        QueryWrapper<BlogTag> tagQueryWrapper = new QueryWrapper<>();
        tagQueryWrapper.last(" limit 10 ");
        List<BlogTag> tagList = blogTagDao.selectList(tagQueryWrapper);
        return tagList.stream().map(TagVO::new).collect(Collectors.toList());
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
        if (!Objects.equals(tag.getIntro(), tagVO.getIntro())) {
            tag.setIntro(tagVO.getIntro());
            flag = true;
        }
        if (!Objects.equals(tag.getCategoryId(), tagVO.getCategoryId())) {
            tag.setCategoryId(tagVO.getCategoryId());
            flag = true;
        }
        if (flag) {
            blogTagDao.updateById(tag);
        }
    }

    @Override
    public List<TreeNodeVO<?>> listTagTree() {
        List<TreeNodeVO<?>> tree = new ArrayList<>();
        List<BlogTagCategory> blogTagCategories = blogTagCategoryDao.selectList(null);
        List<BlogTag> blogTags = blogTagDao.selectList(null);
        Map<Long, List<BlogTag>> listMap = blogTags.parallelStream().collect(Collectors.groupingBy(BlogTag::getCategoryId));
        blogTagCategories.forEach(blogTagCategory -> {
            TreeNodeVO<BlogTagCategory> categoryNode = new TreeNodeVO<>(blogTagCategory.getId().toString(),
                    TreeNodeVO.TypeEnum.TagCategory, blogTagCategory);
            List<BlogTag> tagList = listMap.get(blogTagCategory.getId());
            if (!CollectionUtils.isEmpty(tagList)) {
                tagList.forEach(tag -> {
                    TreeNodeVO<BlogTag> tagNode = new TreeNodeVO<>(tag.getId().toString(), TreeNodeVO.TypeEnum.Tag, tag);
                    categoryNode.addChild(tagNode);
                });
            }
            tree.add(categoryNode);
        });
        List<BlogTag> tagList = listMap.get(0L);
        if (!CollectionUtils.isEmpty(tagList)) {
            TreeNodeVO<String> categoryNode = new TreeNodeVO<>("", TreeNodeVO.TypeEnum.TagCategory, "未分类");
            tagList.forEach(tag -> {
                TreeNodeVO<BlogTag> tagNode = new TreeNodeVO<>(tag.getId().toString(), TreeNodeVO.TypeEnum.Tag, tag);
                categoryNode.addChild(tagNode);
            });
            tree.add(categoryNode);
        }
        return tree;
    }
}
