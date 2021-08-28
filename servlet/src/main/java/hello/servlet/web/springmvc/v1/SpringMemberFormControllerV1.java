package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // Component 스캔을 할 때 자동으로 스프링 빈에 등록된다.
// 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식한다.
// @Controller가 붙어있으면 HandlerMapping이 맵핑 정보로 인식한다.put(uri, new SpringMemberFormControllerV1()), get(uri) 가능
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
