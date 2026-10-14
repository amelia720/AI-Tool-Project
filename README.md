# AI-Tool-Project

This is my final-year Software Development project.

The aim of the project is to take a REST API described using OpenAPI and convert its operations into MCP tools.

## How It Works

The program will use the OpenAPI description of a REST API to create MCP tools. These tools will then allow an AI assistant to interact with the API.

I will add more details as I develop the project.

## Main Features

The project will include:

- Reading an OpenAPI description
- Generating MCP tools from API operations
- Viewing the generated tools
- Connecting the tools to the REST API
- Testing the generated tools
- Showing the API request and response

## Technologies

- Python
- Java
- MCP SDK
- OpenAPI
- REST APIs
- Spring Boot
- Maven
- MariaDB

## Sample API

The `Sample API` folder contains a small Java REST API that I am using to develop and test my project. It is not the main part of the project.

It is based on a Java application I created in second year. I added Spring Boot REST endpoints for customers, products and invoices. I also added OpenAPI and Swagger UI.

### Running the Sample API

If you would like to test the Sample API:

1. Download or clone this repository.
2. Install Java, Maven and MariaDB.
3. Create a MariaDB database called `Customers`.
4. Import `Sample API/DatabaseDump/customers.sql` into the database.
5. Open the `Sample API` folder.
6. Run:

   `mvn clean compile`

7. Run `ShopManagerApplication.java`.

Once the application is running, the API will be available at:

`http://localhost:8080`

Swagger UI can be viewed at:

`http://localhost:8080/swagger-ui/index.html`

The generated OpenAPI JSON can be viewed at:

`http://localhost:8080/v3/api-docs`

A saved copy is also included in the Sample API folder as `openapi.json`.

## Installation

Installation instructions for the main project will be added as it is developed.

## Usage

Examples of how to use the program will be added as it is developed.

## Testing

The Sample API endpoints are currently tested using Bruno.

More testing information will be added as the main project is developed.

## Documentation

Project documentation can be found in the `docs` folder.
