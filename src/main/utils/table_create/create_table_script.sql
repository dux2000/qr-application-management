CREATE TABLE address
(
    id         SERIAL PRIMARY KEY,
    street     VARCHAR(255),
    city       VARCHAR(255),
    postalCode VARCHAR(20),
    country    VARCHAR(100)
);


CREATE TABLE customer
(
    id         SERIAL PRIMARY KEY,
    fullname   VARCHAR(255),
    address_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES address (id)
);


CREATE TABLE contact
(
    id           SERIAL PRIMARY KEY,
    type         VARCHAR(10),
    contact_info VARCHAR(40),
    customer_id  BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE status
(
    code        VARCHAR(40) PRIMARY KEY,
    name        VARCHAR(40) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE status_transition
(
    id                  BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    current_status_code VARCHAR(40) NOT NULL,
    next_status_code    VARCHAR(40) NOT NULL,
    CONSTRAINT fk_current_status FOREIGN KEY (current_status_code) REFERENCES status (code) ON DELETE CASCADE,
    CONSTRAINT fk_next_status FOREIGN KEY (next_status_code) REFERENCES status (code) ON DELETE CASCADE
);

CREATE TABLE user
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    fullname VARCHAR(255),
    password VARCHAR(255),
    created  TIMESTAMP,
    updated  TIMESTAMP,
    deleted  TIMESTAMP,
    update   BOOLEAN
);

CREATE TABLE product
(
    id              UUID primary key,
    name            VARCHAR(50),
    description     VARCHAR(255),
    status_code     VARCHAR(50),
    type_code       VARCHAR(50),
    customer_id     INT,
    current_user_id INT,
    created         TIMESTAMP,
    created_by      INT,
    updated         TIMESTAMP,
    updated_by      INT,
    deleted         TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (current_user_id) REFERENCES "user" (id),
    FOREIGN KEY (status_code) REFERENCES status (code),
    FOREIGN KEY (created_by) REFERENCES "user" (id),
    FOREIGN KEY (updated_by) REFERENCES "user" (id),
    FOREIGN KEY (type_code) REFERENCES product_type (code);
)

CREATE TABLE characteristics
(
    id          UUID PRIMARY KEY,
    product_id  UUID,
    code        VARCHAR(20),
    value       VARCHAR(50),
    global_code VARCHAR(20),
    FOREIGN KEY (product_id) REFERENCES product (id),
    FOREIGN KEY (code) REFERENCES characteristics_specification (code)
);

CREATE TABLE characteristics_specification
(
    code        VARCHAR(20) PRIMARY KEY,
    global_code VARCHAR(20)
);

create table revinfo
(
    rev      integer generated by default as identity (start with 1),
    revtstmp bigint,
    primary key (rev)
);

CREATE TABLE product_aud
(
    id              UUID,
    rev             integer not null,
    revtype         smallint,
    name            VARCHAR(50),
    description     VARCHAR(255),
    status_code     VARCHAR(50),
    type_code       VARCHAR(50),
    customer_id     INT,
    current_user_id INT,
    created         TIMESTAMP,
    created_by      INT,
    updated         TIMESTAMP,
    updated_by      INT,
    deleted         TIMESTAMP,
    primary key (id, rev),
    foreign key (rev) references revinfo
)

create table product_type
(
    id   SERIAL PRIMARY KEY,
    code VARCHAR(50) UNIQUE,
    name VARCHAR(50),
);

CREATE TABLE user_type
(
    id        SERIAL PRIMARY KEY,
    user_id   BIGINT       NOT NULL,
    type_code VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE,
    FOREIGN KEY (type_code) REFERENCES user_type_definition (code) ON DELETE CASCADE
);

CREATE TABLE user_type_definition
(
    code VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

create table user_type_product_status_transition
(
    id         SERIAL PRIMARY KEY,
    type_code VARCHAR(40) NOT NULL,
    status_code VARCHAR(40) NOT NULL,
    PRIMARY KEY (type_code, status_code),
    FOREIGN KEY (type_code) REFERENCES user_type_definition (code),
    FOREIGN KEY (status_code) REFERENCES status (code)
)