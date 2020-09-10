package com.example.scanme;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scanme.databinding.MenuBinding;

public class MenuNavigation extends Fragment {

    private Fragment fragment = null;
    private MenuBinding binding;

    public MenuNavigation() {
        // Required empty public constructor
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = MenuBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.chipNavigation.setItemSelected(R.id.scan, true);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Scan()).commit();
        binding.chipNavigation.setOnItemSelectedListener(i -> {
            switch (i) {
                case R.id.favourites:
                    fragment = new Favourites();
                    break;
                case R.id.scan:
                    fragment = new Scan();
                    Log.i("HAHHAHHA", "HOHOHOHO");
                    break;
                case R.id.recently_scanned:
                    fragment = new Recently();
                    break;
            }

            if (fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }

        });}
}