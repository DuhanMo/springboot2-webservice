package com.devduhan.first;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스프링부트의 자동 설정, 스프링 Bean 읽기와 생성 모두 자동 설정
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
//        run 메소드로 내장 WAS를 실행
        SpringApplication.run(Application.class, args);
    }
}
