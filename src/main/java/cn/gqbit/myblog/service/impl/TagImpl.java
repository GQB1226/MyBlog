package cn.gqbit.myblog.service.impl;

import cn.gqbit.myblog.dao.ITagRepository;
import cn.gqbit.myblog.entity.Tag;
import cn.gqbit.myblog.service.ITagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service(value = "tagService")
@Transactional
public class TagImpl implements ITagService {

    @Resource
    private ITagRepository tagRepository;

    @Override
    public int getTotal() {
        Long count = tagRepository.count();
        return count.intValue();
    }

    @Override
    public Page<Tag> findTagNoCriteria(Integer page, Integer size) {
        Pageable pageable=new PageRequest(page,size, Sort.Direction.DESC,"id");
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listAllTag() {
        return tagRepository.findAll();
    }

    @Override
    public Tag loadTagByTid(int tid) {
        return tagRepository.findTagByTid(tid);
    }

    @Override
    public Tag loadTagByTname(String tname) {
        return tagRepository.findTagByTname(tname);
    }

    @Override
    public void save(Tag tag) {
        tagRepository.save(tag);
    }
}
