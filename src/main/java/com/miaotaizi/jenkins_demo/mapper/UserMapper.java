package com.miaotaizi.jenkins_demo.mapper;

import com.miaotaizi.jenkins_demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author Snowflying
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-01-17 17:34:28
* @Entity com.miaotaizi.jenkins_demo.entity.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

}




