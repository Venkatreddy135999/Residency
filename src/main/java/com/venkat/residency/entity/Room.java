package com.venkat.residency.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String roomNumber;

    @NotBlank
    private String roomType; // SINGLE, DOUBLE, FAMILY, DORM

    @NotBlank
    private String hostelType; // BACHELOR, FAMILY, MIXED

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal pricePerNight;

    @NotBlank
    private String description;

    private Integer capacity;
    private Boolean available = true;
    
    // Amenities
    private Boolean attachedBathroom = false;
    private Boolean acAvailable = false;
    private Boolean wifiAvailable = true;
    private Boolean mealsIncluded = false;
    private Boolean laundryService = false;
    private Boolean securityDeposit = true;
    private BigDecimal depositAmount = BigDecimal.ZERO;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    public Room() {}

    public Room(String roomNumber, String roomType, String hostelType, 
                BigDecimal pricePerNight, String description, Integer capacity) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.hostelType = hostelType;
        this.pricePerNight = pricePerNight;
        this.description = description;
        this.capacity = capacity;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    public String getHostelType() { return hostelType; }
    public void setHostelType(String hostelType) { this.hostelType = hostelType; }
    public BigDecimal getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(BigDecimal pricePerNight) { this.pricePerNight = pricePerNight; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
    public Boolean getAttachedBathroom() { return attachedBathroom; }
    public void setAttachedBathroom(Boolean attachedBathroom) { this.attachedBathroom = attachedBathroom; }
    public Boolean getAcAvailable() { return acAvailable; }
    public void setAcAvailable(Boolean acAvailable) { this.acAvailable = acAvailable; }
    public Boolean getWifiAvailable() { return wifiAvailable; }
    public void setWifiAvailable(Boolean wifiAvailable) { this.wifiAvailable = wifiAvailable; }
    public Boolean getMealsIncluded() { return mealsIncluded; }
    public void setMealsIncluded(Boolean mealsIncluded) { this.mealsIncluded = mealsIncluded; }
    public Boolean getLaundryService() { return laundryService; }
    public void setLaundryService(Boolean laundryService) { this.laundryService = laundryService; }
    public Boolean getSecurityDeposit() { return securityDeposit; }
    public void setSecurityDeposit(Boolean securityDeposit) { this.securityDeposit = securityDeposit; }
    public BigDecimal getDepositAmount() { return depositAmount; }
    public void setDepositAmount(BigDecimal depositAmount) { this.depositAmount = depositAmount; }
    public List<Booking> getBookings() { return bookings; }
    public void setBookings(List<Booking> bookings) { this.bookings = bookings; }
}