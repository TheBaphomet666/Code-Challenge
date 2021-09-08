# Senior Code-Challenge

###This service the mock and the database are deployed on heroku
### Payments-service: 
###https://payments-service.herokuapp.com
### Mock: https://github.com/TheBaphomet666/mocks
### https://mocks666.herokuapp.com/

### Database:
    url: jdbc:postgresql://ec2-18-235-45-217.compute-1.amazonaws.com:5432/d1a2gmh5ru2ht5
    username: ddmzkaytlsdxdm
    password: 65984a5474bf5eb749e9d1be7d2a359c950a68bb7496f8e4be912816f2795f9e

#Payment Requests
### localhost:18115/payments/purchase
##APPROVED REQUEST
Mock only approves with 32 installments
```JSON
{
    "clientId": "1",
    "payer": {
        "name": "paco",
        "adress": "123",
        "email": "123",
        "number": "123"
    },
    "encryptedCard": "4pbg4a6XYA9oSyKdz/AmnWmud06bR/rGTZhLxua22t2raX4IugPrUw3QNVHNZFwnAVWQH9SrbWkadye7aOJ7W8q6CyNEprqAp46gwRDxBo0=",
    "installmentsNumber": 32,
    "amount": {
        "value":1000,
        "currency": "CO"
    }

}
```

##DECLINED REQUEST
```JSON
{
    "clientId": "1",
    "payer": {
        "name": "paco",
        "adress": "123",
        "email": "123",
        "number": "123"
    },
    "encryptedCard": "4pbg4a6XYA9oSyKdz/AmnWmud06bR/rGTZhLxua22t2raX4IugPrUw3QNVHNZFwnAVWQH9SrbWkadye7aOJ7W8q6CyNEprqAp46gwRDxBo0=",
    "installmentsNumber": 1,
    "amount": {
        "value":1000,
        "currency": "CO"
    }

}
```

##ANTIFRAUD DECLINED REQUEST
Antifraud marks as risk transaction when card holder name is not paco
the cards on the example are encrypted as the service needs
```JSON
{
    "clientId": "1",
    "payer": {
        "name": "paco",
        "adress": "123",
        "email": "123",
        "number": "123"
    },
    "encryptedCard": "4pbg4a6XYA9oSyKdz/AmnWmud06bR/rGTZhLxua22t2raX4IugPrUw3QNVHNZFwnAVWQH9SrbWkadye7aOJ7W5upJ9/+1I6WR7+yVHvQDKI=",
    "installmentsNumber": 1,
    "amount": {
        "value":1000,
        "currency": "CO"
    }

}
```

#LOCAL DEPLOYMENT

mvn clean compile spring-boot:run

