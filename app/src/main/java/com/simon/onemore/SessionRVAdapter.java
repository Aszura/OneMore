package com.simon.onemore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SessionRVAdapter extends RecyclerView.Adapter<SessionRVAdapter.SessionViewHolder> {
    Context context;

    public static class SessionViewHolder extends RecyclerView.ViewHolder {
        TextView sessionName;
        SessionData session;

        SessionViewHolder(View itemView) {
            super(itemView);
            sessionName = (TextView)itemView.findViewById(R.id.textSessionName);
        }
    }

    SessionRVAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SessionRVAdapter.SessionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_session, parent, false);
        SessionRVAdapter.SessionViewHolder svh = new SessionRVAdapter.SessionViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull SessionRVAdapter.SessionViewHolder holder, int position) {
        SessionData session = DataLayer.getInstance().getSessions().get(position);
        holder.sessionName.setText(session.name);
        holder.session = session;

        holder.itemView.findViewById(R.id.buttonToSession).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLayer.getInstance().setActiveSession(holder.session);
                context.startActivity(new Intent(context, SessionActivity.class));
            }
        });

        holder.itemView.findViewById(R.id.buttonRemoveSession).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLayer.getInstance().getSessions().removeIf(x -> x.name == holder.session.name);
                SessionRVAdapter.this.notifyDataSetChanged();
                DataLayer.getInstance().saveData(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return DataLayer.getInstance().getSessions().size();
    }
}
