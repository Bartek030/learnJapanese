CREATE TABLE app_user
(
    id          SERIAL          NOT NULL,
    email       VARCHAR(64)     NOT NULL,
    password    VARCHAR(128)    NOT NULL,
    first_name  VARCHAR(16)     NOT NULL,
    last_name   VARCHAR(32)     NOT NULL,
    active      BOOLEAN         NOT NULL,
    locked      BOOLEAN         NOT NULL,
    role        VARCHAR(16)     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE      (email)
);