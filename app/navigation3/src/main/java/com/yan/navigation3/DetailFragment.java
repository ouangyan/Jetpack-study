package com.yan.navigation3;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class DetailFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail, container, false);

//        HomeFragmentArgs homeFragmentArgs = HomeFragmentArgs.fromBundle(getArguments());
//        String userName = homeFragmentArgs.getUserName();
//        int age = homeFragmentArgs.getAge();
//        Log.i("1qaz2wsx",userName+","+age);

        Bundle args = getArguments();
        if(args != null){
            String params = args.getString("params");
            if(!TextUtils.isEmpty(params)){
                Log.i("1qaz2wsx",params);
            }
        }
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button = getView().findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_detailFragment_to_homeFragment);
            }
        });
    }
}