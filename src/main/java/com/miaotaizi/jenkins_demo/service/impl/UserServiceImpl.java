package com.miaotaizi.jenkins_demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miaotaizi.jenkins_demo.entity.User;
import com.miaotaizi.jenkins_demo.service.UserService;
import com.miaotaizi.jenkins_demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Snowflying
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-01-17 17:34:28
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




