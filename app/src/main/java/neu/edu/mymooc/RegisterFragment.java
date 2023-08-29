package neu.edu.mymooc;

import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;


public class RegisterFragment extends Fragment {
    public static final int TAKE_PHOTO = 1; //拍照
    public static final int SELECT_PHOTO = 2;//从图库选择
    public static final int CROP_PHOTO = 3;//剪切编辑
    public static final int ASK_PERMISSIONS = 4;//请求权限
    private Uri imageUri;//所选图像的URI
    private ImageView imageViewAvatar;
    //底部选择图片
    private BottomSheetDialog sheetDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        //响应头像点击，弹出菜单，让用户选择允何种方式获得头像
        this.imageViewAvatar = v.findViewById(R.id.imageViewAvatar);
        imageViewAvatar.setOnClickListener(v1 -> {
            sheetDialog = new BottomSheetDialog(getActivity());
            View view = getLayoutInflater().inflate(R.layout.image_pick_sheet_menu, null);
            sheetDialog.setContentView(view);
            sheetDialog.show();
        });

        //点击了提交按钮，注册之
        v.findViewById(R.id.buttonCommit).setOnClickListener(v1 -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            LoginFragment fragment = new LoginFragment();
            //替换掉FrameLayout中现有的Fragment
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            //将这次切换放入后退栈中，这样可以在点后退键时自动返回上一个页面
            fragmentTransaction.addToBackStack("register");
            fragmentTransaction.commit();
            Toast.makeText(getActivity()," 注册成功",Toast.LENGTH_SHORT).show();
        });
        return v;
    }
}