package cn.gqbit.myblog.service;

import cn.gqbit.myblog.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryService extends IBaseService{

    Page<Category> findTagNoCriteria(Integer page, Integer size);
    List<Category> listAllCategory();
    Category loadCategoryByCid(int cid);
    Category loadCategoryByCname(String cname);
    void save(Category category);
    boolean checkExit(Category category);
    void update(Category category);
}
