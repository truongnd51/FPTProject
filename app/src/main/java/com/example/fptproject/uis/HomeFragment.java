package com.example.fptproject.uis;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fptproject.R;
import com.example.fptproject.banner.ImagePaperAdapter;
import com.example.fptproject.models.HomeMenu;
import com.example.fptproject.models.HomeMenuAdapter;
import com.example.fptproject.models.HomeMenuDoctor;
import com.example.fptproject.models.HomeMenuDoctorAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;
    private HomeMenuAdapter homeMenuAdapter;
    private HomeMenuDoctorAdapter homeMenuDoctorAdapter;
    private ViewPager mViewPager;
    private ImagePaperAdapter mAdapter;

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

    private List<HomeMenu> getList(){
        List<HomeMenu> list = new ArrayList<>();
        list.add(new HomeMenu(R.drawable.ic_action_schedule, "Booking"));
        list.add(new HomeMenu(R.drawable.ic_action_about, "About us"));
        list.add(new HomeMenu(R.drawable.ic_action_guide, "Guide"));
        return list;
    }
    private List<HomeMenuDoctor> getDoctorList(){
        List<HomeMenuDoctor> list = new ArrayList<>();
        list.add(new HomeMenuDoctor(R.drawable.doctor, "DOCTOR A"));
        list.add(new HomeMenuDoctor(R.drawable.doctor, "DOCTOR B"));
        list.add(new HomeMenuDoctor(R.drawable.doctor, "DOCTOR C"));
        list.add(new HomeMenuDoctor(R.drawable.doctor, "DOCTOR D"));
        list.add(new HomeMenuDoctor(R.drawable.doctor, "DOCTOR E"));
        list.add(new HomeMenuDoctor(R.drawable.doctor, "DOCTOR F"));
        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.Home_menu_option);
        homeMenuAdapter = new HomeMenuAdapter(getList());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(homeMenuAdapter);

        recyclerView1 = view.findViewById(R.id.Home_doctor_list);
        homeMenuDoctorAdapter = new HomeMenuDoctorAdapter(getDoctorList());
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 2);
        recyclerView1.setLayoutManager(gridLayoutManager1);
        recyclerView1.setAdapter(homeMenuDoctorAdapter);

        mViewPager = view.findViewById(R.id.view_pager_home);
        mAdapter = new ImagePaperAdapter(requireContext(), mImageIds);
        mViewPager.setAdapter(mAdapter);

        return view;
    }
}