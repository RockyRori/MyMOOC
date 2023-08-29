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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import neu.edu.mymooc.Adapter.QuestionAdapter;

public class QuestionFragment extends Fragment {
    public QuestionFragment() {
        // Required empty public constructor
    }
    private TextView tvUser;
    private SharedPreferences preferences;
    private static final String USER_PREFERENCE = "preference";
    private static final String USER_NAME = "userName";
    private static final String USER_CREDIT = "userCredit";
    private ListView listViewQuestion;
    private int userCredit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question, container, false);
        final Activity activity = getActivity();
        listViewQuestion = v.findViewById(R.id.listViewQuestion);

        //设置用户名
        tvUser = v.findViewById(R.id.tvUser);
        preferences = activity.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        String username = preferences.getString(USER_NAME, "<>");
        tvUser.setText(username);

        //初始化积分
        userCredit = preferences.getInt(USER_CREDIT, 0);

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

        //获取问题列表
        Question[] singleChoiceQuestion = Question.getSingleChoiceQuestionList();
        Question[] multipleChoiceQuestion = Question.getMultipleChoiceQuestionList();
        Question[] judgementChoiceQuestion = Question.getJudgementQuestionList();
        Question[] writtenAnswerQuestion = Question.getWrittenAnswerQuestionList();

        //设置单选问题列表
        v.findViewById(R.id.test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //构造 adapter
                QuestionAdapter questionAdapter = new QuestionAdapter(activity,R.layout.item_question_only,singleChoiceQuestion);
                listViewQuestion.setAdapter(questionAdapter);
                listViewQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    int WHICH = -1;
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setTitle(singleChoiceQuestion[position].getQuestion());
                        builder.setSingleChoiceItems(singleChoiceQuestion[position].getChoice(),-1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                WHICH = which;
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        });
                        builder.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(WHICH == singleChoiceQuestion[position].getAnswer().charAt(0)-65){
                                            SharedPreferences.Editor editor = preferences.edit();
                                            editor.putInt(USER_CREDIT, userCredit+1);
                                            editor.apply();
                                            Toast.makeText(activity,"回答正确，获得1积分",Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }else{
                                            Toast.makeText(activity,"回答错误，正确答案是："+singleChoiceQuestion[position].getAnswer(),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        builder.show();
                    }
                });
            }
        });

        //设置多选问题列表
        v.findViewById(R.id.test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //构造 adapter
                QuestionAdapter questionAdapter = new QuestionAdapter(activity,R.layout.item_question_only,multipleChoiceQuestion);
                listViewQuestion.setAdapter(questionAdapter);
                listViewQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setTitle(multipleChoiceQuestion[position].getQuestion());
                        builder.setMultiChoiceItems(multipleChoiceQuestion[position].getChoice(), null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        });
                        builder.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(false){
                                            SharedPreferences.Editor editor = preferences.edit();
                                            editor.putInt(USER_CREDIT, userCredit+1);
                                            editor.apply();
                                            Toast.makeText(activity,"回答正确，获得1积分",Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }else{
                                            Toast.makeText(activity,"回答错误，正确答案是："+multipleChoiceQuestion[position].getAnswer().substring(5),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        builder.show();
                    }
                });
            }
        });

        //设置判断问题列表
        v.findViewById(R.id.test3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //构造 adapter
                QuestionAdapter questionAdapter = new QuestionAdapter(activity,R.layout.item_question_only,judgementChoiceQuestion);
                listViewQuestion.setAdapter(questionAdapter);
                listViewQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    int WHICH = -1;
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setTitle(judgementChoiceQuestion[position].getQuestion());
                        builder.setSingleChoiceItems(judgementChoiceQuestion[position].getChoice(),-1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                WHICH = which;
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        });
                        builder.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(WHICH == judgementChoiceQuestion[position].getAnswer().charAt(0)-65){
                                            SharedPreferences.Editor editor = preferences.edit();
                                            editor.putInt(USER_CREDIT, userCredit+1);
                                            editor.apply();
                                            Toast.makeText(activity,"回答正确，获得1积分",Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }else{
                                            Toast.makeText(activity,"回答错误，正确答案是："+judgementChoiceQuestion[position].getAnswer(),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        builder.show();
                    }
                });
            }
        });

        //设置填空问题列表
        v.findViewById(R.id.test4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //构造 adapter
                QuestionAdapter questionAdapter = new QuestionAdapter(activity,R.layout.item_question_only,writtenAnswerQuestion);
                listViewQuestion.setAdapter(questionAdapter);
                listViewQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        EditText editText = new EditText(builder.getContext());
                        builder.setTitle(writtenAnswerQuestion[position].getQuestion());
                        builder.setView(editText);
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        });
                        builder.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(editText.getText().toString().equals(writtenAnswerQuestion[position].getAnswer())){
                                            SharedPreferences.Editor editor = preferences.edit();
                                            editor.putInt(USER_CREDIT, userCredit+1);
                                            editor.apply();
                                            Toast.makeText(activity,"回答正确，获得1积分",Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }else{
                                            Toast.makeText(activity,"回答错误，正确答案是："+writtenAnswerQuestion[position].getAnswer(),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        builder.show();
                    }
                });
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