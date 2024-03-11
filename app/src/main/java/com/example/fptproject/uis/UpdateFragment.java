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
import android.widget.EditText;
import android.widget.TextView;

import com.example.fptproject.R;
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.databases.PrefManager;
import com.example.fptproject.databases.repositories.DoctorRepository;
import com.example.fptproject.databases.repositories.PatientRepository;
import com.example.fptproject.models.Doctor;
import com.example.fptproject.models.Patient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText edt_name;
    private EditText edt_pass;
    private EditText edt_email;
    private EditText edt_phone;
    private TextView tv_save,tv_tk;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public UpdateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateFragment newInstance(String param1, String param2) {
        UpdateFragment fragment = new UpdateFragment();
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
        return inflater.inflate(R.layout.fragment_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String username = PrefManager.getString(getContext(),"username");
        DBHelper dbHelper = new DBHelper(getContext());
        PatientRepository patientRepository= new PatientRepository(dbHelper);
        DoctorRepository doctorRepository = new DoctorRepository(dbHelper);
        edt_name = view.findViewById(R.id.edt_name);
        edt_pass = view.findViewById(R.id.edt_pass);
        edt_email = view.findViewById(R.id.edt_email);
        edt_phone = view.findViewById(R.id.edt_phone);

        tv_tk=view.findViewById(R.id.tv_tk);
        tv_save=view.findViewById(R.id.tv_save);
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = edt_pass.getText().toString();
                String newName = edt_name.getText().toString();
                String newEmail = edt_email.getText().toString();
                String newPhone = edt_phone.getText().toString();
                if(doctorRepository.getDoctorByDoctorUsername(username)!=null){
                    Doctor doctor=doctorRepository.getDoctorByDoctorUsername(username);
                    tv_tk.setText(doctor.getUsername());
                }else{
                    Patient patient = patientRepository.getPatientByPatientUsername(username);
                    tv_tk.setText(patient.getUsername());
                    patientRepository.UpdatePatientByUsername(username, newPassword, newName, newEmail, newPhone);
                }
                NavController navController= NavHostFragment.findNavController(UpdateFragment.this);
                navController.navigate(R.id.action_updateFragment_to_hoSoFragment);
            }
        });

    }



}