package com.venkat.residency.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.venkat.residency.entity.Booking;
import com.venkat.residency.entity.Payment;
import com.venkat.residency.repository.PaymentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {
    
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;
    
    @Autowired
    private PaymentRepository paymentRepository;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    public PaymentIntent createPaymentIntent(Booking booking) throws StripeException {
        Long amount = booking.calculateTotalAmount().multiply(BigDecimal.valueOf(100)).longValue();
        
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount)
                .setCurrency("inr")
                .putMetadata("bookingId", booking.getId().toString())
                .build();

        return PaymentIntent.create(params);
    }

    public Payment savePayment(Booking booking, String paymentIntentId, String transactionId) {
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setPaymentId(paymentIntentId);
        payment.setTransactionId(transactionId);
        payment.setAmount(booking.calculateTotalAmount());
        payment.setPaymentStatus("SUCCEEDED");
        payment.setPaymentMethod("CARD");
        
        return paymentRepository.save(payment);
    }

    public boolean verifyPayment(String paymentIntentId) throws StripeException {
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
        return "succeeded".equals(paymentIntent.getStatus());
    }
}