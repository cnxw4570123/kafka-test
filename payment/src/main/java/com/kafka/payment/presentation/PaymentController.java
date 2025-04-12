package com.kafka.payment.presentation;

import com.kafka.payment.application.service.PaymentService;
import com.kafka.payment.presentation.dto.request.PaymentCreateRequest;
import com.kafka.payment.presentation.dto.response.FindPaymentResponse;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public String processPayment(@RequestBody PaymentCreateRequest paymentCreateRequest) {
        // TODO : 주문 정보 저장
        Long paymentId = paymentService.savePayment(paymentCreateRequest.toPaymentCreateCommand());
        return "redirect:/api/payment/checkout?paymentId=" + paymentId;
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<FindPaymentResponse> findPayment(@PathVariable Long paymentId) {
        return ResponseEntity.ok(FindPaymentResponse.of(paymentService.findPayment(paymentId)));
    }

    @PostMapping("/confirm")
    public ResponseEntity<Void> confirmPayment(
        @RequestBody Map<String, String> params
    ) {
        log.info("응답 = {}", params);
        // TODO: TOSSPAY로 결제 요청
        return ResponseEntity.ok(null);
    }
}
