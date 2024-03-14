package com.example.fptproject.uis;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.fptproject.DoctorChooseInterface;
import com.example.fptproject.R;
import com.example.fptproject.adapters.DoctorManagerAdapter;
import com.example.fptproject.banner.ImagePaperAdapter;
import com.example.fptproject.databases.DBHelper;
import com.example.fptproject.databases.PrefManager;
import com.example.fptproject.databases.repositories.AdminRepository;
import com.example.fptproject.databases.repositories.DoctorRepository;
import com.example.fptproject.databases.repositories.PatientRepository;
import com.example.fptproject.models.Doctor;
import com.example.fptproject.models.HomeMenu;
import com.example.fptproject.adapters.HomeMenuAdapter;
import com.example.fptproject.models.HomeMenuDoctor;
import com.example.fptproject.adapters.HomeMenuDoctorAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements DoctorChooseInterface {

    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;
    private HomeMenuAdapter homeMenuAdapter;
    private HomeMenuDoctorAdapter homeMenuDoctorAdapter;
    private ViewPager mViewPager;
    private ImagePaperAdapter mAdapter;
    private ImageView img;
    NavController navController;
    DBHelper dbHelper;
    DoctorRepository doctorRepository;
    PatientRepository patientRepository;
    AdminRepository adminRepository;
    LinearLayout llLichDoctor, admin;

    private int[] mImageIds = {R.drawable.banner1, R.drawable.banner3, R.drawable.banner4};
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    private List<HomeMenu> getList() {
        List<HomeMenu> list = new ArrayList<>();
        list.add(new HomeMenu(R.drawable.ic_action_schedule, "Đặt lịch"));
        list.add(new HomeMenu(R.drawable.ic_action_about, "Giới thiệu"));
        list.add(new HomeMenu(R.drawable.ic_action_guide, "Hướng dẫn"));
        return list;
    }

//    private List<HomeMenuDoctor> getDoctorList() {
//        List<HomeMenuDoctor> list = new ArrayList<>();
//
//        // Khởi tạo lớp trợ giúp và đọc dữ liệu từ bảng "Doctor"
//        DBHelper dbHelper = new DBHelper(getContext());
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String query = "SELECT doctor_name FROM Doctor";
//        Cursor cursor = db.rawQuery(query, null);
//
//        // Xử lý dữ liệu và thêm vào danh sách
//        if (cursor.moveToFirst()) {
//            do {
//                @SuppressLint("Range")
//                String doctorName = cursor.getString(cursor.getColumnIndex("doctor_name"));
//                list.add(new HomeMenuDoctor(R.drawable.doctor, doctorName));
//            } while (cursor.moveToNext());
//        }
//
//        // Đóng kết nối cơ sở dữ liệu và giải phóng tài nguyên
//        cursor.close();
//        dbHelper.close();
//
//        return list;
//    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.Home_menu_option);
        llLichDoctor=view.findViewById(R.id.llLichDoctor);
        admin=view.findViewById(R.id.admin);
        dbHelper = new DBHelper(getContext());
        adminRepository = new AdminRepository(dbHelper);
        doctorRepository = new DoctorRepository(dbHelper);
        patientRepository = new PatientRepository(dbHelper);
        //check xem tk là patient haY doctor
        if(doctorRepository.getDoctorByDoctorUsername(PrefManager.getString(getContext(),"username"))!=null){
            recyclerView.setVisibility(View.GONE);
//            llLichDoctor.setVisibility(View.VISIBLE);
        } else if (adminRepository.getAdminByUsername(PrefManager.getString(getContext(),"username"))!=null) {
            recyclerView.setVisibility(View.GONE);
            admin.setVisibility(View.VISIBLE);
        }
        navController = NavHostFragment.findNavController(HomeFragment.this);
        homeMenuAdapter = new HomeMenuAdapter(getList());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(homeMenuAdapter);
        recyclerView1 = view.findViewById(R.id.Home_doctor_list);
        homeMenuDoctorAdapter = new HomeMenuDoctorAdapter(doctorRepository.getAll(), HomeFragment.this, getContext());
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 2);
        recyclerView1.setLayoutManager(gridLayoutManager1);
        recyclerView1.setAdapter(homeMenuDoctorAdapter);
        mViewPager = view.findViewById(R.id.view_pager_home);
        mAdapter = new ImagePaperAdapter(requireContext(), mImageIds);
        mViewPager.setAdapter(mAdapter);
        img = view.findViewById(R.id.doc_manager);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeFragment_to_doctorManager);
            }
        });

        return view;
    }
    @Override
    public void onClickDoctor(Doctor doctor) {
        Bundle bundle = new Bundle();
        bundle.putInt("doctor_id", doctor.getId());
        navController.navigate(R.id.action_homeFragment_to_doctorInfoActivity, bundle);
    }


}