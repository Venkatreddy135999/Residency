package com.venkat.residency.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RoomForm {

    private Long id;

    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @NotBlank(message = "Room type is required")
    private String roomType; // SINGLE, DOUBLE, FAMILY, DORM

    @NotBlank(message = "Hostel type is required")
    private String hostelType; // BACHELOR, FAMILY, MIXED

    @NotNull(message = "Price per night is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal pricePerNight;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;

    private Boolean available = true;

    private Boolean attachedBathroom = false;
    private Boolean acAvailable = false;
    private Boolean wifiAvailable = true;
    private Boolean mealsIncluded = false;
    private Boolean laundryService = false;
    private Boolean securityDeposit = true;
    private BigDecimal depositAmount = BigDecimal.ZERO;

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
}




