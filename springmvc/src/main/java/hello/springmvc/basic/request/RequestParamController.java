package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // @RestController 대신 사용 가능하다.
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);

        return "ok";
    }

    @ResponseBody // @RestController 대신 사용 가능하다.
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, // 파라미터와 변수가 같으면 @RequestParam("username")이 생략 가능하다.
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) { // 파라미터와 변수가 같고 String 등의 단순 타입이면 @RequestParam 도 생략 가능 @RequestParam이 생략 가능하다.

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                 @RequestParam(required = false)Integer age) { // 파라미터와 변수가 같고 String 등의 단순 타입이면 @RequestParam 도 생략 가능 @RequestParam이 생략 가능하다.

        log.info("username={}, age={}", username, age);

        return "ok";
    }
// @RequestParam(required=false)는 값이 없으면 null을 저장한다.

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username,
                                       @RequestParam(required = true, defaultValue = "-1")int age) { // 파라미터와 변수가 같고 String 등의 단순 타입이면 @RequestParam 도 생략 가능 @RequestParam이 생략 가능하다.

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
//        log.info("hellodata={}", helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    // @ModelAttribute는 객체에 변수가 선언되어 있기 때문에 기본값이 저장되어 있다. String=null, Int = 0
    // @RequestParam은 요청 파라미터로 값을 받기 때문에 모든 값이 null로 저장된다. -> 오류

    // 위 두 개는 요청 파라미터, @RequestBody는 HTTP 메시지 바디의 내용이다.
}
