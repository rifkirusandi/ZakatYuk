package com.si410702.java.zakatyuk.adapter;

import com.si410702.java.zakatyuk.ZakatFitrahFragment;
import com.si410702.java.zakatyuk.ZakatPenghasilanFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class AlokasiZakatAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public AlokasiZakatAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new ZakatFitrahFragment();
            case 1: return new ZakatPenghasilanFragment();
            default: return null;
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
