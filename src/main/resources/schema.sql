CREATE TABLE IF NOT EXISTS user_data (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     user_id BIGINT NOT NULL,
     username VARCHAR(255) NOT NULL,
     data VARCHAR(255),
     CONSTRAINT user_id_username_unique UNIQUE (user_id, username)
);
