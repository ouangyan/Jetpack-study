package com.yan.navigation2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 隐藏appBar
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // 隐藏appBar
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }
}