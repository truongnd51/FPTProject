package com.example.fptproject.uis;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fptproject.R;
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.databases.PrefManager;
import com.example.fptproject.databases.repositories.BookingRepository;
import com.example.fptproject.databases.repositories.DiseaseRepository;
import com.example.fptproject.databases.repositories.DoctorRepository;
import com.example.fptproject.databases.repositories.PatientRepository;
import com.example.fptproject.models.Booking;
import com.example.fptproject.models.Doctor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BookingAcitivty extends AppCompatActivity {
    Spinner spGio,spDoctor,spBenh;
    EditText edtNgay;
    Button btnConfirm;
    ImageButton imgOpenDatePicker;
    static String date;
    DBHelper dbHelper;
    DoctorRepository doctorRepository;
    List<String> listTime;
    BookingRepository bookingRepository;
    DiseaseRepository diseaseRepository;
    PatientRepository patientRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_acitivty);
        initView();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        date = dayOfMonth + "-" + month + "-" + year;
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = calendar.getTime();
        String time = timeFormat.format(currentTime);
        //khi bam vao icon datepicker
        imgOpenDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a DatePickerDialog and show it
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        BookingAcitivty.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                                String selectedDate = formatDate(selectedYear, selectedMonth, selectedDay);
                                edtNgay.setText(selectedDate);
                                int doctorId = doctorRepository.getDoctorIdByDoctorName(spDoctor.getSelectedItem().toString());
                                listTime=new ArrayList<>();
                                listTime.add("7:00-8:00");
                                listTime.add("8:00-9:00");
                                listTime.add("9:00-10:00");
                                listTime.add("10:00-11:00");
                                listTime.add("13:00-14:00");
                                listTime.add("14:00-15:00");
                                listTime.add("15:00-16:00");
                                listTime.add("16:00-17:00");
                                List<String> timeInDB = bookingRepository.listGioByDoctorAndDate(doctorId,selectedDate);
                                //remove nhung giờ đã dc đặt
                                for (String time : timeInDB){
                                    listTime.remove(time);
                                }

                                ArrayAdapter<String> adapterGio = new ArrayAdapter<>(BookingAcitivty.this, android.R.layout.simple_spinner_item,listTime);
                                adapterGio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spGio.setAdapter(adapterGio);
                            }
                        },
                        year,
                        month,
                        dayOfMonth
                );
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
            private String formatDate(int year, int month, int day) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                return sdf.format(calendar.getTime());
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingAcitivty.this, MainActivity.class);
                startActivity(intent);
                sendNotification();
                String name=spDoctor.getSelectedItem().toString().trim();
                String gio=spGio.getSelectedItem().toString().trim();
                String ngay=edtNgay.getText().toString().trim();
                String benh=spBenh.getSelectedItem().toString().trim();
                if(name.length()>0 && gio.length()>0&&ngay.length()>0&&benh.length()>0){
                    int doctorId=doctorRepository.getDoctorIdByDoctorName(name);
                    int patientId=patientRepository.getPatientIdByPatientUsername(PrefManager.getString(BookingAcitivty.this,"username"));
                    int diseaseId=diseaseRepository.getDiseaseIdByDiseaseName(benh);
                    bookingRepository.addBooking(doctorId,patientId,ngay,gio,diseaseId);
                    Toast.makeText(BookingAcitivty.this, "Đặt lịch thành công", Toast.LENGTH_SHORT).show();
                    spDoctor.setSelection(0);
                    edtNgay.setText("");
                    spBenh.setSelection(0);
                    spGio.setSelection(0);
                }else{
                    Toast.makeText(BookingAcitivty.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initView() {
        spGio=findViewById(R.id.spGio);
        spDoctor=findViewById(R.id.spDoctor);
        spBenh=findViewById(R.id.spBenh);
        edtNgay=findViewById(R.id.edtNgay);
        btnConfirm=findViewById(R.id.btnConfirm);
        imgOpenDatePicker=findViewById(R.id.btnOpenDatePicker);
        dbHelper=new DBHelper(BookingAcitivty.this);
        bookingRepository=new BookingRepository(dbHelper);
        doctorRepository=new DoctorRepository(dbHelper);
        diseaseRepository =new DiseaseRepository(dbHelper);
        patientRepository=new PatientRepository(dbHelper);
        List<String> listDoctorName= doctorRepository.getListDoctorName();
        //set du lieu cho spinner
        ArrayAdapter<String> adapterDoctor = new ArrayAdapter<>(BookingAcitivty.this, android.R.layout.simple_spinner_item,listDoctorName);
        adapterDoctor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDoctor.setAdapter(adapterDoctor);
        List<String> listBenh= diseaseRepository.getAllDiseaseNames();
        ArrayAdapter<String> adapterDisease = new ArrayAdapter<>(BookingAcitivty.this, android.R.layout.simple_spinner_item,listBenh);
        adapterDisease.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBenh.setAdapter(adapterDisease);
        spDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int doctorId = doctorRepository.getDoctorIdByDoctorName(spDoctor.getSelectedItem().toString());
                listTime=new ArrayList<>();
                listTime.add("7:00-8:00");
                listTime.add("8:00-9:00");
                listTime.add("9:00-10:00");
                listTime.add("10:00-11:00");
                listTime.add("13:00-14:00");
                listTime.add("14:00-15:00");
                listTime.add("15:00-16:00");
                listTime.add("16:00-17:00");
                List<String> timeInDB = bookingRepository.listGioByDoctorAndDate(doctorId,edtNgay.getText().toString());
                for (String time : timeInDB){
                    listTime.remove(time);
                }
                ArrayAdapter<String> adapterGio = new ArrayAdapter<>(BookingAcitivty.this, android.R.layout.simple_spinner_item,listTime);
                adapterGio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spGio.setAdapter(adapterGio);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private String formatDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return sdf.format(calendar.getTime());
    }

    private void sendNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){

        }
        final String CHANNEL_ID = "001";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_action_notification)
                .setContentTitle("Thông báo xác nhận đặt lịch khám thành công _ MedF")
                .setContentText("Cảm ơn quý khách đã đặt lịch khám tại MedF, lịch khám của quá khách đã được lưu lại và gửi tới bác sĩ, xin quý khách vui lòng đến đúng giờ.");
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, intent, PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Demo channel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(2, builder.build());
    }
}