package com.boldijarpaul.itfest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.boldijarpaul.itfest.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Browsing on 11/7/2015.
 */
public class ImageFragment extends Fragment {


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

        ImageView imageView = (ImageView) view.findViewById(R.id.item_image_imageview);
        String url = getArguments().getString(KEY_URL);
        Picasso.with(view.getContext())
                .load(url)
                .into(imageView);
        return view;
    }
}
