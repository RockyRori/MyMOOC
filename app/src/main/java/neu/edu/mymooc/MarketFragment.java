package neu.edu.mymooc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MarketFragment extends Fragment {
    public MarketFragment() {
        // Required empty public constructor
    }

    private TextView tvUser;
    private SharedPreferences preferences;
    private static final String USER_PREFERENCE = "preference";
    private static final String USER_NAME = "userName";
    private static final String USER_CREDIT = "userCredit";
    private LinearLayout itemLayout;
    private LinearLayout currentLayout;
    private TextView currentCredit;
    private int credit = 0;
    private int userCredit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_market, container, false);
        final Activity activity = getActivity();

        //设置用户名
        tvUser = v.findViewById(R.id.tvUser);
        preferences = activity.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        String username = preferences.getString(USER_NAME, "<>");
        tvUser.setText(username);

        //初始化积分
        userCredit = preferences.getInt(USER_CREDIT, 0);
        currentCredit = v.findViewById(R.id.tvCurrentCredit);
        currentCredit.setText(""+userCredit);

        //选中商品
        itemLayout = v.findViewById(R.id.linearLayoutItems);
        v.findViewById(R.id.item1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0;i < itemLayout.getChildCount();i++){
                    itemLayout.getChildAt(i).setBackgroundColor(0xFFFFFFFF);
                }
                currentLayout = v.findViewById(R.id.item1);
                currentLayout.setBackgroundColor(0xFFB3E0ED);
                currentCredit = (TextView) currentLayout.getChildAt(2);
                credit = Integer.parseInt(currentCredit.getText().toString());
            }
        });

        v.findViewById(R.id.item2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0;i < itemLayout.getChildCount();i++){
                    itemLayout.getChildAt(i).setBackgroundColor(0xFFFFFFFF);
                }
                currentLayout = v.findViewById(R.id.item2);
                currentLayout.setBackgroundColor(0xFFB3E0ED);
                currentCredit = (TextView) currentLayout.getChildAt(2);
                credit = Integer.parseInt(currentCredit.getText().toString());
            }
        });

        v.findViewById(R.id.item3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0;i < itemLayout.getChildCount();i++){
                    itemLayout.getChildAt(i).setBackgroundColor(0xFFFFFFFF);
                }
                currentLayout = v.findViewById(R.id.item3);
                currentLayout.setBackgroundColor(0xFFB3E0ED);
                currentCredit = (TextView) currentLayout.getChildAt(2);
                credit = Integer.parseInt(currentCredit.getText().toString());
            }
        });

        v.findViewById(R.id.item4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0;i < itemLayout.getChildCount();i++){
                    itemLayout.getChildAt(i).setBackgroundColor(0xFFFFFFFF);
                }
                currentLayout = v.findViewById(R.id.item4);
                currentLayout.setBackgroundColor(0xFFB3E0ED);
                currentCredit = (TextView) currentLayout.getChildAt(2);
                credit = Integer.parseInt(currentCredit.getText().toString());
            }
        });

        v.findViewById(R.id.item5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0;i < itemLayout.getChildCount();i++){
                    itemLayout.getChildAt(i).setBackgroundColor(0xFFFFFFFF);
                }
                currentLayout = v.findViewById(R.id.item5);
                currentLayout.setBackgroundColor(0xFFB3E0ED);
                currentCredit = (TextView) currentLayout.getChildAt(2);
                credit = Integer.parseInt(currentCredit.getText().toString());
            }
        });

        v.findViewById(R.id.item6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0;i < itemLayout.getChildCount();i++){
                    itemLayout.getChildAt(i).setBackgroundColor(0xFFFFFFFF);
                }
                currentLayout = v.findViewById(R.id.item6);
                currentLayout.setBackgroundColor(0xFFB3E0ED);
                currentCredit = (TextView) currentLayout.getChildAt(2);
                credit = Integer.parseInt(currentCredit.getText().toString());
            }
        });

        //确认兑换
        v.findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("兑换以上物品需要<"+credit+">积分，账户余额为<"+userCredit+">积分,确定兑换吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (credit <= userCredit){
                            userCredit = userCredit-credit;
                            currentCredit = v.findViewById(R.id.tvCurrentCredit);
                            currentCredit.setText(""+userCredit);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt(USER_CREDIT, userCredit);
                            editor.apply();
                            Toast.makeText(activity,"兑换成功！剩余积分<"+userCredit+">",Toast.LENGTH_SHORT).show(); }
                        else{
                            Toast.makeText(activity,"积分不足，无法兑换",Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
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
                fragmentTransaction.addToBackStack("market");
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
                fragmentTransaction.addToBackStack("market");
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
                fragmentTransaction.addToBackStack("market");
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
                fragmentTransaction.addToBackStack("market");
                fragmentTransaction.commit();
            }
        });
        return v;
    }
}