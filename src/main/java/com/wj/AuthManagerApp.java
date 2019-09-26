package com.wj;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author jun.wang
 * @title: AuthManagerApp
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/4 11:43
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableDistributedTransaction
public class AuthManagerApp {

    public static void main(String args[]) {
        SpringApplication.run(AuthManagerApp.class, args);
    }
}
