-- Sample Rooms Data for Venkat Residency
-- Bachelor Hostel Rooms
INSERT INTO rooms (room_number, room_type, hostel_type, price_per_night, description, capacity, available, attached_bathroom, ac_available, wifi_available, meals_included, laundry_service, security_deposit, deposit_amount) VALUES
('B101', 'SINGLE', 'BACHELOR', 800.00, 'Comfortable single room with attached bathroom, study table, wardrobe, and bed. Perfect for students and working professionals.', 1, true, true, true, true, true, true, true, 2000.00),
('B102', 'SINGLE', 'BACHELOR', 750.00, 'Economy single room with shared bathroom facilities. Includes study table and wardrobe. Ideal for budget-conscious students.', 1, true, false, false, true, true, false, true, 1500.00),
('B103', 'SINGLE', 'BACHELOR', 900.00, 'Premium single room with AC, attached bathroom, and balcony view. Includes all modern amenities.', 1, true, true, true, true, true, true, true, 2500.00),
('B104', 'DORM', 'BACHELOR', 400.00, '4-bed dormitory room with personal lockers for each bed. Shared bathroom facilities. Great for group accommodation.', 4, true, false, true, true, true, true, true, 1000.00),
('B105', 'DORM', 'BACHELOR', 350.00, '6-bed dormitory room with common study area. Shared amenities. Most economical option for students.', 6, true, false, false, true, true, false, true, 800.00),
('B201', 'SINGLE', 'BACHELOR', 850.00, 'Single room on 2nd floor with city view, attached bathroom, and all essential amenities.', 1, true, true, true, true, true, true, true, 2200.00),
('B202', 'SINGLE', 'BACHELOR', 700.00, 'Basic single room with shared bathroom. Includes bed, table, and wardrobe. WiFi and meals included.', 1, true, false, false, true, true, false, true, 1500.00),

-- Family Hostel Rooms
('F101', 'DOUBLE', 'FAMILY', 1500.00, 'Spacious double room with attached bathroom, TV, balcony, and sitting area. Perfect for couples.', 2, true, true, true, true, true, true, true, 3000.00),
('F102', 'DOUBLE', 'FAMILY', 1200.00, 'Double room with garden view and comfortable sitting area. Includes attached bathroom and all basic amenities.', 2, true, true, false, true, true, true, true, 2500.00),
('F103', 'DOUBLE', 'FAMILY', 1400.00, 'Deluxe double room with AC, TV, attached bathroom, and balcony. Ideal for family stays.', 2, true, true, true, true, true, true, true, 2800.00),
('F201', 'FAMILY', 'FAMILY', 2500.00, 'Family room with 2 double beds, extra space for children, attached bathroom, TV, and balcony. Spacious and comfortable.', 4, true, true, true, true, true, true, true, 5000.00),
('F202', 'FAMILY', 'FAMILY', 2000.00, 'Family suite with kitchenette, dining area, attached bathroom, and living space. Perfect for extended stays.', 4, true, true, true, true, false, true, true, 4000.00),
('F203', 'FAMILY', 'FAMILY', 2200.00, 'Premium family room with all amenities including AC, TV, attached bathroom, and extra storage space.', 4, true, true, true, true, true, true, true, 4500.00),
('F301', 'DOUBLE', 'FAMILY', 1300.00, 'Comfortable double room on 3rd floor with attached bathroom and balcony. Peaceful and quiet location.', 2, true, true, false, true, true, true, true, 2600.00),

-- Mixed Hostel Rooms
('M101', 'SINGLE', 'MIXED', 1000.00, 'Premium single room with balcony and city view. Includes all luxury amenities - AC, attached bathroom, WiFi, and more.', 1, true, true, true, true, true, true, true, 2500.00),
('M102', 'SINGLE', 'MIXED', 950.00, 'Executive single room with modern decor, attached bathroom, and premium facilities. Ideal for business travelers.', 1, true, true, true, true, true, true, true, 2400.00),
('M103', 'DOUBLE', 'MIXED', 1800.00, 'Deluxe double room with luxury amenities including AC, TV, attached bathroom, balcony, and premium furnishings.', 2, true, true, true, true, true, true, true, 3500.00),
('M104', 'DOUBLE', 'MIXED', 1600.00, 'Comfortable double room with all modern amenities. Spacious and well-furnished for a relaxing stay.', 2, true, true, true, true, true, true, true, 3200.00),
('M201', 'FAMILY', 'MIXED', 3000.00, 'Executive family suite with premium facilities - 2 bedrooms, living area, kitchenette, attached bathrooms, and all luxury amenities.', 4, true, true, true, true, true, true, true, 6000.00),
('M202', 'FAMILY', 'MIXED', 2800.00, 'Premium family suite with spacious rooms, modern amenities, attached bathrooms, and comfortable living space.', 4, true, true, true, true, true, true, true, 5500.00),
('M301', 'DOUBLE', 'MIXED', 1700.00, 'Superior double room with premium features including AC, TV, attached bathroom, balcony, and high-quality furnishings.', 2, true, true, true, true, true, true, true, 3400.00),
('M302', 'SINGLE', 'MIXED', 1100.00, 'Luxury single room with panoramic views, premium amenities, and exceptional comfort for discerning guests.', 1, true, true, true, true, true, true, true, 2700.00);
