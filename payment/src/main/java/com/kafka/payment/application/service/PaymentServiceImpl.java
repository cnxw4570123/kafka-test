package com.kafka.payment.application.service;

import com.kafka.payment.application.command.request.PaymentCreateCommand;
import com.kafka.payment.domain.entity.Payment;
import com.kafka.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentEventProducer eventProducer;

    @Override
    @Transactional
    public void createPayment(PaymentCreateCommand paymentCreateCommand) {
        log.info("서비스 레이어 도착");
        Payment build = Payment.builder()
            .userEmail(paymentCreateCommand.userEmail())
            .productInfo(paymentCreateCommand.productInfo())
            .totalPrice(paymentCreateCommand.totalPrice())
            .build();

        Payment save = paymentRepository.save(build);

        // Order -> 이벤트 발행
        log.info("order로 이벤트 발행");
        eventProducer.sendPaymentCreatedEvent(paymentCreateCommand, save.getId());
    }
}
