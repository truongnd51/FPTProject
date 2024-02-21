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


