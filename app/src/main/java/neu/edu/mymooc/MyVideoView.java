package neu.edu.mymooc;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;
public class MyVideoView extends VideoView{
    //Android Studio 自带的VideoView不能改变大小，屏占比不合理，
    //故设计可以更改大小的MyVideoView，其余功能继承
    public MyVideoView(Context context) { super(context); }
    public MyVideoView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }
    public MyVideoView(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(getWidth(), widthMeasureSpec);
        int height = getDefaultSize(getHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}