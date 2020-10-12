package com.example.scanme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class Favourites extends Fragment {

    private FavoritesAdapter favoritesAdapter;
    private List<MainData> dataList;
    private RoomDB database;
    private RecyclerView recyclerView;

    public Favourites() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

          View view  = inflater.inflate(R.layout.favourites_scan, container, false);
        // Inflate the layout for this fragment
        recyclerView = view.findViewById(R.id.recyclerView);

        //Initialize database
        database = RoomDB.getInstance(getContext());

        //Store database value in data list
        dataList = database.mainDao().getAll();

        //Initialize linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        //Set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);

        //initialize adapter
        favoritesAdapter = new FavoritesAdapter(getActivity(), dataList);

        //Set adapter
        recyclerView.setAdapter(favoritesAdapter);

        return view;
    }
}