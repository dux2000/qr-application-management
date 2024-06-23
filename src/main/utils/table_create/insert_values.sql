INSERT INTO status (code, name, description)
VALUES ('CREATED', 'Kreirano', 'Item created'),
       ('IN_WASHING', 'U pranju', 'Item is currently being washed'),
       ('IN_WASHING_FIN', 'Pranje završeno', 'Item is washed'),
       ('IN_DRYING', 'U sušenju', 'Item is currently drying'),
       ('IN_DRYING_FIN', 'Sušenje završeno', 'Item is dried'),
       ('IN_IRONING', 'Na peglanju', 'Item is currently on ironing'),
       ('IN_IRONING_FIN', 'Peglanje završeno', 'Item is ironed'),
       ('IN_DELIVERY', 'Na dostavi', 'Item is currently on delivery'),
       ('IN_DELIVERY_FIN', 'Dostava završena', 'Item is delivered'),
       ('READY_FOR_CUSTOMER', 'Spremno za kupca', 'Item is clean and ready for pick-up'),
       ('PICKED_BY_CUSTOMER', 'Kupac preuzeo', 'Item has been picked up by the customer');

INSERT INTO status_transition (current_status_code, next_status_code)
VALUES ('CREATED', 'IN_WASHING'),
       ('CREATED', 'IN_DRYING'),
       ('CREATED', 'IN_IRONING'),
       ('CREATED', 'IN_DELIVERY'),
       ('CREATED', 'READY_FOR_CUSTOMER'),
       ('IN_WASHING', 'IN_WASHING_FIN'),
       ('IN_WASHING_FIN', 'IN_WASHING'),
       ('IN_WASHING_FIN', 'IN_DRYING'),
       ('IN_DRYING', 'IN_DRYING_FIN'),
       ('IN_DRYING_FIN', 'IN_WASHING'),
       ('IN_DRYING_FIN', 'IN_IRONING'),
       ('IN_DRYING_FIN', 'IN_DELIVERY'),
       ('IN_DRYING_FIN', 'READY_FOR_CUSTOMER'),
       ('IN_IRONING', 'IN_IRONING_FIN'),
       ('IN_IRONING_FIN', 'IN_IRONING'),
       ('IN_IRONING_FIN', 'IN_WASHING'),
       ('IN_IRONING_FIN', 'IN_DELIVERY'),
       ('IN_IRONING_FIN', 'READY_FOR_CUSTOMER'),
       ('IN_DELIVERY', 'IN_DELIVERY_FIN'),
       ('READY_FOR_CUSTOMER', 'PICKED_BY_CUSTOMER');

insert into product_type (id, code, name)
values (1, 'T_SHIRT', 'Majica kratkih rukava');
insert into product_type (id, code, name)
values (2, 'LONG_SHIRT', 'Majica dugih rukava');
insert into product_type (id, code, name)
values (3, 'SHORTS', 'Kratke hlače');
insert into product_type (id, code, name)
values (4, 'JEANS', 'Traperice');
insert into product_type (id, code, name)
values (5, 'HOODIE', 'Hudica');
insert into product_type (id, code, name)
values (6, 'DRESS', 'Haljina');
insert into product_type (id, code, name)
values (7, 'JACKET', 'Jakna');
insert into product_type (id, code, name)
values (8, 'PANTIES', 'Gaće');

INSERT INTO user_type_definition (code, name)
VALUES ('ADMIN', 'Administrator');
INSERT INTO user_type_definition (code, name)
VALUES ('NARUDŽBE', 'Narudžbe');
INSERT INTO user_type_definition (code, name)
VALUES ('PEGLANJE', 'Peglanje');
INSERT INTO user_type_definition (code, name)
VALUES ('PRANJE', 'Pranje');
INSERT INTO user_type_definition (code, name)
VALUES ('DOSTAVA', 'Dostava');
INSERT INTO user_type_definition (code, name)
VALUES ('SUSEŠNJE', 'Sušenje');

INSERT INTO user_type_product_status_transition (id, type_code, status_code)
VALUES (1, 'NARUDŽBE', 'CREATED'),
       (2, 'PRANJE', 'IN_WASHING'),
       (3, 'PRANJE', 'IN_WASHING_FIN'),
       (4, 'SUSEŠNJE', 'IN_DRYING'),
       (5, 'SUSEŠNJE', 'IN_DRYING_FIN'),
       (6, 'PEGLANJE', 'IN_IRONING'),
       (7, 'PEGLANJE', 'IN_IRONING_FIN'),
       (8, 'DOSTAVA', 'IN_DELIVERY'),
       (9, 'DOSTAVA', 'IN_DELIVERY_FIN'),
       (10, 'NARUDŽBE', 'READY_FOR_CUSTOMER'),
       (11, 'NARUDŽBE', 'PICKED_BY_CUSTOMER');
