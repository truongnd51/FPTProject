CREATE TABLE Doctor (
    doctor_id INTEGER PRIMARY KEY AUTOINCREMENT,
    doctor_name TEXT,
    doctor_username TEXT,
    doctor_password TEXT,
    doctor_email TEXT,
    doctor_phone TEXT,
    doctor_image TEXT
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
    patient_id INTEGER,
    doctor_id INTEGER,
    time_start TIMESTAMP,
    time_end TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES Patient(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id)
);

CREATE TABLE Report (
    report_id INTEGER PRIMARY KEY AUTOINCREMENT,
    doctor_id INTEGER,
    patient_id INTEGER,
    disease_id INTEGER,
    schedule_id INTEGER,
    is_paid BIT,
    FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id),
    FOREIGN KEY (patient_id) REFERENCES Patient(patient_id),
    FOREIGN KEY (disease_id) REFERENCES Disease(disease_id),
    FOREIGN KEY (schedule_id) REFERENCES Schedule(schedule_id)
);

INSERT INTO Doctor (doctor_name, doctor_username, doctor_password, doctor_email, doctor_phone, doctor_image)
VALUES ("Doctor A", "truong", "truong","truong@gmail.com", "0386713388",NULL);

INSERT INTO Doctor (doctor_name, doctor_username, doctor_password, doctor_email, doctor_phone, doctor_image)
VALUES ("Doctor B", "truong2", "truong","truong2@gmail.com", "0386713387",NULL);

INSERT INTO Doctor (doctor_name, doctor_username, doctor_password, doctor_email, doctor_phone, doctor_image)
VALUES ("Doctor C", "truong4", "truong","truong3@gmail.com", "0386713386",NULL);
INSERT INTO Doctor (doctor_name, doctor_username, doctor_password, doctor_email, doctor_phone, doctor_image)
VALUES ("Doctor D", "truong5", "truong","truong3@gmail.com", "0386713386",NULL);
INSERT INTO Doctor (doctor_name, doctor_username, doctor_password, doctor_email, doctor_phone, doctor_image)
VALUES ("Doctor E", "truong6", "truong","truong3@gmail.com", "0386713386",NULL);
INSERT INTO Doctor (doctor_name, doctor_username, doctor_password, doctor_email, doctor_phone, doctor_image)
VALUES ("Doctor F", "truong7", "truong","truong3@gmail.com", "0386713386",NULL);

INSERT INTO Patient (patient_name, patient_username, patient_password, patient_email, patient_phone)
VALUES ("Duy Truong", "duytruong", "truong","duytruong@gmail.com", "0386713380");

INSERT INTO Patient (patient_name, patient_username, patient_password, patient_email, patient_phone)
VALUES ("DT", "DT", "truong","dt@gmail.com", "0386713382");
INSERT INTO Disease (disease_name, disease_description)
VALUES ("Thoái hóa cột sống cổ", "Đau tại vị trí thoái hóa, đau tay, tay tê bì.");
INSERT INTO Disease (disease_name, disease_description)
VALUES ("Thoái hóa cột sống lưng", "Đau tại vị trí thoái hóa (Đau lưng) đau lưng, tê chân, ngồi lâu sẽ bị mỏi, buốt lưng");
INSERT INTO Disease (disease_name, disease_description)
VALUES ("Thoát vị đĩa đệm", "Đau tại vị trí thoái hóa (Đau lưng) đau lưng, tê chân, ngồi lâu sẽ bị mỏi, buốt lưng");
INSERT INTO Disease (disease_name, disease_description)
VALUES ("Đau dây thần kinh liên sườn", "Đau quanh lồng ngực, thay đổi tư thế bị đau");
INSERT INTO Disease (disease_name, disease_description)
VALUES ("Viêm quanh khớp vai", "Đau vai, tay, khó giơ tay khi mặc áo");
INSERT INTO Disease (disease_name, disease_description)
VALUES ("Đau đầu, đau nửa đầu", "Đau đầu");
INSERT INTO Disease (disease_name, disease_description)
VALUES ("Thiểu năng tuần hoàn não", "Đau đầu, tê bì đầu, tê tay");
INSERT INTO Disease (disease_name, disease_description)
VALUES ("Hen mãn tính", "Khó thở, thở gấp");
