package cn.gqbit.myblog.service.impl;

import cn.gqbit.myblog.dao.ICategoryRepository;
import cn.gqbit.myblog.entity.Category;
import cn.gqbit.myblog.service.ICategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service(value = "categoryService")
@Transactional
public class CategoryImpl implements ICategoryService {
    @Resource
    private ICategoryRepository categoryRepository;

    @Override
    public int getTotal() {
        Long count=categoryRepository.count();
        return count.intValue();
    }

    @Override
    public Page<Category> findTagNoCriteria(Integer page, Integer size) {
        Pageable pageable=new PageRequest(page,size, Sort.Direction.ASC,"cid");
        return categoryRepository.findAll(pageable);
    }

    @Override
    public List<Category> listAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category loadCategoryByCid(int cid) {
        return categoryRepository.findCategoryByCid(cid);
    }

    @Override
    public Category loadCategoryByCname(String cname) {
        return categoryRepository.findCategoryByName(cname);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public boolean checkExit(Category category) {
        if(category==null)return true;
        Set<Category> tmp=categoryRepository.findCategoryByNameOrAlias(category.getName(),category.getAlias());
        if (tmp!=null && tmp.size()>=1){
            return true;
        }
        return false;
    }

    @Override
    public void update(Category category) {
        if(category!=null){
            System.out.println(category.getCid());
            categoryRepository.update(category.getName(),category.getAlias(),category.getCid());
        }
    }
}
