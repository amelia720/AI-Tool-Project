import json

# Read the OpenAPI JSON file
with open("Sample API/openapi.json", "r") as file:
    data = json.load(file)

serverUrl = data["servers"][0]["url"]  #saving API url so that we can later find the HTTP request

apiOperations = []

# Get schema details from the OpenAPI file
def getSchemaDetails(schema):

    # Check if the schema has a reference
    if "$ref" in schema:
        schemaName = schema["$ref"].split("/")[-1]
        schemaDetails = data["components"]["schemas"][schemaName]

        return getSchemaDetails(schemaDetails)

    updatedSchema = schema.copy()

    # Go through object properties
    if "properties" in schema:
        properties = {}

        for propertyName in updatedSchema["properties"]:
            fieldSchema = updatedSchema["properties"][propertyName]
            properties[propertyName] = getSchemaDetails(fieldSchema)

        updatedSchema["properties"] = properties

    # Check for array items
    if "items" in updatedSchema:
        itemSchema = updatedSchema["items"]
        updatedSchema["items"] = getSchemaDetails(itemSchema)

    return updatedSchema


# Go through each path in the API description
for path in data["paths"]:

    # go through each method
    for method in data["paths"][path]:
        operation = data["paths"][path][method]

        # Skip anything that isn't an HTTP method
        if method not in ["get", "post", "put", "patch", "delete"]:
            continue

        # Get the operation description
        description = operation.get("description")

        if not description:
            description = operation.get("summary")

        if not description:
            description = operation["operationId"]

        # Store information about this operation
        operationInfo = {
            "name": operation["operationId"],
            "title": operation.get("summary"),
            "description": description,
            "method": method,
            "path": path,
            "serverUrl": serverUrl,
            "inputSchema": {
                "type": "object",
                "properties": {},
                "required": []
            },
            "outputSchema": None
        }

        inputSchema = operationInfo["inputSchema"]

        #get parameters
        parameters = operation.get("parameters", [])

        for parameter in parameters:

            parameterName = parameter["name"]

            # Add parameters to the input schema
            inputSchema["properties"][parameterName] = getSchemaDetails(
                parameter["schema"]
            )

            if parameter.get("required", False):
                inputSchema["required"].append(parameterName)

        # Check for a request body
        requestBody = operation.get("requestBody")

        if requestBody:
            schema = requestBody["content"]["application/json"]["schema"]

            # Get schema details
            schemaDetails = getSchemaDetails(schema)

            properties = schemaDetails.get("properties", {})

            # Get required properties
            requiredProperties = schemaDetails.get("required", [])

            # Go through body inputs
            for propertyName in properties:

                # Add request body fields to the input schema
                inputSchema["properties"][propertyName] = properties[propertyName]

                if propertyName in requiredProperties:
                    inputSchema["required"].append(propertyName)

        # Check for a successful response
        responses = operation.get("responses", {})

        for statusCode in responses:

            if str(statusCode).startswith("2"):
                response = responses[statusCode]
                content = response.get("content", {})

                if content:
                    mediaType = next(iter(content))
                    schema = content[mediaType].get("schema")

                    if schema:
                        # Get schema details
                        schemaDetails = getSchemaDetails(schema)

                        # MCP outputSchema must describe an object
                        if schemaDetails.get("type") == "object":
                            operationInfo["outputSchema"] = schemaDetails

                        elif schemaDetails.get("type") == "array":
                            operationInfo["outputSchema"] = {
                                "type": "object",
                                "properties": {
                                    "items": schemaDetails
                                }
                            }

                        else:
                            operationInfo["outputSchema"] = {
                                "type": "object",
                                "properties": {
                                    "result": schemaDetails
                                }
                            }

                        break

        # Add this operation to the list
        apiOperations.append(operationInfo)


# Print API operations
print("\nAPI Operations:")
for operation in apiOperations:
    print(json.dumps(operation, indent=4))
