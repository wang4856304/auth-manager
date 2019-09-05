package com.wj.user;

import com.wj.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jun.wang
 * @title: UserTest
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/4 15:03
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void findAllUser() throws Exception {
        mockMvc.perform(get("/api/user/findAllUser")
                .contentType(MediaType.TEXT_PLAIN_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
