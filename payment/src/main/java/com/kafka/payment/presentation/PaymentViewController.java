package com.kafka.payment.presentation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequestMapping("/api/payment")
public class PaymentViewController {

    @GetMapping("/checkout")
    public String createPayment(@RequestParam(name = "paymentId") Long paymentId) {
        return "checkout";
    }

    @GetMapping("/success")
    public String confirmPayment() {
        return "success";
    }
}
