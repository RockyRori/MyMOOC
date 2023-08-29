package neu.edu.mymooc;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;

public class LoginFragment extends Fragment {
    private ConstraintLayout layoutContext;//正常内容部分，是一个ConstraintLayout
    private LinearLayout layoutHistory;//历史菜单部分，是一个LinearLayout
    private EditText editTextMOOCNum;//MOOC账号输入框
    private SharedPreferences preferences;
    private static final String USER_PREFERENCE = "preference";
    private static final String USER_NAME = "userName";
    private static final String USER_CREDIT = "userCredit";

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        final Activity activity = getActivity();
        layoutContext = v.findViewById(R.id.layoutContext);
        layoutHistory = v.findViewById(R.id.layoutHistory);
        editTextMOOCNum = v.findViewById(R.id.editTextMOOCNum);

        //创建两条历史记录菜单项，添加到layoutHistory中
        for (int i = 0; i < 4; i++) {
            View layoutItem = getActivity().getLayoutInflater().inflate(R.layout.login_history_item, null);
            //响应菜单项的点击，把它里面的信息填到输入框中。
            layoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editTextMOOCNum.setText("1365523465");
                    layoutContext.setVisibility(View.VISIBLE);
                    layoutHistory.setVisibility(View.INVISIBLE);
                }
            });
            layoutHistory.addView(layoutItem);
        }

        //响应下拉箭头的点击事件，弹出登录历史记录菜单
        v.findViewById(R.id.textViewHistory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutContext.setVisibility(View.INVISIBLE);
                layoutHistory.setVisibility(View.VISIBLE);
                //使用动画显示历史记录
                AnimationSet set = (AnimationSet) AnimationUtils.loadAnimation(
                        getContext(), R.anim.login_history_anim);
                layoutHistory.startAnimation(set);
            }
        });

        //当点击菜单项之外的区域时，把历史菜单隐藏
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (layoutHistory.getVisibility() == View.VISIBLE) {
                    layoutContext.setVisibility(View.VISIBLE);
                    layoutHistory.setVisibility(View.INVISIBLE);
                }
            }
        });

        //响应登录按钮的点击事件
        v.findViewById(R.id.buttonLogin).setOnClickListener(v1 -> {
            //设置用户名
            preferences = activity.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(USER_NAME, editTextMOOCNum.getText().toString());
            //设置用户积分
            editor.putInt(USER_CREDIT, 280);
            editor.apply();
            //无错误时执行,登录成功，进入主页面
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            CourseFragment fragment = new CourseFragment();
            //替换掉FrameLayout中现有的Fragment
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            //将这次切换放入后退栈中，这样可以在点后退键时自动返回上一个页面
            fragmentTransaction.addToBackStack("login");
            fragmentTransaction.commit();
        });

        v.findViewById(R.id.textViewRegister).setOnClickListener(v1 -> {
            //启动注册Activity
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RegisterFragment fragment = new RegisterFragment();
            //替换掉FrameLayout中现有的Fragment
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            //将这次切换放入后退栈中，这样可以在点后退键时自动返回上一个页面
            fragmentTransaction.addToBackStack("login");
            fragmentTransaction.commit();
        });
        return v;
    }
}