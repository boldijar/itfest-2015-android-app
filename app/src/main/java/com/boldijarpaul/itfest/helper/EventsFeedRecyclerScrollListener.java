package com.boldijarpaul.itfest.helper;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import timber.log.Timber;

/**
 * Created by Browsing on 11/7/2015.
 */
public class EventsFeedRecyclerScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager mLinearLayoutManager;
    private ScrollListener mScrollListener;

    public EventsFeedRecyclerScrollListener(LinearLayoutManager mLinearLayoutManager, ScrollListener scrollListener) {
        this.mLinearLayoutManager = mLinearLayoutManager;
        mScrollListener = scrollListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (mLinearLayoutManager.findLastVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1) {
           /* reached the end of the list */
            Timber.i("Scroll reached end! LastVisibleItemPosition: %s", mLinearLayoutManager.findLastVisibleItemPosition());
            mScrollListener.onReachedEndOfScroll();
        }
    }

    public static interface ScrollListener {
        void onReachedEndOfScroll();
    }
}
