CREATE database model
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE TABLE userGroups(
                         id INT auto_increment PRIMARY KEY,
                         name VARCHAR (255) NOT NULL
);
CREATE TABLE users(
                    id BIGINT auto_increment PRIMARY KEY,
                    username VARCHAR(255) NOT NULL,
                    email VARCHAR(255) NOT NULL UNIQUE,
                    password VARCHAR(255),
                    usergroup_id INT NOT NULL,
                    FOREIGN KEY (usergroup_id) REFERENCES userGroups(id)
);
CREATE TABLE exercises(
                       id INT auto_increment PRIMARY KEY,
                       title VARCHAR(255),
                       description TEXT
);
CREATE TABLE solutions(
                       id INT auto_increment PRIMARY KEY,
                       created DATETIME NOT NULL,
                       updated DATETIME,
                       description TEXT,
                       exercise_id INT NOT NULL,
                       user_id BIGINT NOT NULL,
                       FOREIGN KEY (exercise_id) REFERENCES exercises(id) ON DELETE CASCADE,
                       FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
