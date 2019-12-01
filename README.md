# Microservice Application

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
