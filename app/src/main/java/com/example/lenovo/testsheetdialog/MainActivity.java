package com.example.lenovo.testsheetdialog;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private  TextView tv;
    private BottomSheetDialog dialog;///底部弹窗
    private Handler mHandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new BottomSheetDialog(MainActivity.this, R.style.BottomSheetStyle);
                View commentView= LayoutInflater.from(MainActivity.this).inflate(R.layout.comment_dialog_layout,null);//关键
                final EditText commentText=commentView.findViewById(R.id.dialog_comment_et);
                final TextView tv=commentView.findViewById(R.id.tv);

                dialog.setContentView(commentView);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showKeyboard(commentText);
                    }
                },300);

                /**
                 * 解决bsd显示不全的情况
                 */
                View parent = (View) commentView.getParent();
                BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
                commentView.measure(0,0);
                behavior.setPeekHeight(commentView.getMeasuredHeight());

                dialog.show();
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    //弹出软键盘
    public void showKeyboard(EditText editText) {
        //其中editText为dialog中的输入框的 EditText
        if(editText!=null){
            //设置可获得焦点
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            //请求获得焦点
            editText.requestFocus();
            //调用系统输入法
            InputMethodManager inputManager = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(editText, 0);
        }
    }

}
