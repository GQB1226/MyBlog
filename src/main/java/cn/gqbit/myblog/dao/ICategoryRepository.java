package cn.gqbit.myblog.dao;

import cn.gqbit.myblog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository(value = "categoryRepository")
public interface ICategoryRepository extends JpaRepository<Category,Integer>,JpaSpecificationExecutor {
    Category findCategoryByCid(int cid);
    Category findCategoryByName(String name);

    Set<Category> findCategoryByNameOrAlias(String name, String alias);


    @Query("update Category c set c.name= ?1,c.alias= ?2 where c.cid= ?3")
    @Modifying
    int update(String name,String alias, int cid);
}
