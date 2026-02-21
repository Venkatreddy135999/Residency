package com.venkat.residency.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.venkat.residency.entity.Booking;
import com.venkat.residency.service.BookingService;
import com.venkat.residency.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private BookingService bookingService;
    
    @Value("${stripe.public.key}")
    private String stripePublicKey;

    @GetMapping("/{bookingId}")
    public String showPaymentPage(@PathVariable Long bookingId, Model model) {
        Optional<Booking> bookingOpt = bookingService.getBookingById(bookingId);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            try {
                PaymentIntent paymentIntent = paymentService.createPaymentIntent(booking);
                model.addAttribute("booking", booking);
                model.addAttribute("clientSecret", paymentIntent.getClientSecret());
                model.addAttribute("stripePublicKey", stripePublicKey);
                return "payments/payment";
            } catch (StripeException e) {
                model.addAttribute("error", "Payment processing error: " + e.getMessage());
                return "error";
            }
        }
        return "redirect:/bookings";
    }

    @PostMapping("/confirm/{bookingId}")
    public String confirmPayment(@PathVariable Long bookingId, 
                               @RequestParam String paymentIntentId,
                               Model model) {
        Optional<Booking> bookingOpt = bookingService.getBookingById(bookingId);
        if (bookingOpt.isPresent()) {
            try {
                if (paymentService.verifyPayment(paymentIntentId)) {
                    Booking booking = bookingOpt.get();
                    paymentService.savePayment(booking, paymentIntentId, "txn_" + System.currentTimeMillis());
                    
                    booking.setPaymentStatus("ADVANCE_PAID");
                    booking.setStatus("CONFIRMED");
                    bookingService.updateBooking(booking);
                    
                    return "redirect:/bookings/success?bookingId=" + bookingId;
                }
            } catch (StripeException e) {
                model.addAttribute("error", "Payment verification failed: " + e.getMessage());
                return "error";
            }
        }
        return "redirect:/bookings";
    }

    @GetMapping("/success")
    public String paymentSuccess() {
        return "payments/success";
    }

    @GetMapping("/failure")
    public String paymentFailure() {
        return "payments/failure";
    }
}