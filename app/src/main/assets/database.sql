CREATE TABLE User (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT,
    first_name TEXT,
    last_name TEXT,
    password TEXT,
    image TEXT,
    phone_number TEXT,
    address TEXT
);
CREATE TABLE Menu (
    menu_id INTEGER PRIMARY KEY,
    name TEXT
);
CREATE TABLE Food (
    food_id INTEGER PRIMARY KEY ,
    name TEXT,
    price TEXT,
    image TEXT,
    menu_id INTEGER,
    FOREIGN KEY (menu_id) REFERENCES Menu (menu_id)
);
CREATE TABLE Cart (
    user_id INTEGER,
    food_id INTEGER,
    quantity INTEGER,
    FOREIGN KEY (user_id) REFERENCES User (user_id),
    FOREIGN KEY (food_id) REFERENCES Food (food_id)
);
CREATE TABLE Favourite (
    user_id INTEGER,
    food_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES User (user_id),
    FOREIGN KEY (food_id) REFERENCES Food (food_id)
);