# package dtos.ticket.request;

1. **CreateTicketRequest.java**

| Column1 | Column2      |
| ------- | ------------ |
| UUID    | userId       |
| UUID    | ticketTypeId |

2. **UpdateTicketRequest.java**

| Column1          | Column2      |
| ---------------- | ------------ |
| UUID             | ticketId     |
| UUID             | userId       |
| UUID             | ticketTypeId |
| TicketStatusEnum | ticketStatus |
| LocalDateTime    | usedAt       |
| LocalDateTime    | cancelledAt  |

# package dtos.ticket.response;

1. **CreateTicketResponse.java**

| Column1          | Column2         |
| ---------------- | --------------- |
| UUID             | ticketId        |
| UUID             | userId          |
| UUID             | ticketTypeId    |
| TicketStatusEnum | ticketStatus    |
| Double           | priceAtPurchase |

2. **GetTicketResponse.java**

| Column1          | Column2         |
| ---------------- | --------------- |
| UUID             | ticketId        |
| UUID             | userId          |
| UUID             | ticketTypeId    |
| TicketStatusEnum | ticketStatus    |
| Double           | priceAtPurchase |

3. **UpdateTicketResponse.java**

| Column1          | Column2      |
| ---------------- | ------------ |
| UUID             | ticketId     |
| UUID             | userId       |
| UUID             | ticketTypeId |
| TicketStatusEnum | ticketStatus |
| LocalDateTime    | usedAt       |
| LocalDateTime    | cancelledAt  |

# package dtos.tickettype.request;

1. **CreateTicketTypeRequest.java**

| Column1              | Column2        |
| -------------------- | -------------- |
| UUID                 | eventId        |
| String               | name           |
| Double               | price          |
| String               | description    |
| Integer              | totalAvailable |
| TicketTypeStatusEnum | status         |

2. **UpdateTicketTypeRequest.java**

| Column1 | Column2        |
| ------- | -------------- |
| UUID    | id             |
| String  | name           |
| Double  | price          |
| String  | description    |
| Integer | totalAvailable |

# package dtos.tickettype.response;

1. **CreateTicketTypeResponse.java**

| Column1              | Column2        |
| -------------------- | -------------- |
| UUID                 | ticketTypeId   |
| UUID                 | eventId        |
| String               | name           |
| Double               | price          |
| String               | description    |
| Integer              | totalAvailable |
| TicketTypeStatusEnum | status         |

2. **GetEventTicketTypesResponse.java**

| Column1              | Column2        |
| -------------------- | -------------- |
| UUID                 | ticketTypeId   |
| UUID                 | eventId        |
| String               | name           |
| Double               | price          |
| String               | description    |
| Integer              | totalAvailable |
| TicketTypeStatusEnum | status         |
| LocalDateTime        | createdAt      |
| LocalDateTime        | updatedAt      |

3. **UpdateTicketTypeResponse.java**

| Column1              | Column2        |
| -------------------- | -------------- |
| UUID                 | ticketTypeId   |
| UUID                 | eventId        |
| String               | name           |
| Double               | price          |
| String               | description    |
| Integer              | totalAvailable |
| TicketTypeStatusEnum | status         |
| LocalDateTime        | createdAt      |
| LocalDateTime        | updatedAt      |
