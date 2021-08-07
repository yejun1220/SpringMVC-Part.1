package hello.core;

import hello.core.AppConfig;
import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
        //         AppConfig appConfig = new AppConfig();
        //         MemberService memberService = appConfig.memberService();
        //         OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigReactiveWebApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);



        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("member = " + order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());
    }
}
