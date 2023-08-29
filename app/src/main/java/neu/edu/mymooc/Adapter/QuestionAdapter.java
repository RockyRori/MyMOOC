package neu.edu.mymooc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import neu.edu.mymooc.Question;
import neu.edu.mymooc.R;

public class QuestionAdapter extends ArrayAdapter<Question> {
    private LayoutInflater myLayoutInflater;
    public QuestionAdapter(@NonNull Context context, int resource, @NonNull Question[] objects) {
        super(context, resource, objects);
        myLayoutInflater = LayoutInflater.from(context);
    }

    private static class ViewHolder{
        TextView question;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = myLayoutInflater.inflate(R.layout.item_question_only,null);
        }
        //判断缓存对象
        ViewHolder holder = null;
        if(holder == null){
            holder = new ViewHolder();
            holder.question = convertView.findViewById(R.id.questionOnly);
            convertView.setTag(holder);
        }else {
            holder =(ViewHolder) convertView.getTag();
        }

        //获取需要显示的数据
        Question question = getItem(position);
        holder.question.setText(question.getQuestion());
        return convertView;
    }
}
