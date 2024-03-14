package com.example.fptproject.uis;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fptproject.R;
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.databases.PrefManager;
import com.example.fptproject.databases.repositories.AdminRepository;
import com.example.fptproject.databases.repositories.DoctorRepository;
import com.example.fptproject.databases.repositories.PatientRepository;
import com.example.fptproject.models.Admin;
import com.example.fptproject.models.Doctor;
import com.example.fptproject.models.Patient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment implements OnMapReadyCallback {
    TextView tv_name;
    DBHelper dbHelper;
    PatientRepository patientRepository;
    AdminRepository adminRepository;
    Button button;
    IClickLogOut iClickLogOut;
    Button button_csbm;
    Button button_qdsd;
    Button button_dkdv;
    Button button_call;

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private GoogleMap gMap;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền được cấp, khởi tạo và hiển thị bản đồ
                SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                        .findFragmentById(R.id.id_map);
                mapFragment.getMapAsync(this);
            } else {
                // Người dùng không cấp quyền, xử lý tùy ý
                Toast.makeText(requireContext(), "Bạn cần cấp quyền để truy cập vị trí", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        // Kiểm tra và yêu cầu quyền truy cập vị trí nếu cần
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Nếu quyền chưa được cấp, yêu cầu quyền từ người dùng
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            // Quyền đã được cấp, khởi tạo và hiển thị bản đồ
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.id_map);
            mapFragment.getMapAsync(this);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_name= view.findViewById(R.id.nameTextView);
        button_csbm = view.findViewById(R.id.button_csbm);
        button_qdsd = view.findViewById(R.id.button_qdsd);
        button_dkdv = view.findViewById(R.id.button_dkdv);
        dbHelper = new DBHelper(getContext());
        patientRepository = new PatientRepository(dbHelper);
        adminRepository = new AdminRepository(dbHelper);
        DoctorRepository doctorRepository=new DoctorRepository(dbHelper);
        String username = PrefManager.getString(getContext(),"username");
        if(doctorRepository.getDoctorByDoctorUsername(username)!=null){
            Doctor doctor=doctorRepository.getDoctorByDoctorUsername(username);
            tv_name.setText(doctor.getName());
        } else if (adminRepository.getAdminByUsername(username)!=null) {
            Admin admin=adminRepository.getAdminByUsername(username);
            tv_name.setText(admin.getName());
        } else{
            Patient patient = patientRepository.getPatientByPatientUsername(username);
            tv_name.setText(patient.getName());
        }
        button = view.findViewById(R.id.logoutButton);
        button_qdsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= NavHostFragment.findNavController(UserFragment.this);
                navController.navigate(R.id.action_userFragment_to_QDSDActivity);
            }
        });
        button_csbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= NavHostFragment.findNavController(UserFragment.this);
                navController.navigate(R.id.action_userFragment_to_CSBMActivity);
            }
        });
        button_dkdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= NavHostFragment.findNavController(UserFragment.this);
                navController.navigate(R.id.action_userFragment_to_DKDVActivity);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                PrefManager.removeKey(getContext(),"username");
                if(iClickLogOut!=null){
                    iClickLogOut.onClick();
                }
            }
        });
        button_call = view.findViewById(R.id.call);
        button_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "0386713388";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: " + phoneNumber));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;
        LatLng latLng = new LatLng(21.0124, 105.5253);
        gMap.addMarker(new MarkerOptions().position(latLng).title("Đại học FPT"));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));
    }

    public interface IClickLogOut{
        void onClick();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IClickLogOut) {
            iClickLogOut = (IClickLogOut) context;
        }else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }
}