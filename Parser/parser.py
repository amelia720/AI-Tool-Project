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