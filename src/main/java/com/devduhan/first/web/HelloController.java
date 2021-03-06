package com.devduhan.first.web;

import com.devduhan.first.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌
// 메소드마다 @ResponseBody를 각 메소드마다 선언했던 것을 한번에 해준다.
@RestController
public class HelloController {
    //    HTTP Method 인 Get의 요청을 받을 수 있는 API 를 만들어준다.
//    예전에는 @RequestMapping(method...)로 사용되었음
//    이제 이 프로젝트는 /hello요청이 오면 문자열 hello를 반환하는 기능
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    //@RequsetParam
    //외부에서 API로 넘긴 파라미터를 가져오는 어노테테이션
    //외부에서 name(@RequestParam("name"))이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}

