package com.example.githubmobile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.githubmobile.Model.Repos.ReposResponse;
import com.example.githubmobile.R;
import java.util.ArrayList;

public class ReposAdapter extends ArrayAdapter<ReposResponse> {
    private Context context;
    ArrayList<ReposResponse> reposResponses;

    public ReposAdapter(Context context, ArrayList<ReposResponse> reposResponses){
        super(context, 0, reposResponses);
        this.context = context;
        this.reposResponses = reposResponses;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View itemView = convertView;
        if (itemView == null){
            itemView = LayoutInflater.from(context). inflate(R.layout.layout_item_repos, parent, false);
        }

        final ReposResponse repos = reposResponses.get(position);

        TextView tvName = itemView.findViewById(R.id.repos_nama);
        TextView tvUrl = itemView.findViewById(R.id.repos_url);
        CardView cardView = itemView.findViewById(R.id.cv_repos);

        tvName.setText(repos.getName());
        tvUrl.setText(repos.getHtmlUrl());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(repos.getHtmlUrl()));
                v.getContext().startActivity(intent);
            }
        });

        return  itemView;
    }

}
