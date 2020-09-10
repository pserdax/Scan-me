package com.example.scanme;

import android.app.FragmentManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scanme.databinding.ActivityMainBinding;
import com.example.scanme.databinding.ScanExactBinding;

public class Scan extends Fragment {

    private ScanExactBinding scanExactBinding;
    private ActivityMainBinding mainBinding;
    NavController navController;

    public Scan() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        scanExactBinding = ScanExactBinding.inflate(getLayoutInflater());
        View view = scanExactBinding.getRoot();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        scanExactBinding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        scanExactBinding.btnScan.setOnClickListener(i-> navController.navigate(R.id.action_menuNavigation_to_qrCodeScan));
    }
}