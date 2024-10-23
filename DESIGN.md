# Assumptions and Design Decisions
## Project Name: Reservation Management System
# 1. Assumptions

## 1.1 User Interaction

Users will provide accurate and valid input for reservation details (name, phone number, email, date, time, etc.).
The system will primarily serve individual users rather than groups or businesses.
The user prefers email and SMS as the main methods of communication for confirmations and reminders.

## 1.2 System Constraints

The system is expected to handle a moderate volume of reservations (e.g., up to 1000 reservations per day) without performance degradation.
The database is available 24/7, with no expected downtime.

## 1.3 Notifications

It is assumed that third-party services for SMS and email  are reliable and available at all times.
Reservation reminders will be sent exactly 4 hours prior to the reservation time.

## 1.4 Reservation Cancellation

Users will cancel reservations using a unique reservation ID sent to them upon booking.
No cancellation fee or penalty applies to any user cancellations.
# 2. Design Decisions

## 2.1 Tech Stack

The system was built using Spring Boot due to its robust support for web development and ease of integration with JPA for database management.
H2 is used as the development database for quick prototyping. For production, the system will switch to a more robust relational database like MySQL or PostgreSQL.
Lombok is used to reduce boilerplate code (e.g., getters/setters, constructors).

## 2.2 Security

Although Spring Security was considered, the project allows all HTTP requests to be unauthenticated for simplicity. Security can be added in the future with JWT or OAuth if needed.
To accommodate future scalability, routes are structured to make adding authentication layers (like API keys or tokens) straightforward.

## 2.3 Error Handling

Custom exceptions (e.g., ReservationNotFoundException, ReservationCreateException) are used to handle specific errors related to the reservation process. A global exception handler is implemented to provide meaningful error messages to users.
## 2.4 API Structure

The system follows a RESTful API design to make it easy for clients (web or mobile apps) to interact with the service.
POST requests are used for creating reservations, while GET requests allow users to retrieve existing reservations by email or ID.
PUT requests allow updates to existing reservations (time and number of guests), and DELETE requests handle reservation cancellations.

## 2.5 Notification Service

Email notifications are implemented using Spring Boot’s JavaMailSender to send confirmation emails upon booking, updates, and cancellations.
SMS notifications are planned to be integrated with a third-party provider (e.g., Twilio) for flexibility in communication channels.

## 2.6 Database Structure

The reservation table stores essential details like customer name, contact info, reservation date/time, and the number of guests.
A unique reservation ID is generated for each booking to allow customers to view, update, or cancel their reservations.
## 2.7 Performance Considerations

The system caches frequently accessed data (e.g., reservations for a specific date range) to reduce database load.
Future scaling is considered, with an option to deploy the app in a cloud environment (e.g., AWS or GCP) with load balancers and database replication.
