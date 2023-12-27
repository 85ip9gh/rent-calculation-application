# rent-calculation-application

## 12/26/2023
- create table tenants(
    roomNumber INT,
    name VARCHAR(255),
    address VARCHAR(255),
    rent DOUBLE,
    advance DOUBLE,
    dateRented DATE, 
    dateVacated DATE, 
    currentlyRented BOOLEAN,
    agreementValid BOOLEAN,
    dues DOUBLE,
    notes VARCHAR(255)
)
- insert into tenants values(
    12,
    'Sam Man',
    'Random Address',
    12000,
    120,
    '2023-02-14', 
    '2023-03-14', 
    0,
    1,
    0,
    'none'
)