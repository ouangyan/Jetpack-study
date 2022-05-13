package com.yan.navigation3;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class HomeFragment extends Fragment {


    private int notificationId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button = getView().findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                NavController navController = Navigation.findNavController(v);
//                Bundle args = new HomeFragmentArgs
//                        .Builder()
//                        .setUserName("Jordan")
//                        .setAge(59)
//                        .build()
//                        .toBundle();
//                navController.navigate(R.id.action_homeFragment_to_detailFragment,args);
                sendNotification();
            }
        });
    }

    /**
     * 显示DeepLink
     * 通过pendingIntent导航到特定目的地
     */
    private void sendNotification(){
        // 通知渠道
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(getActivity().getPackageName(),"My Channel",NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("My NotificationChannel");
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification notification = new NotificationCompat
                .Builder(getActivity(),getActivity().getPackageName())
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Deep Link")
                .setContentText("点击我试试...")
                .setContentIntent(getPendingIntent())
                .build();

        NotificationManagerCompat.from(getActivity()).notify(notificationId++,notification);
    }

    private PendingIntent getPendingIntent() {
        return Navigation.findNavController(getActivity(),R.id.fragment)
                .createDeepLink()
                .setGraph(R.navigation.my_nav_graph)
                .setDestination(R.id.detailFragment)
                .createPendingIntent();
    }

}