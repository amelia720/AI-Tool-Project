import json

# Read the OpenAPI JSON file
with open("Sample API/openapi.json", "r") as file:
    data = json.load(file)

print("File read successfully \n")

print("OpenAPI version:", data["openapi"])
print("\nAPI title:", data["info"]["title"], "\n")

serverUrl = data["servers"][0]["url"]  #saving API url so that we can later find the HTTP request

print("Server URL:", serverUrl)

# Go through each path in the API
for path in data["paths"]:
    print("\nPath:", path)

    # go through each mehtod
    for method in data["paths"][path]:
        print("Method:", method)

        operation = data["paths"][path][method]
        print("Operation ID:", operation["operationId"])

        #get parameters
        parameters = operation.get("parameters", [])

        for parameter in parameters:
            print("Parameter:", parameter["name"])
            print("Location:", parameter["in"])
            print("Required:", parameter["required"])
            print("Type:", parameter["schema"]["type"])
        
        # Check for a request body
        requestBody = operation.get("requestBody")

        if requestBody:
            print("Request Body: Yes")

            schema = requestBody["content"]["application/json"]["schema"]

            print("Request Body Schema:", schema["$ref"])
            
            # Get schema name from $ref
            schemaName = schema["$ref"].split("/")[-1]

            print("Schema Name:", schemaName)

            schemaDetails = data["components"]["schemas"][schemaName]

            properties = schemaDetails["properties"]

            # Get required propertie
            requiredProperties = schemaDetails.get("required", [])
            
            # Go through body inputs
            for propertyName in properties:
                print("Property:", propertyName)
                print("Type:", properties[propertyName]["type"])
                print("Required:", propertyName in requiredProperties)