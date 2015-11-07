package com.boldijarpaul.itfest.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.boldijarpaul.itfest.ui.fragments.ImageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Browsing on 11/7/2015.
 */
public class ImageFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<String> mUrls;
    private List<Fragment> mFragments = new ArrayList<>();

    public ImageFragmentPagerAdapter(FragmentManager fm, List<String> mUrls) {
        super(fm);
        this.mUrls = mUrls;
        for (String string : mUrls) {
            mFragments.add(ImageFragment.newInstance(string));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mUrls.size();
    }
}
