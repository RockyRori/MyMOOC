package neu.edu.mymooc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static android.content.ContentValues.TAG;

public class VideoFragment extends Fragment {
    public VideoFragment() {
        // Required empty public constructor
    }
    private TextView tvUser;
    private SharedPreferences preferences;
    private static final String USER_PREFERENCE = "preference";
    private static final String USER_NAME = "userName";
    private ArrayList<String> videoList = new ArrayList<>();
    private VideoView videoView;
    private int videoIndex = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        final Activity activity = getActivity();

        //设置用户名
        tvUser = v.findViewById(R.id.tvUser);
        preferences = activity.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        String username = preferences.getString(USER_NAME, "<>");
        tvUser.setText(username);

        //申请读取本地视频权限
        verifyStoragePermission(getActivity());

        //设置视频地址
        @SuppressLint("SdCardPath")
        File file = new File(Environment.getExternalStorageDirectory()+"/Movies/");
        Log.e(TAG, "getResource: fileName "+file.getName()+";files "+ Arrays.toString(file.listFiles()));
        videoList.add(file.getPath()+"/习近平喜欢的典故.mp4");
        videoList.add(file.getPath()+"/我的梦想将和中国紧密相连.mp4");
        videoList.add(file.getPath()+"/PS演示.mp4");
        videoList.add(file.getPath()+"/山海镜花.mp4");
        Log.e(TAG, "onCreateView: video paths:"+videoList);
        MediaController mediaController = new MediaController(activity);
        videoView = v.findViewById(R.id.videoView);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoPath(videoList.get(videoIndex));

        //选中对应视频
        v.findViewById(R.id.video1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoIndex = 0;
                videoView.setVideoPath(videoList.get(videoIndex));
                videoView.start();
            }
        });

        v.findViewById(R.id.video2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoIndex = 1;
                videoView.setVideoPath(videoList.get(videoIndex));
                videoView.start();
            }
        });

        v.findViewById(R.id.video3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoIndex = 2;
                videoView.setVideoPath(videoList.get(videoIndex));
                videoView.start();
            }
        });

        v.findViewById(R.id.video4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoIndex = 3;
                videoView.setVideoPath(videoList.get(videoIndex));
                videoView.start();
            }
        });

        //播放暂停键
        Button btnReplay = v.findViewById(R.id.btnReplay);
        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (videoView.isPlaying()){
                    videoView.pause();
                    btnReplay.setText("播放");
                }else {
                    videoView.start();
                    btnReplay.setText("暂停");
                }
            }
        });

        //下一条视频
        Button btnNext = v.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (videoIndex == videoList.size()-1){
                    videoIndex = -1;
                }
                videoIndex = videoIndex + 1;
                videoView.setVideoPath(videoList.get(videoIndex));
            }
        });

        v.findViewById(R.id.linearLayoutTop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进入课程页面
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CourseFragment fragment = new CourseFragment();
                //替换掉FrameLayout中现有的Fragment
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                //将这次切换放入后退栈中，这样可以在点后退键时自动返回上一个页面
                fragmentTransaction.addToBackStack("video");
                fragmentTransaction.commit();
            }
        });

        v.findViewById(R.id.btnQuestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进入答题页面
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                QuestionFragment fragment = new QuestionFragment();
                //替换掉FrameLayout中现有的Fragment
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                //将这次切换放入后退栈中，这样可以在点后退键时自动返回上一个页面
                fragmentTransaction.addToBackStack("video");
                fragmentTransaction.commit();
            }
        });

        v.findViewById(R.id.btnVideo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进入播放页面
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                VideoFragment fragment = new VideoFragment();
                //替换掉FrameLayout中现有的Fragment
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                //将这次切换放入后退栈中，这样可以在点后退键时自动返回上一个页面
                fragmentTransaction.addToBackStack("video");
                fragmentTransaction.commit();
            }
        });

        v.findViewById(R.id.btnMarket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进入商城页面
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MarketFragment fragment = new MarketFragment();
                //替换掉FrameLayout中现有的Fragment
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                //将这次切换放入后退栈中，这样可以在点后退键时自动返回上一个页面
                fragmentTransaction.addToBackStack("video");
                fragmentTransaction.commit();
            }
        });

        return v;
    }
    public static void verifyStoragePermission(Activity activity) {
        String[] PERMISSIONS_STORAGE = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE" };
        int REQUEST_EXTERNAL_STORAGE = 1;
        try {
            //检测是否有读取SD卡的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有读的权限，会弹出对话框申请读的权限
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}