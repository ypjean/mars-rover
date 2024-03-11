CREATE TABLE rovers (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    x_axis INT NOT NULL,
    y_axis INT NOT NULL,
    direction CHAR(1) NOT NULL
);