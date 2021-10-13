package com.simon.onemore;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemRVAdapter extends RecyclerView.Adapter<ItemRVAdapter.ItemViewHolder> {
    Context context;

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemCount;

        ItemViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView)itemView.findViewById(R.id.textItemName);
            itemCount = (TextView)itemView.findViewById(R.id.textItemCount);
        }
    }

    ItemRVAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false);
        ItemViewHolder ivh = new ItemViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemData item = DataLayer.getInstance().getActiveSession().items.get(position);
        holder.itemName.setText(item.name);
        holder.itemCount.setText(Integer.toString(item.count));

        holder.itemView.findViewById(R.id.buttonItemAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++item.count;
                ItemRVAdapter.this.notifyDataSetChanged();
                DataLayer.getInstance().saveData(context);
            }
        });

        holder.itemView.findViewById(R.id.buttonItemRemove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --item.count;

                if(item.count <= 0) {
                    DataLayer.getInstance().getActiveSession().items.removeIf(i -> i.count <= 0);
                }

                ItemRVAdapter.this.notifyDataSetChanged();
                DataLayer.getInstance().saveData(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return DataLayer.getInstance().getActiveSession().items.size();
    }
}
