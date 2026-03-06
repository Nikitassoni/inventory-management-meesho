## Inventory Management – Meesho Interview

This is a small Spring Boot–based coding exercise that models a simple e‑commerce inventory and order management flow.

### What it does

- Manages **products** with an initial stock count.
- Maintains **inventory** for each product using in‑memory repositories.
- Creates **orders** and temporarily **blocks inventory** during payment.
- Confirms orders to make inventory reduction permanent, or releases inventory automatically if payment is not completed within 5 minutes.

### Key components

- `InterviewApplication` – Bootstraps the Spring Boot app and runs a demo flow.
- `ProductService` – Adds products and initializes inventory.
- `InventoryService` – Reads/updates inventory, blocks inventory for orders, and confirms orders.
- `OrderService` – Places orders and stores them in an in‑memory repository.
- `model/*` – Domain models like `Product`, `Inventory`, `Order`, `OrderStatus`, and `User`.

### Tech stack

- Java
- Spring Boot
- In‑memory `ConcurrentHashMap` repositories (no external database)

