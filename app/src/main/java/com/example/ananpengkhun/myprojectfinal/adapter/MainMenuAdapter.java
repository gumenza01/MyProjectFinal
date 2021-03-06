package com.example.ananpengkhun.myprojectfinal.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ananpengkhun.myprojectfinal.dao.DataDao;
import com.example.ananpengkhun.myprojectfinal.fragment.AddProductFragment;
import com.example.ananpengkhun.myprojectfinal.fragment.AddProductTypeFragment;
import com.example.ananpengkhun.myprojectfinal.fragment.AddProviderFragment;
import com.example.ananpengkhun.myprojectfinal.fragment.MainFragment;
import com.example.ananpengkhun.myprojectfinal.fragment.StatisticsFragment;

import java.util.List;

/**
 * Created by ananpengkhun on 12/19/16.
 */

public class MainMenuAdapter extends FragmentStatePagerAdapter {
    //private DataDao dataDao;
    //private List<DataDao.ProductTypeBean> productTypeDaos;
    public MainMenuAdapter(FragmentManager fm) {
        super(fm);

        //this.dataDao = dataDao;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(0 == position){
            fragment = MainFragment.newInstant();
        }else if(1 == position){
            fragment = AddProductFragment.newInstant();
        }else if(2 == position){
            fragment = AddProductTypeFragment.newInstant();
        }else if(3 == position){
            fragment = AddProviderFragment.newInstant();
        }else if(4 == position){
            fragment = StatisticsFragment.newInstant();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
