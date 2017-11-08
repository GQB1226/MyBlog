package cn.gqbit.myblog.service.impl;

import cn.gqbit.myblog.dao.IUserRepository;
import cn.gqbit.myblog.entity.User;
import cn.gqbit.myblog.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Resource
    IUserRepository userRepository;

    @Override
    public User loadUserByName(String username) {
        return userRepository.findByUserName(username);
    }
}
