package com.boldijarpaul.itfest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.api.ApiModule;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Browsing on 11/7/2015.
 */
public class ImageFragment extends Fragment {


    @Bind(R.id.item_image_imageview)
    ImageView mImage;
    @Bind(R.id.item_image_play)
    View mPlay;

    public static String KEY_URL = "KEYURL";

    public static ImageFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString(KEY_URL, url);

        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_image, container, false);

        ButterKnife.bind(this, view);

        String url = getArguments().getString(KEY_URL);
        url = url.replace("localhost", ApiModule.IP);
        Picasso.with(view.getContext())
                .load(url)
                .into(mImage);
        return view;
    }
}
