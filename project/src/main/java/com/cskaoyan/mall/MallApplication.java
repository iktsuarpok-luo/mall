package com.cskaoyan.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
<<<<<<< HEAD
=======
<<<<<<< HEAD
//mapper的扫描包
=======
>>>>>>> 0afb6ebfa0be1866977b0b86766121d10dc59c8d
>>>>>>> 3d278f281c72bfca69d7cd3e52bbb3dd6c2ea2ba
@MapperScan("com.cskaoyan.mall.mapper")
public class MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

}
