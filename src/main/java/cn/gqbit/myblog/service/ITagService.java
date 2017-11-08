package cn.gqbit.myblog.service;

import cn.gqbit.myblog.entity.Tag;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITagService extends IBaseService{

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Tag> findTagNoCriteria(Integer page, Integer size);

    /**
     * 列举所有标签
     * @return
     */
    List<Tag> listAllTag();

    /**
     * 根据id查询
     * @param tid
     * @return
     */
    Tag loadTagByTid(int tid);

    /**
     * 根据name查找
     * @param tname
     * @return
     */
    Tag loadTagByTname(String tname);

    /**
     * 保存
     * @param tag
     */
    void save(Tag tag);
}
