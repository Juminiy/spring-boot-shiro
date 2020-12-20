package com.hua.shirospringboot;

import com.hua.shirospringboot.mapper.UserMapper;
import com.hua.shirospringboot.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringBootApplicationTests {

    @Autowired
    UserServiceImpl userService ;

    @Test
    void contextLoads() {
        System.out.println(userService.getUserByName("user1"));
    }

}
