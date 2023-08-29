package neu.edu.mymooc;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CourseFragment extends Fragment {
    public CourseFragment() {
        // Required empty public constructor
    }

    private TextView tvUser;
    private SharedPreferences preferences;
    private static final String USER_PREFERENCE = "preference";
    private static final String USER_NAME = "userName";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course, container, false);
        final Activity activity = getActivity();

        //设置用户名
        tvUser = v.findViewById(R.id.tvUser);
        preferences = activity.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        String username = preferences.getString(USER_NAME, "<>");
        tvUser.setText(username);

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
                fragmentTransaction.addToBackStack("course");
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
                fragmentTransaction.addToBackStack("course");
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
                fragmentTransaction.addToBackStack("course");
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
                fragmentTransaction.addToBackStack("course");
                fragmentTransaction.commit();
            }
        });

        return v;
    }
}