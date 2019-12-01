# Microservice Application

## Example POST credit request:
Method: POST

URL: http://localhost:8300/credits

Response body: 

```
{
	"firstName": "Example name",
	"surname": "Example surname",
	"pesel": "3123889831",
	"productName": "product name ",
	"productValue": 60000,
	"creditName": "Credit name"
}
```

## Example GET credits by creditsIds request
Method: GET

URL: http://localhost:8300/credits/creditsIds=1161946666,1694286282
Response body: 
```
[
    {
        "customer": {
            "firstName": "Name 1",
            "surname": "Surname 1",
            "pesel": "3228314131319"
        },
        "product": {
            "productName": "Product name 1",
            "productValue": 60000
        },
        "credit": {
            "creditName": "Credit name 1"
        }
    },
    {
        "customer": {
            "firstName": "Name 2",
            "surname": "Surname 2",
            "pesel": "3228314131319"
        },
        "product": {
            "productName": "Product name 2",
            "productValue": 60000
        },
        "credit": {
            "creditName": "Credit name 2"
        }
    }
]
```

# Services

| Service name | Port |
|-------------|------|
| credit-service | 8300 |
| customer-service | 8200 |
| product-service | 8100 |

## Docker commands

## Credit service 

```docker build -t credit-service . && docker run -td --restart=always -p 8300:8300 credit-service```

## Customer service 

```docker build -t customer-service . && docker run -td --restart=always -p 8200:8200 customer-service```

## Product service 

```docker build -t product-service . && docker run -td --restart=always -p 8100:8100 product-service```
