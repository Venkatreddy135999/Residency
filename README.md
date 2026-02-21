# Venkat Residency - Hostel Booking System

A comprehensive Spring Boot web application for managing hostel bookings with support for Bachelor, Family, and Mixed hostel types.

## Features

- **Room Management**: Browse and search rooms by type and availability
- **Booking System**: Complete booking workflow with date validation
- **Payment Integration**: Stripe payment gateway integration (configure keys needed)
- **Email Notifications**: Booking confirmation emails
- **Admin Dashboard**: Admin panel for managing rooms and bookings
- **Multiple Hostel Types**: 
  - Bachelor Hostel (for students/professionals)
  - Family Hostel (for families)
  - Mixed Hostel (premium accommodations)

## Room Data

The application comes pre-loaded with sample rooms:

### Bachelor Hostel (7 rooms)
- Room Numbers: B101-B202
- Types: Single rooms, Dormitory rooms (4-bed, 6-bed)
- Price Range: ₹350 - ₹900 per night
- Features: Study areas, WiFi, meal plans, laundry service

### Family Hostel (7 rooms)
- Room Numbers: F101-F301
- Types: Double rooms, Family rooms (4-person capacity)
- Price Range: ₹1,200 - ₹2,500 per night
- Features: Attached bathrooms, TV, balconies, kitchenettes

### Mixed Hostel (8 rooms)
- Room Numbers: M101-M302
- Types: Premium Single, Double, and Family suites
- Price Range: ₹950 - ₹3,000 per night
- Features: Luxury amenities, premium furnishings, all modern facilities

## Technology Stack

- **Backend**: Spring Boot 3.1.0, Java 17
- **Database**: H2 In-Memory Database
- **Frontend**: Thymeleaf, Bootstrap 5
- **Payment**: Stripe API
- **Email**: Spring Mail

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Configuration

1. **Database**: H2 database is configured automatically (in-memory)
   - Access H2 Console: http://localhost:8081/h2-console
   - JDBC URL: `jdbc:h2:mem:venkatresidency`
   - Username: `sa`
   - Password: (empty)

2. **Server Port**: Configured to run on port **8081**
   - Application URL: http://localhost:8081

3. **Stripe Payment** (Optional):
   - Update `stripe.public.key` in `application.properties`
   - Update `stripe.secret.key` in `application.properties`

4. **Email** (Optional):
   - Update email credentials in `application.properties`

### Running the Application

```bash
cd residency
mvn spring-boot:run
```

Or use your IDE to run `VenkatResidencyApplication.java`

## Application Endpoints

### Public Pages
- `/` - Home page with featured rooms
- `/rooms` - Browse all rooms
- `/rooms/bachelor` - Bachelor hostel rooms
- `/rooms/family` - Family hostel rooms
- `/rooms/mixed` - Mixed hostel rooms
- `/rooms/search` - Search available rooms by date
- `/rooms/{id}` - Room details
- `/bookings` - View all bookings
- `/bookings/new?roomId={id}` - Create new booking
- `/about` - About page
- `/contact` - Contact page

### Admin Pages
- `/admin/dashboard` - Admin dashboard
- `/admin/rooms` - Manage rooms
- `/admin/bookings` - Manage bookings

### API Endpoints
- `/test` - Health check endpoint
- `/health` - Application status

## Project Structure

```
residency/
├── src/main/java/com/venkat/residency/
│   ├── controller/          # REST/Web controllers
│   ├── entity/              # JPA entities (Room, Booking, Payment)
│   ├── repository/          # Data access layer
│   ├── service/             # Business logic
│   ├── dto/                 # Data Transfer Objects
│   └── VenkatResidencyApplication.java
├── src/main/resources/
│   ├── templates/           # Thymeleaf HTML templates
│   ├── application.properties
│   └── data.sql            # Sample room data
└── pom.xml
```

## Sample Data

The application automatically loads sample rooms from `data.sql` on startup:
- 7 Bachelor Hostel rooms
- 7 Family Hostel rooms
- 8 Mixed Hostel rooms

All rooms are marked as available and ready for booking.

## Notes

- The database is in-memory (H2), so data resets on application restart
- Stripe keys are placeholders - configure real keys for payment processing
- Email service requires proper SMTP configuration to send emails
- Sample rooms are automatically loaded on application startup

## License

This project is for demonstration purposes.




