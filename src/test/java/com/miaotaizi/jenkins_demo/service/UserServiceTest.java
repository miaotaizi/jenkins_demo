package com.miaotaizi.jenkins_demo.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.javafaker.Faker;
import com.miaotaizi.jenkins_demo.entity.User;
import com.miaotaizi.jenkins_demo.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServiceTest {

    Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    private Faker faker;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    private void setFaker(){
        faker = new Faker(new Locale("zh-CN"));
    }

    @BeforeEach
    private void setUp() throws NoSuchAlgorithmException {
        this.setFaker();
        this.fakerUsers();
    }

    private void fakerUsers() throws NoSuchAlgorithmException {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setName(faker.name().fullName());
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setPasswordHash(SecureUtil.md5(RandomUtil.randomString(16)));
            users.add(user);
        }
        this.userService.saveBatch(users);
    }



    @Test
    public void testUserService() {
        User user = new User();
        user.setName("sss");
        user.setPhone("123");
        user.setPasswordHash("123");
        userService.save(user);
        assertNotNull(user.getId());
    }

    @Test
    public void testUserEquals() {
        User user = new User();
        user.setName("sss");
        user.setPhone("123");
        user.setPasswordHash("123");
        userService.save(user);

        User saved = userService.getById(user.getId());
        assertEquals(true, user.equals(saved));
    }

    @Test
    public void testUserDelete() {
        User user = new User();
        user.setName("sss");
        user.setPhone("123");
        user.setPasswordHash("123");
        userService.save(user);

        userService.removeById(user.getId());

        User deleted = userService.getById(user.getId());
        assertNull(deleted);
    }

    @Test
    public void testListQuery() {
        List<User> list = this.userService.list();
        assertEquals(list.stream().count(), 20);
        Page<User> userIPage = new Page<>(1,  5);
        this.userService.page(userIPage);
        logger.info("总条数 -------------> {}", userIPage.getTotal());
        logger.info("当前页数 -------------> {}", userIPage.getCurrent());
        logger.info("当前每页显示数 -------------> {}", userIPage.getSize());
        List<User> pageList =  userIPage.getRecords();

        assertEquals(pageList.stream().count(), 5);

        logger.info("----------------------------------json 正反序列化-------------------------------------------------------");
        String json = JSONUtil.toJsonStr(userIPage);
        logger.info("json ----------> {}", json);
    }

    @Test
    public void testUpdate(){
        Integer userId = 1;
        User firstUser = userService.getById(userId);
        logger.info("--------- 用户信息 -----------");
        logger.info("{}", JSONUtil.toJsonStr(firstUser));
        String newName = faker.name().fullName();
        logger.info("用户新名称: ---------- {}", newName);
        firstUser.setName(newName);
        userService.updateById(firstUser);
        User updatedUser = userService.getById(userId);
        logger.info("--------- 更新后用户信息 -----------");
        logger.info("{}", JSONUtil.toJsonStr(updatedUser));
        assertEquals(firstUser.getId(), updatedUser.getId());
        assertEquals(newName, updatedUser.getName());
        assertEquals(firstUser.getUpdatedAt(), updatedUser.getUpdatedAt());
    }

    @Test
    public void testBatchUpdate() {
        Page<User> firstPageUsers = new Page<>(1, 5);
        userService.page(firstPageUsers);
        List<Object> jsonList = new ArrayList<>();
        List<User> list = firstPageUsers.getRecords();
        list.forEach(user -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("name", user.getName());
            jsonList.add(map);
            user.setName( user.getName() + "-" + faker.name().fullName());
        });
        logger.info("--------- 用户列表 -----------");
        logger.info("{}", JSONUtil.toJsonStr(jsonList));
        userService.updateBatchById(list);
        Page<User> updatedUsers = new Page<>(1, 5);
        userService.page(updatedUsers);
        List<Object> updatedData = new ArrayList<>();
        List<User> updatedList = updatedUsers.getRecords();
        updatedList.forEach(user -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("name", user.getName());
            updatedData.add(map);
        });
        logger.info("--------- 更新过的用户列表 -----------");
        logger.info("{}", JSONUtil.toJsonStr(updatedData));

    }
}