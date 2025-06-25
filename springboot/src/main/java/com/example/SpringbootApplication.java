package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//项目中缺少JPA 所需的 jakarta.persistence 相关依赖，
//但 Spring Boot 自动配置中引入了与 JPA 相关的类（比如 SharedEntityManagerCreator），从而导致了异常。
//暂时选择禁用自动加载 JPA 和数据源相关的类
//(exclude = {
//        org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class,
//        org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
//})

@SpringBootApplication
public class SpringbootApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // ✅ 允许任意来源（安全地配合 allowCredentials）
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowedOrigins("http://localhost:5173");
    }




}
