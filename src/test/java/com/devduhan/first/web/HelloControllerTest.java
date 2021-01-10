package com.devduhan.first.web;

import com.devduhan.first.config.auth.SecurityConfig;
import com.devduhan.first.web.HelloController;
// org.junit.Test 에서 바뀜 < Junit4 임
import org.junit.jupiter.api.Test; // Junit 5
// junit5로 바뀌면서 @RunWith -> @ExtendWith로 바뀜
//import org.junit.runner.RunWith123;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is; // 자동임포트되지않아서 직접 입력했음
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실핼자를 실행시킴
// 여기서 실행자 " SpringRunner -> SpringExtension 으로 바뀜 (Junit5)
// 스프링 부트 테스트와 JUnit 사이에 연결자 역할
@ExtendWith(SpringExtension.class)

// 선언할 경우 @Controller, @ControllerAdvice 등을 사용 가능
// 단, @Service,@Component, @Repository 등은 사용 불가
// 여기서는 컨틀롤러만 사용하기 때문에 선언

@WebMvcTest(controllers = HelloController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
    }
)
public class HelloControllerTest {
    //  스프링이 관리하는 빈(Bean)을 주입받음
    @Autowired
    private MockMvc mvc; // 웹 API를 테스트 할 때 사용, 스프링 MVC 테스트의시작점

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MocMvc를 통해 /hello 주소로 HTTP GET 요청을 함
                .andExpect(status().isOk()) // mvc.perform의 결과 검증, HTTP Header의 status를 검증, OK 즉 200인지 아닌지검증
                .andExpect(content().string(hello)); // mvc.perform결과 점긍, 응답본문의 내용 검증, Controller에서 "hello" 를 반환 하는지 검증
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                    get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}
