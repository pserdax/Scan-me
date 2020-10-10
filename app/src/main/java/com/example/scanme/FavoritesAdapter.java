package com.example.scanme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    private List<MainData> dataList;
    private Context context;
    private RoomDB database;

    public FavoritesAdapter(Context context, List<MainData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Initialize view
        View view  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_items, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        //initialize Main data
        MainData data = dataList.get(position);

        //Initialize database;
        database = RoomDB.getInstance(context);

        //Set text on textView
        holder.textView.setText(data.getText());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class FavoritesViewHolder extends RecyclerView.ViewHolder {
        // initialize variable
        TextView textView;


        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_scanned_result);
        }
    }

}
