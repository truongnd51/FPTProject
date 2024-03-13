package com.example.fptproject.uis;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fptproject.R;
import com.example.fptproject.adapters.PhieuKhamAdapter;
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.databases.PrefManager;
import com.example.fptproject.databases.repositories.BookingRepository;
import com.example.fptproject.databases.repositories.DiseaseRepository;
import com.example.fptproject.databases.repositories.DoctorRepository;
import com.example.fptproject.databases.repositories.PatientRepository;
import com.example.fptproject.models.Booking;
import com.example.fptproject.models.Disease;
import com.example.fptproject.models.Doctor;
import com.example.fptproject.models.Patient;
import com.example.fptproject.models.PhieuKham;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhieuKhamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhieuKhamFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView rcv;
    DBHelper dbHelper;
    DoctorRepository doctorRepository;
    PatientRepository patientRepository;
    BookingRepository bookingRepository;
    DiseaseRepository diseaseRepository;
    PhieuKhamAdapter phieuKhamAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhieuKhamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhieuKhamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhieuKhamFragment newInstance(String param1, String param2) {
        PhieuKhamFragment fragment = new PhieuKhamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phieu_kham, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        List<PhieuKham> list =new ArrayList<>();
        String username = PrefManager.getString(getContext(),"username");
        if(doctorRepository.getDoctorByDoctorUsername(username)!=null){
            List<Booking> listBook1 = bookingRepository.getAllBookingByDoctorId(doctorRepository.getDoctorIdByDoctorUsername(PrefManager.getString(getContext(),"username")));
            for(Booking booking : listBook1){
                Doctor doctor=doctorRepository.getDoctorByDoctorId(booking.getDoctorId());
                Patient patient=patientRepository.getPatientByPatientId(booking.getPatientId());
                String benh=diseaseRepository.getDiseaseNameByDiseaseId(booking.getBenh());
                PhieuKham phieuKham = new PhieuKham(doctor.getName(), doctor.getPhone(),patient.getName(),patient.getPhone(), booking.getNgay(), booking.getGio(),benh,doctor.getPrice());
                list.add(phieuKham);
            }
        }else{
            List<Booking> listBook = bookingRepository.getAllBookingByPatientId(patientRepository.getPatientIdByPatientUsername(PrefManager.getString(getContext(),"username")));
            for(Booking booking : listBook){
                Doctor doctor=doctorRepository.getDoctorByDoctorId(booking.getDoctorId());
                Patient patient=patientRepository.getPatientByPatientId(booking.getPatientId());
                String benh=diseaseRepository.getDiseaseNameByDiseaseId(booking.getBenh());
                PhieuKham phieuKham = new PhieuKham(doctor.getName(), doctor.getPhone(),patient.getName(),patient.getPhone(), booking.getNgay(), booking.getGio(),benh,doctor.getPrice());
                list.add(phieuKham);
            }
        }

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);
        phieuKhamAdapter=new PhieuKhamAdapter(list);
        rcv.setAdapter(phieuKhamAdapter);
    }

    private void init(View view) {
        dbHelper=new DBHelper(getContext());
        doctorRepository=new DoctorRepository(dbHelper);
        patientRepository=new PatientRepository(dbHelper);
        bookingRepository = new BookingRepository(dbHelper);
        diseaseRepository=new DiseaseRepository(dbHelper);
        rcv=view.findViewById(R.id.rcvPhieuKham);
    }
}