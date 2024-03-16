CREATE TABLE address (
    id SERIAL PRIMARY KEY,
    street VARCHAR(255),
    city VARCHAR(255),
    postalCode VARCHAR(20),
    country VARCHAR(100)
);


CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    fullname VARCHAR(255),
    address_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES address (id)
);


CREATE TABLE contact (
    id SERIAL PRIMARY KEY,
    type VARCHAR(10),
    contact_info VARCHAR(40),
    customer_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE clothes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(40),
    size VARCHAR(10),
    color VARCHAR(50),
    customer_id BIGINT,
    status_code VARCHAR(40)
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE status (
  id SERIAL PRIMARY KEY,
  code VARCHAR(40) NOT NULL UNIQUE,
  description VARCHAR(255)
);

CREATE TABLE user (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255),
    fullname VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(50),
    created TIMESTAMP,
    updated TIMESTAMP,
    deleted TIMESTAMP,
    FOREIGN KEY (role) REFERENCES role (code)
);

INSERT INTO status (code, description) VALUES
  ('READY_FOR_WASHING', 'Clothes are ready to be washed'),
  ('IN_WASHING', 'Clothes are currently being washed'),
  ('IN_DRYING', 'Clothes are currently drying'),
  ('READY_FOR_CUSTOMER', 'Clothes are clean and ready for pick-up'),
  ('PICKED_BY_CUSTOMER', 'Clothes have been picked up by the customer');

  CREATE TABLE status_transition (
  id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  current_status_code VARCHAR(40) NOT NULL,
  next_status_code VARCHAR(40) NOT NULL,
  CONSTRAINT fk_current_status FOREIGN KEY (current_status_code) REFERENCES status(code) ON DELETE CASCADE,
  CONSTRAINT fk_next_status FOREIGN KEY (next_status_code) REFERENCES status(code) ON DELETE CASCADE
);

INSERT INTO status_transition (current_status_code, next_status_code)
VALUES ('READY_FOR_WASHING', 'IN_WASHING'),
       ('IN_WASHING', 'READY_FOR_WASHING'),
       ('IN_WASHING', 'IN_DRYING'),
       ('IN_DRYING', 'IN_WASHING'),
       ('IN_DRYING', 'READY_FOR_CUSTOMER'),
       ('READY_FOR_CUSTOMER', 'IN_DRYING'),
       ('READY_FOR_CUSTOMER', 'PICKED_BY_CUSTOMER');

CREATE TABLE product (
    id UUID primary key,
    name VARCHAR(50),
    description VARCHAR(255),
    status_code VARCHAR(50),
    customer_id INT,
    current_user_id INT,
    created TIMESTAMP,
    updated TIMESTAMP,
    deleted TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (current_user_id) REFERENCES "user" (id),
    FOREIGN KEY (status_code) REFERENCES status (code)
)

CREATE TABLE characteristics (
    id UUID PRIMARY KEY,
    product_id UUID,
    code VARCHAR(20),
    value VARCHAR(50),
    global_code VARCHAR(20),
    FOREIGN KEY (product_id) REFERENCES product (id),
    FOREIGN KEY (code) REFERENCES characteristics_specification (code)
);

CREATE TABLE characteristics_specification (
    code VARCHAR(20) PRIMARY KEY,
    global_code VARCHAR(20)
);

INSERT INTO characteristics_specification (code, global_code)
VALUES ('BIJELO', 'ZLATO'),
       ('ŽUTO', 'ZLATO'),
       ('ROZO', 'ZLATO'),
       ('TEŽINA', 'TEŽINA'),
       ('ŠIRINA', 'ŠIRINA'),
       ('VELIČINA', 'VELIČINA'),
       ('BRILJANTI', 'KAMEN'),
       ('PRINCEZA', 'KAMEN'),
       ('SRCE', 'KAMEN'),
       ('OVAL', 'KAMEN');

CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    code VARCHAR(55) UNIQUE
);

INSERT INTO role (code)
VALUES ('ADMIN'),('FASER'),('POLIRER'),('RUČNI RAD'),('CNC'),('NARUDŽBA')


