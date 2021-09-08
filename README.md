# Code-Challenge

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

