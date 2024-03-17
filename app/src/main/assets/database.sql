CREATE TABLE Doctor (
    doctor_id INTEGER PRIMARY KEY AUTOINCREMENT,
    doctor_name TEXT,
    doctor_username TEXT,
    doctor_password TEXT,
    doctor_price TEXT,
    doctor_email TEXT,
    doctor_phone TEXT,
    doctor_description TEXT,
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

create table Booking(
    doctor_id INTEGER,
    patient_id INTEGER,
    ngay TEXT,
    gio TEXT,
    disease_id,
    FOREIGN KEY (patient_id) REFERENCES Patient(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id),
    FOREIGN KEY (disease_id) REFERENCES Disease(disease_id)
);
CREATE TABLE Admin (
    admin_id INTEGER PRIMARY KEY AUTOINCREMENT,
    admin_name TEXT,
    admin_username TEXT,
    admin_password TEXT
);
insert into Admin(admin_name,admin_username,admin_password)
values("Admin", "admin", "admin");
insert into Booking(doctor_id,patient_id,ngay,gio,disease_id)
values(2,1,"14-03-2024","7:00-8:00",1);
insert into Booking(doctor_id,patient_id,ngay,gio,disease_id)
values(2,2,"14-03-2024","8:00-9:00",2);

INSERT INTO Doctor (doctor_name, doctor_username, doctor_password, doctor_price, doctor_email, doctor_phone, doctor_description, doctor_image)
VALUES ("Lưu Sinh Cơ", "truong", "truong", "300.000 VND" ,"truong@gmail.com", "0386713388", "Có trên 10 năm kinh nghiệm và chữa được nhiều loại bệnh. Đã từng chữa khỏi cho nhiều bệnh nhân mắc phải các căn bệnh khó chữa." ,"https://hongngochospital.vn/wp-content/uploads/2020/02/168-Luu-Sinh-Co.jpg");

INSERT INTO Doctor (doctor_name, doctor_username, doctor_password, doctor_price, doctor_email, doctor_phone, doctor_description, doctor_image)
VALUES ("Trần Đức Tuấn","truong2", "truong2", "200.000 VND" ,"truong2@gmail.com", "0386713387", "Có trên 10 năm kinh nghiệm và chữa được nhiều loại bệnh. Đã từng chữa khỏi cho nhiều bệnh nhân mắc phải các căn bệnh khó chữa." ,"https://hongngochospital.vn/wp-content/uploads/2020/03/bs-Tran-Duc-Tuan.jpg");

INSERT INTO Doctor (doctor_name, doctor_username, doctor_password, doctor_price, doctor_email, doctor_phone, doctor_description, doctor_image)
VALUES ("Mai Hồng Vân", "truong3", "truong3", "150.000 VND" ,"truong3@gmail.com", "0386713386", "Có trên 10 năm kinh nghiệm và chữa được nhiều loại bệnh. Đã từng chữa khỏi cho nhiều bệnh nhân mắc phải các căn bệnh khó chữa." ,"https://hongngochospital.vn/wp-content/uploads/2020/02/317-Mai-Hong-Van.jpg");

INSERT INTO Doctor (doctor_name, doctor_username, doctor_password, doctor_price, doctor_email, doctor_phone, doctor_description, doctor_image)
VALUES ("Nguyễn Tuấn Anh", "truong4", "truong4", "200.000 VND" ,"truong4@gmail.com", "0386713385", "Có trên 10 năm kinh nghiệm và chữa được nhiều loại bệnh. Đã từng chữa khỏi cho nhiều bệnh nhân mắc phải các căn bệnh khó chữa." ,"https://hongngochospital.vn/wp-content/uploads/2020/02/309-Nguyen-Tuan-Anh.jpg");

INSERT INTO Doctor (doctor_name, doctor_username, doctor_password, doctor_price, doctor_email, doctor_phone, doctor_description, doctor_image)
VALUES ("Đỗ Thị Hương", "truong5", "truong5", "200.000 VND" ,"truong5@gmail.com", "0386713384", "Có trên 10 năm kinh nghiệm và chữa được nhiều loại bệnh. Đã từng chữa khỏi cho nhiều bệnh nhân mắc phải các căn bệnh khó chữa." ,"https://hongngochospital.vn/wp-content/uploads/2020/02/228-Do-Thi-Huong-2.jpg");

INSERT INTO Doctor (doctor_name, doctor_username, doctor_password, doctor_price, doctor_email, doctor_phone, doctor_description, doctor_image)
VALUES ("Nguyễn Thị Nga", "truong6", "truong6", "150.000 VND" ,"truong6@gmail.com", "0386713383", "Có trên 10 năm kinh nghiệm và chữa được nhiều loại bệnh. Đã từng chữa khỏi cho nhiều bệnh nhân mắc phải các căn bệnh khó chữa." ,"https://hongngochospital.vn/wp-content/uploads/2020/02/12-Nguyen-Thi-Nga-2.jpg");



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
