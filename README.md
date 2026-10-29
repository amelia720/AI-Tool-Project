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
- MCP Python SDK
- OpenAPI
- REST APIs
- Spring Boot
- Maven
- MariaDB

## Iteration 1

For the first iteration, I focused on creating a REST API that I can use to develop and test the project.

This iteration included:

- Adding a REST API to my existing Java Shop Manager application
- Adding REST endpoints for customers, products and invoices
- Testing the API endpoints using Bruno
- Adding OpenAPI and Swagger UI using springdoc
- Adding validation so required API inputs are shown in the OpenAPI description
- Generating and saving the `openapi.json` file
- Starting a Python parser to read the OpenAPI file
- Extracting paths, HTTP methods, operation IDs and parameters
- Extracting request body schemas and their properties

MCP tool generation has not been implemented yet. This will be the main focus of Iteration 2.

## Sample API

The `Sample API` folder contains a small Java REST API that I am using to develop and test my project. It is not the main part of the project.

It is based on a Java application I created in second year. I added Spring Boot REST endpoints for customers, products and invoices. I also added OpenAPI and Swagger UI.

### Running the Sample API

If you would like to test the Sample API:

1. Clone this repository.
2. Install Java, Maven and MariaDB.
3. Create a MariaDB database called `Customers`.
4. Import `Sample API/DatabaseDump/customers.sql` into the database.
5. Check the database connection settings in `MyConnection.java` and update the username and password if needed.
6. Open the `Sample API` folder.
7. Run `mvn clean compile` in the terminal to compile the project.
8. Run `ShopManagerApplication.java` to start the REST API.
9. Run `RUN_CODE_HERE.java` to start the Shop Manager GUI.

Once the application is running, the API will be available at:

`http://localhost:8080`

Swagger UI can be viewed at:

`http://localhost:8080/swagger-ui/index.html`

The generated OpenAPI JSON can be viewed at:

`http://localhost:8080/v3/api-docs`

A saved copy is also included in the Sample API folder as `openapi.json`.

### Running the OpenAPI Parser

The Python parser can be found in the `Parser` folder.

It reads the saved `openapi.json` file and extracts information from the OpenAPI description.

From the main project folder, run:

`python3 Parser/parser.py`

The parser currently extracts information such as paths, HTTP methods, operation IDs, parameters and request body schemas.

## Installation

Installation instructions for the main project will be added as it is developed.

## Usage

Examples of how to use the program will be added as it is developed.

## Testing

The Sample API endpoints are currently tested using Bruno.

More testing information will be added as the main project is developed.

## Documentation

Project documentation can be found in the `docs` folder.