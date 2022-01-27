package com.example.guru2_team21;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DiaryListRvAdapter extends RecyclerView.Adapter<DiaryListRvAdapter.ViewHolders> {
    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {

    }

    @Override
    public int getItemCount() { return 0; }

    public class ViewHolders extends RecyclerView.ViewHolder {
        public ViewHolders(@NonNull View itemView) {
            super(itemView);
        }
    }
}
