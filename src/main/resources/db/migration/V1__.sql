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

CREATE TABLE authorization
(
    id                            VARCHAR(255) NOT NULL,
    registered_client_id          VARCHAR(255) NULL,
    principal_name                VARCHAR(255) NULL,
    authorization_grant_type      VARCHAR(255) NULL,
    authorized_scopes             TEXT NULL,
    attributes                    TEXT NULL,
    state                         TEXT NULL,
    authorization_code_value      TEXT NULL,
    authorization_code_issued_at  datetime NULL,
    authorization_code_expires_at datetime NULL,
    authorization_code_metadata   VARCHAR(255) NULL,
    access_token_value            TEXT NULL,
    access_token_issued_at        datetime NULL,
    access_token_expires_at       datetime NULL,
    access_token_metadata         TEXT NULL,
    access_token_type             VARCHAR(255) NULL,
    access_token_scopes           TEXT NULL,
    refresh_token_value           TEXT NULL,
    refresh_token_issued_at       datetime NULL,
    refresh_token_expires_at      datetime NULL,
    refresh_token_metadata        TEXT NULL,
    oidc_id_token_value           TEXT NULL,
    oidc_id_token_issued_at       datetime NULL,
    oidc_id_token_expires_at      datetime NULL,
    oidc_id_token_metadata        TEXT NULL,
    oidc_id_token_claims          TEXT NULL,
    user_code_value               TEXT NULL,
    user_code_issued_at           datetime NULL,
    user_code_expires_at          datetime NULL,
    user_code_metadata            TEXT NULL,
    device_code_value             TEXT NULL,
    device_code_issued_at         datetime NULL,
    device_code_expires_at        datetime NULL,
    device_code_metadata          TEXT NULL,
    CONSTRAINT pk_authorization PRIMARY KEY (id)
);

CREATE TABLE authorization_consent
(
    registered_client_id VARCHAR(255) NOT NULL,
    principal_name       VARCHAR(255) NOT NULL,
    authorities          TEXT NULL,
    CONSTRAINT pk_authorizationconsent PRIMARY KEY (registered_client_id, principal_name)
);

CREATE TABLE client
(
    id                            VARCHAR(255) NOT NULL,
    client_id                     VARCHAR(255) NULL,
    client_id_issued_at           datetime NULL,
    client_secret                 VARCHAR(255) NULL,
    client_secret_expires_at      datetime NULL,
    client_name                   VARCHAR(255) NULL,
    client_authentication_methods TEXT NULL,
    authorization_grant_types     TEXT NULL,
    redirect_uris                 TEXT NULL,
    post_logout_redirect_uris     TEXT NULL,
    scopes                        TEXT NULL,
    client_settings               TEXT NULL,
    token_settings                TEXT NULL,
    CONSTRAINT pk_client PRIMARY KEY (id)
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

CREATE TABLE `role`
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE token
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    value     VARCHAR(255) NULL,
    expiry_at datetime NULL,
    user_id   BIGINT NULL,
    deleted   BIT(1) NOT NULL,
    CONSTRAINT pk_token PRIMARY KEY (id)
);

CREATE TABLE user
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    email             VARCHAR(255) NULL,
    hashed_password   VARCHAR(255) NULL,
    name_id           BIGINT NULL,
    address_id        BIGINT NULL,
    phone             VARCHAR(255) NULL,
    is_email_verified BIT(1) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_role_list
(
    user_id      BIGINT NOT NULL,
    role_list_id BIGINT NOT NULL
);

ALTER TABLE address
    ADD CONSTRAINT FK_ADDRESS_ON_GEOLOCATION FOREIGN KEY (geolocation_id) REFERENCES geolocation (id);

ALTER TABLE token
    ADD CONSTRAINT FK_TOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_NAME FOREIGN KEY (name_id) REFERENCES name (id);

ALTER TABLE user_role_list
    ADD CONSTRAINT fk_userollis_on_role FOREIGN KEY (role_list_id) REFERENCES `role` (id);

ALTER TABLE user_role_list
    ADD CONSTRAINT fk_userollis_on_user FOREIGN KEY (user_id) REFERENCES user (id);