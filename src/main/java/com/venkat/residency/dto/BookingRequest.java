package com.venkat.residency.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class BookingRequest {
    
    @NotBlank(message = "Guest name is required")
    private String guestName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String guestEmail;

    @NotBlank(message = "Phone number is required")
    private String guestPhone;

    @NotBlank(message = "ID proof is required")
    private String idProof;

    private String idProofNumber;

    @NotNull(message = "Check-in date is required")
    @Future(message = "Check-in date must be in the future")
    private LocalDate checkInDate;

    @NotNull(message = "Check-out date is required")
    private LocalDate checkOutDate;

    @NotNull(message = "Number of guests is required")
    @Min(value = 1, message = "At least 1 guest is required")
    private Integer numberOfGuests;

    private String specialRequests;
    private String emergencyContact;
    private String emergencyPhone;
    private String purposeOfStay;

    @NotNull(message = "Room ID is required")
    private Long roomId;

    // Getters and Setters
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
    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
    public String getEmergencyPhone() { return emergencyPhone; }
    public void setEmergencyPhone(String emergencyPhone) { this.emergencyPhone = emergencyPhone; }
    public String getPurposeOfStay() { return purposeOfStay; }
    public void setPurposeOfStay(String purposeOfStay) { this.purposeOfStay = purposeOfStay; }
    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }
}