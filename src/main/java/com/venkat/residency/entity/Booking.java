package com.venkat.residency.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String guestName;

    @NotBlank
    @Email
    private String guestEmail;

    @NotBlank
    private String guestPhone;

    @NotBlank
    private String idProof; // AADHAR, PASSPORT, DRIVING_LICENSE

    private String idProofNumber;

    @NotNull
    @Future
    private LocalDate checkInDate;

    @NotNull
    private LocalDate checkOutDate;

    @Min(1)
    private Integer numberOfGuests;
    
    private String specialRequests;
    private String status = "PENDING"; // PENDING, CONFIRMED, CANCELLED, CHECKED_IN, CHECKED_OUT

    private LocalDateTime bookingDate = LocalDateTime.now();
    
    // Payment fields
    private BigDecimal totalAmount;
    private BigDecimal advancePaid = BigDecimal.ZERO;
    private String paymentStatus = "PENDING"; // PENDING, ADVANCE_PAID, FULLY_PAID, REFUNDED
    private String paymentId;
    private String transactionId;
    
    // Additional info
    private String emergencyContact;
    private String emergencyPhone;
    private String purposeOfStay; // BUSINESS, VACATION, EDUCATION, OTHER

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public Booking() {}

    // Calculate total amount
    public BigDecimal calculateTotalAmount() {
        if (room != null && checkInDate != null && checkOutDate != null) {
            long nights = java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
            return room.getPricePerNight().multiply(BigDecimal.valueOf(nights));
        }
        return BigDecimal.ZERO;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getGuestName() { return guestName; }
    public void setGuestName(String guestName) { this.guestName = guestName; }
    public String getGuestEmail() { return guestEmail; }
    public void setGuestEmail(String guestEmail) { this.guestEmail = guestEmail; }
    public String getGuestPhone() { return guestPhone; }
    public void setGuestPhone(String guestPhone) { this.guestPhone = guestPhone; }
    public String getIdProof() { return idProof; }
    public void setIdProof(String idProof) { this.idProof = idProof; }
    public String getIdProofNumber() { return idProofNumber; }
    public void setIdProofNumber(String idProofNumber) { this.idProofNumber = idProofNumber; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public void setCheckInDate(LocalDate checkInDate) { this.checkInDate = checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(LocalDate checkOutDate) { this.checkOutDate = checkOutDate; }
    public Integer getNumberOfGuests() { return numberOfGuests; }
    public void setNumberOfGuests(Integer numberOfGuests) { this.numberOfGuests = numberOfGuests; }
    public String getSpecialRequests() { return specialRequests; }
    public void setSpecialRequests(String specialRequests) { this.specialRequests = specialRequests; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public BigDecimal getAdvancePaid() { return advancePaid; }
    public void setAdvancePaid(BigDecimal advancePaid) { this.advancePaid = advancePaid; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
    public String getEmergencyPhone() { return emergencyPhone; }
    public void setEmergencyPhone(String emergencyPhone) { this.emergencyPhone = emergencyPhone; }
    public String getPurposeOfStay() { return purposeOfStay; }
    public void setPurposeOfStay(String purposeOfStay) { this.purposeOfStay = purposeOfStay; }
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
}