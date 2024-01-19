CREATE TABLE address
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    city           VARCHAR(255) NULL,
    street         VARCHAR(255) NULL,
    number         VARCHAR(255) NULL,
    zipcode        VARCHAR(255) NULL,
    geolocation_id BIGINT NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE geolocation
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    latitude  VARCHAR(255) NULL,
    longitude VARCHAR(255) NULL,
    CONSTRAINT pk_geolocation PRIMARY KEY (id)
);

CREATE TABLE name
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(255) NULL,
    last_name  VARCHAR(255) NULL,
    CONSTRAINT pk_name PRIMARY KEY (id)
);

CREATE TABLE user
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    username   VARCHAR(255) NULL,
    email      VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    name_id    BIGINT NULL,
    address_id BIGINT NULL,
    phone      VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE address
    ADD CONSTRAINT FK_ADDRESS_ON_GEOLOCATION FOREIGN KEY (geolocation_id) REFERENCES geolocation (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_NAME FOREIGN KEY (name_id) REFERENCES name (id);