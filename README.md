# FlightApp

**Username:** user  
**Password:** password  

**Swagger API Documentation:** [Swagger UI](http://localhost:8080/swagger-ui.html)  
**OpenAPI Descriptions:** [OpenAPI](http://localhost:8080/v3/api-docs)

## Flights Controller Documentation

This documentation provides details about the RESTful endpoints available in the Flights Controller.

### Retrieve All Flights

- **URL:** `/flights/`
- **Method:** `GET`
- **Description:** Retrieves all flights stored in the database.
- **Response:** Returns a list of all flights.

### Retrieve Flight by ID

- **URL:** `/flights/{flightId}`
- **Method:** `GET`
- **Description:** Retrieves a specific flight by its ID.
- **Parameters:**
  - `flightId`: ID of the flight to retrieve.
- **Response:** Returns the flight object with the specified ID if found, otherwise returns a 404 Not Found status.

### Create a New Flight

- **URL:** `/flights/create`
- **Method:** `POST`
- **Description:** Creates a new flight and stores it in the database.
- **Request Body:** Flight object to be created.
- **Response:** Returns a message indicating successful creation of the flight.

### Delete a Flight

- **URL:** `/flights/{id}`
- **Method:** `DELETE`
- **Description:** Deletes a flight from the database by its ID.
- **Parameters:**
  - `id`: ID of the flight to delete.
- **Response:** Returns a message indicating successful deletion of the flight.

### Update a Flight

- **URL:** `/flights/update/{id}`
- **Method:** `PUT`
- **Description:** Updates an existing flight with the specified ID.
- **Parameters:**
  - `id`: ID of the flight to update.
- **Request Body:** Updated flight object.
- **Response:** Returns a message indicating successful update of the flight.

### Search Flights by Airports

- **URL:** `/flights/searchByAirports`
- **Method:** `GET`
- **Description:** Searches flights based on departure and arrival airports.
- **Query Parameters:**
  - `departure_airport`: Departure airport code.
  - `arrival_airport`: Arrival airport code.
- **Response:** Returns a list of flights matching the specified departure and arrival airports.

### Search Flights by Departure and Arrival Times

- **URL:** `/flights/searchByDepartureAndArrivalTimes`
- **Method:** `GET`
- **Description:** Searches flights based on departure and arrival date/time.
- **Query Parameters:**
  - `departureDateTime`: Departure date/time in ISO format.
  - `arrivalDateTime`: Arrival date/time in ISO format.
- **Response:** Returns a list of flights departing between the specified departure and arrival times.

### Search Flights by Departure Time

- **URL:** `/flights/searchByDepartureTime`
- **Method:** `GET`
- **Description:** Searches one-way flights based on departure airport, arrival airport, and departure date/time.
- **Query Parameters:**
  - `departure`: Departure airport code.
  - `arrival`: Arrival airport code.
  - `departureDateTime`: Departure date/time in ISO format.
  - `returnDateTime` (optional): Return date/time in ISO format for round-trip flights.
- **Response:** Returns a list of one-way flights departing between the specified departure and return times.

### Search Flights

- **URL:** `/flights/search`
- **Method:** `GET`
- **Description:** Searches flights based on departure airport, arrival airport, departure date/time, and return date/time (for round-trip flights).
- **Query Parameters:**
  - `departure`: Departure airport code.
  - `arrival`: Arrival airport code.
  - `departureDateTime`: Departure date/time in ISO format.
  - `returnDateTime` (optional): Return date/time in ISO format for round-trip flights.
- **Response:** Returns a list of one-way flights departing between the specified departure and return times, and if provided, a list of return flights.

# Airport Controller Documentation

This documentation provides details about the RESTful endpoints available in the Airport Controller.

## Retrieve All Airports

- **URL:** `/airports`
- **Method:** `GET`
- **Description:** Retrieves all airports stored in the database.
- **Response:** Returns a list of all airports.

## Retrieve Airport by ID

- **URL:** `/airports/{id}`
- **Method:** `GET`
- **Description:** Retrieves a specific airport by its ID.
- **Parameters:**
  - `id`: ID of the airport to retrieve.
- **Response:** Returns the airport object with the specified ID.

## Create a New Airport

- **URL:** `/airports/create`
- **Method:** `POST`
- **Description:** Creates a new airport and stores it in the database.
- **Request Body:** Airport object to be created.
- **Response:** Returns a message indicating successful creation of the airport.

## Update an Airport

- **URL:** `/airports/{id}`
- **Method:** `PUT`
- **Description:** Updates an existing airport with the specified ID.
- **Parameters:**
  - `id`: ID of the airport to update.
- **Request Body:** Updated airport object.
- **Response:** Returns a message indicating successful update of the airport.

## Delete an Airport

- **URL:** `/airports/{id}`
- **Method:** `DELETE`
- **Description:** Deletes an airport from the database by its ID.
- **Parameters:**
  - `id`: ID of the airport to delete.

## Retrieve Airports by City

- **URL:** `/airports/city`
- **Method:** `GET`
- **Description:** Retrieves airports based on the specified city.
- **Query Parameters:**
  - `city`: Name of the city to search airports for.
- **Response:** Returns a list of airports located in the specified city.
