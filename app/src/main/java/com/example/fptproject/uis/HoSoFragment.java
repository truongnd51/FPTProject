package com.example.fptproject.uis;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fptproject.R;
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.databases.PrefManager;
import com.example.fptproject.databases.repositories.AdminRepository;
import com.example.fptproject.databases.repositories.DoctorRepository;
import com.example.fptproject.databases.repositories.PatientRepository;
import com.example.fptproject.models.Admin;
import com.example.fptproject.models.Doctor;
import com.example.fptproject.models.Patient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HoSoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HoSoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DBHelper dbHelper;
    DoctorRepository doctorRepository;
    PatientRepository patientRepository;
    AdminRepository adminRepository;
    TextView tvName,tvEmail,tvPhone,tv_update;

    public HoSoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HoSoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HoSoFragment newInstance(String param1, String param2) {
        HoSoFragment fragment = new HoSoFragment();
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
        return inflater.inflate(R.layout.fragment_ho_so, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String username= PrefManager.getString(getContext(),"username");
        dbHelper=new DBHelper(getContext());
        tvName=view.findViewById(R.id.tvNameHoSo);
        tvEmail=view.findViewById(R.id.tvEmailHoSo);
        tvPhone=view.findViewById(R.id.tvPhoneHoSo);

        tv_update=view.findViewById(R.id.tv_update);
        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= NavHostFragment.findNavController(HoSoFragment.this);
                navController.navigate(R.id.action_hoSoFragment_to_updateFragment);
            }
        });
        doctorRepository=new DoctorRepository(dbHelper);
        patientRepository=new PatientRepository(dbHelper);
        adminRepository=new AdminRepository(dbHelper);
        if(doctorRepository.getDoctorByDoctorUsername(username)!=null){
            Doctor doctor=doctorRepository.getDoctorByDoctorUsername(username);
            tvName.setText(doctor.getName());
            tvPhone.setText(doctor.getPhone());
            tvEmail.setText(doctor.getEmail());

        }else if (adminRepository.getAdminByUsername(username)!=null) {
            Admin admin = adminRepository.getAdminByUsername(username);
            tvName.setText(admin.getName());
        }else{
            Patient patient = patientRepository.getPatientByPatientUsername(username);
            tvName.setText(patient.getName());
            tvPhone.setText(patient.getPhone());
            tvEmail.setText(patient.getEmail());
        }
    }
}