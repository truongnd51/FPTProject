--CREATE TABLE Clinic (
--    clinic_id INTEGER PRIMARY KEY AUTOINCREMENT,
--    clinic_name TEXT,
--    clinic_address TEXT,
--    clinic_details TEXT
--);
CREATE TABLE Doctor (
    doctor_id INTEGER PRIMARY KEY AUTOINCREMENT,
    doctor_name TEXT,
    doctor_username TEXT,
    doctor_password TEXT,
    doctor_email TEXT,
    doctor_phone TEXT,
    doctor_image TEXT,
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
CREATE TABLE Schedule (
    schedule_id INTEGER PRIMARY KEY AUTOINCREMENT,
    patient_id
    doctor_id
    time_start TIMESTAMP,
    time_end TIMESTAMP,
CREATE TABLE Report (
    report_id INTEGER PRIMARY KEY AUTOINCREMENT,
    doctor_id INTEGER,
    patient_id INTEGER,
    disease_id INTEGER,
    schedule_id INTEGER,
--    time_start TIMESTAMP,
--    time_end TIMESTAMP,
    is_paid BIT,
    FOREIGN KEY (doctor_id) REFERENCES Doctor (doctor_id),
    FOREIGN KEY (patient_id) REFERENCES Patient (patient_id),
    FOREIGN KEY (disease_id) REFERENCES Disease (disease_id)
    FOREIGN KEY (schedule_id) REFERENCES Schedule (schedule_id)
);
INSERT INTO Doctor (doctor_id, doctor_name, doctor_username, doctor_password, doctor_email, doctor_phone, doctor_image)
VALUES (1, "Duy Truong", "truong", "truong","truong@gmail.com", "0386713388",NULL);
INSERT INTO Doctor (doctor_id, doctor_name, doctor_username, doctor_password, doctor_email, doctor_phone, doctor_image)
VALUES (2, "Duy Truong2", "truong2", "truong","truong2@gmail.com", "0386713387",NULL);
INSERT INTO Doctor (doctor_id, doctor_name, doctor_username, doctor_password, doctor_email, doctor_phone, doctor_image)
VALUES (3, "Duy Truong3", "truong3", "truong","truong3@gmail.com", "0386713386",NULL);
INSERT INTO Patient (patient_id, patient_name, patient_username, patient_password, patient_email, patient_phone)
VALUES (1, "Duy Truong", "duytruong", "truong","duytruong@gmail.com", "0386713380",NULL);
INSERT INTO Patient (patient_id, patient_name, patient_username, patient_password, patient_email, patient_phone)
VALUES (2, "DT", "DT", "truong","dt@gmail.com", "0386713382",NULL);



