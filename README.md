# Federico Zagato - Home assignment - Awesome Pizza

This is a demo project for the Adesso.it home assignment.

The project represents the initial setup, and it is not production ready.

## Build and run

The project requires Java 25 or newer.

To build the project, make sure your JAVA_HOME environment variable is set and an internet connection is available.

To run the application, execute the following command in your favorite terminal from the project root directory (adjusting the gradlew command if needed on your operating system)

```bash
.\gradlew :app:bootRun
```

The first run may take longer due to the Gradle initial setup.

When the application starts correctly, endpoints will be reachable at http://localhost:8080

## Endpoints

Four endpoints have been implemented:
- `/customer/createOrder?text=margherita` Create an order using the `text` parameter

- `/customer/getOrderStatus?id=1` Get the current status of the order specified via `id` parameter

- `/company/listOrders` List all orders

- `/company/updateOrderStatus?id=1&status=PREPARING` For the order specified via `id`, changes its status to the one send in `status` parameter. Valid statuses are: CREATED, PREPARING, DELIVERING, COMPLETED

## Notes

Since this home assignment focuses on the workflow, some assumptions and decisions were made:
- GET requests: for convenience in testing and reviewing data, all endpoints are accessible using GET
- input data validation is not performed
- information is stored in Java objects and does not persist when the application stops