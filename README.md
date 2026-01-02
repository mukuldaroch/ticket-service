# Ticket Service — EventManagment Microservice

The **Ticket Service** is a dedicated **microservice** within the Event ecosystem that handles **all ticket-related operations**. It provides backend API for **creating, managing, and selling tickets** for events.

---

## Features

- **Manage Tickets:** Create, update, and retrieve tickets for events.
- **Ticket Types:** Supports multiple types per event (VIP, Regular, Student, etc.).
- **Availability Tracking:** Keeps track of available vs. sold tickets .

![Ticket Service Diagram](docs/assets/ticket-service.jpg)

---

## Database Design

The **Ticket Service database** tracks tickets and ticket types. It works closely with the Event microservice to link tickets to their respective events.

Key entities:

- `Ticket`: Individual tickets purchased by users.
- `TicketType`: Defines categories, price, and available count.

###[ Architecture diagrams ](https://miro.com/welcomeonboard/czVOSUJvdDFuVm1NcXZtS29wZEc0SmFWTUVXemgxZTdETURCUDhsWGdyRDBMUUNHVWJCckNoS3l6Sjlkc3g4dnE4VnNTU1VWRWNpcmJQVVdyTXBqUHprZ0kzUnRKRWh0Nm1RNi9IakE0V2tqMU9zdDVIMlRuZVR4L0RsbTlIa2VNakdSWkpBejJWRjJhRnhhb1UwcS9BPT0hdjE=?share_link_id=315624819253)

###[ Microservice Design ](https://miro.com/welcomeonboard/czVOSUJvdDFuVm1NcXZtS29wZEc0SmFWTUVXemgxZTdETURCUDhsWGdyRDBMUUNHVWJCckNoS3l6Sjlkc3g4dnE4VnNTU1VWRWNpcmJQVVdyTXBqUHprZ0kzUnRKRWh0Nm1RNi9IakE0V2xNRmJZQXJKeEtBZGhGd0lpa2xaaHN3VHhHVHd5UWtSM1BidUtUYmxycDRnPT0hdjE=?share_link_id=689928873168)

---

## API Endpoints

Ticket-Types

| Method    | Endpoint                                          | Description                        |
| --------- | ------------------------------------------------- | ---------------------------------- |
| **GET**   | `/event/{event_id}/ticket-types`                  | List all ticket types for an event |
| **GET**   | `/event/{event_id}/ticket-types/{ticket_type_id}` | Retrieve ticket type details       |
| **PATCH** | `/event/{event_id}/ticket-types/{ticket_type_id}` | Update ticket type (partial)       |

Tickets

| Method     | Endpoint                               | Description                   |
| ---------- | -------------------------------------- | ----------------------------- |
| **GET**    | `/event/{event_id}/tickets`            | List all tickets for an event |
| **GET**    | `/event/{event_id}/ticket/{ticket_id}` | Retrieve details of a ticket  |
| **PATCH**  | `/event/{event_id}/ticket`             | Update ticket info (partial)  |
| **DELETE** | `/event/{event_id}/tickets`            | delete a ticket of a event    |

---

## 🧩 Tech Stack

- **Backend:** Spring Boot (Java 17+)
- **Build Tool:** Gradle
- **Database:** PostgreSQL
- **Containerization:** Docker
- **Auth:** JWT-based Authentication
- **Deployment:** Dockerized microservice setup (Planned)

---

## ⚙️ Running Locally

Requirements:

- Java 17+
- Gradle (wrapper included)
- PostgreSQL running locally

Steps:

```bash
# 1️⃣ Clone repo
git clone https://github.com/mukuldaroch/ticket-service.git
cd eventforge

# Build container

docker compose up -d

# Run docker

docker compose up

# 3️⃣ Build project

./gradlew clean build

# 4️⃣ Run microservice

./gradlew clean bootrun

```

---

## Future Plans

- Payment gateway integration (Stripe/Razorpay)
- QR code generation & validation for tickets
- Email confirmation for purchased tickets
- Real-time analytics for ticket sales

---

## 🤝 Contributing

Open issues, suggest features, or submit PRs — all contributions welcome.
Help make Ticket Service the **core of EventManagment ticketing system**.

---

## 👨‍💻 Author

- [@Mukul Daroch](https://github.com/mukuldaroch)
