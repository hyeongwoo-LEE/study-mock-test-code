package sample.cafekiosk.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.service.mail.MailService;
import sample.cafekiosk.spring.domain.Order.Order;
import sample.cafekiosk.spring.domain.Order.OrderRepository;
import sample.cafekiosk.spring.domain.Order.OrderStatus;

import java.time.LocalDate;
import java.util.List;

import static java.lang.String.format;

// 메일 전송하는 로직에는 @Transcation을 안붙히는게 좋다. -> DB 조회 시 커넥션 자원 낭비
@RequiredArgsConstructor
@Service
public class OrderStaticsService {

    private final OrderRepository orderRepository;
    private final MailService mailService;

    public boolean sendOrderStatisticsMail(LocalDate orderDate, String email) {

        // 해당 일자에 결제완료된 주문들을 가져와서
        List<Order> orders = orderRepository.findOrdersBy(
                orderDate.atStartOfDay(),
                orderDate.plusDays(1).atStartOfDay(),
                OrderStatus.COMPLETED
        );

        // 총 매출 합계를 계산하고
        int totalAmount = orders.stream()
                .mapToInt(Order::getTotalPrice)
                .sum();

        // 메일 전송
        boolean result = mailService.sendMail(
                "no-reply@cafekiosk.com",
                email,
                format("[매출통계] %s", orderDate),
                format("총 매출 합계는 %s원입니다.", totalAmount));
        if (!result) {
            throw new IllegalArgumentException("매출 통계 메일 전송에 실패했습니다.");
        }
        return true;
    }

}
