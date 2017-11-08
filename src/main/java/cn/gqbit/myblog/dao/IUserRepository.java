package cn.gqbit.myblog.dao;

import cn.gqbit.myblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository(value = "userRepository")
public interface IUserRepository extends JpaRepository<User,Integer> ,JpaSpecificationExecutor{
    User findByUserName(String username);
}
