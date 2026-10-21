import json

with open("Sample API/openapi.json", "r") as file:
    data = json.load(file)

print("File read successfully \n")

print("OpenAPI version:", data["openapi"])
print("\nAPI title:", data["info"]["title"], "\n")

for path in data["paths"]:
    print("\nPath:", path)

    for method in data["paths"][path]:
        print("Method:", method)

        operation = data["paths"][path][method]
        print("Operation ID:", operation["operationId"])

        parameters = operation.get("parameters", [])

        for parameter in parameters:
            print("Parameter:", parameter["name"])