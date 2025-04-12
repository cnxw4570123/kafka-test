package com.kafka.payment.application.service;

import com.kafka.payment.application.dto.command.PaymentCreateCommand;
import com.kafka.payment.application.dto.query.FindPaymentQuery;
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
        Payment build = paymentCreateCommand.toPayment();

        Payment save = paymentRepository.save(build);

        // Order -> 이벤트 발행
        log.info("order로 이벤트 발행");
        eventProducer.sendPaymentCreatedEvent(paymentCreateCommand, save.getId());
    }

    @Override
    public Long savePayment(PaymentCreateCommand paymentCreateCommand) {
        log.info("토스페이 결제 전 결제 정보 저장");
        Payment save = paymentRepository.save(paymentCreateCommand.toPayment());
        return save.getId();
    }

    @Override
    public FindPaymentQuery findPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new RuntimeException("not found"));

        return FindPaymentQuery.of(payment);
    }

}
