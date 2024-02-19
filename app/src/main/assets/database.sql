<<<<<<< HEAD
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
=======
CREATE TABLE Clinic (
    clinic_id INTEGER PRIMARY KEY AUTOINCREMENT,
    clinic_name TEXT,
    clinic_address TEXT,
    clinic_details TEXT
);
CREATE TABLE Doctor (
    doctor_id INTEGER PRIMARY KEY AUTOINCREMENT,
    clinic_id INTEGER,
    doctor_name TEXT,
    doctor_username TEXT,
    doctor_password TEXT,
    doctor_email TEXT,
    doctor_phone TEXT,
    FOREIGN KEY (clinic_id) REFERENCES Clinic (clinic_id)
);
CREATE TABLE Patient (
    patient_id INTEGER PRIMARY KEY AUTOINCREMENT,
    patient_name TEXT,
    patient_username TEXT,
    patient_password TEXT,
    patient_email TEXT,
    patient_phone TEXT
);
CREATE TABLE Disease (
    disease_id INTEGER PRIMARY KEY AUTOINCREMENT,
    disease_name TEXT,
    disease_description TEXT
);
CREATE TABLE Report (
    report_id INTEGER PRIMARY KEY AUTOINCREMENT,
    doctor_id INTEGER,
    clinic_id INTEGER,
    patient_id INTEGER,
    disease_id INTEGER,
    schedule date,
    time_start TIMESTAMP,
    time_end TIMESTAMP,
    is_paid BIT,
    FOREIGN KEY (clinic_id) REFERENCES Clinic (clinic_id),
    FOREIGN KEY (doctor_id) REFERENCES Doctor (doctor_id),
    FOREIGN KEY (patient_id) REFERENCES Patient (patient_id),
    FOREIGN KEY (disease_id) REFERENCES Disease (disease_id)
);

>>>>>>> origin/master
