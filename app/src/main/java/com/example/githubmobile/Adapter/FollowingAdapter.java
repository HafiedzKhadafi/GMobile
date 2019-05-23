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
import android.widget.ImageView;
import android.widget.TextView;
import com.example.githubmobile.Model.Following.GetFollowingResponse;
import com.example.githubmobile.R;
import com.example.githubmobile.View.DetailFollowing;
import com.example.githubmobile.View.Update;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class FollowingAdapter extends ArrayAdapter<GetFollowingResponse> {
    private Context context;
    ArrayList<GetFollowingResponse> getFollowingResponses;

    public FollowingAdapter(Context context, ArrayList<GetFollowingResponse> getFollowingResponses){
        super(context, 0, getFollowingResponses);
        this.context = context;
        this.getFollowingResponses = getFollowingResponses;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View itemView = convertView;
        if (itemView == null){
            itemView = LayoutInflater.from(context). inflate(R.layout.layout_item_following, parent, false);
        }

        final GetFollowingResponse repos = getFollowingResponses.get(position);

        TextView tvName = itemView.findViewById(R.id.following_nama);
        ImageView imgAvatar = itemView.findViewById(R.id.following_avatar);
        CardView cv = itemView.findViewById(R.id.cv_following);

        tvName.setText(repos.getLogin());
        Picasso.get()
                .load(repos.getAvatarUrl())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imgAvatar);

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String kirim = repos.getLogin().toString();
                Intent i = new Intent(v.getContext(), DetailFollowing.class);
                i.putExtra("usn", kirim);
                v.getContext().startActivity(i);*/
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(repos.getHtmlUrl()));
                v.getContext().startActivity(intent);
            }
        });

        return  itemView;
    }

}