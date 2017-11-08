package cn.gqbit.myblog.service;

import cn.gqbit.myblog.entity.User;

public interface IUserService {
    User loadUserByName(String username);
}
