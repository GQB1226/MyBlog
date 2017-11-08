package cn.gqbit.myblog.dao;

import cn.gqbit.myblog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository(value = "tagRepository")
public interface ITagRepository extends JpaRepository<Tag,Integer>,JpaSpecificationExecutor {

    Tag findTagByTid(int tid);
    Tag findTagByTname(String tname);
}
