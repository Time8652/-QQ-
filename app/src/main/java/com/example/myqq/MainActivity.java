package com.example.myqq;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private QQFragment firstFragment;// 用于显示QQ界面
    private ContactFragment secondFragment;//用于显示联系人界面
    private FindFragment thirdFragment;// 用于显示发现界面

    /*声明组件变量*/
    private ImageView qqImg, contactImg, findImg;
    private TextView qqText, contactText, findText;
    private String number;

    private FragmentManager fragmentManager = null;// 用于对Fragment进行管理
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//要求窗口没有title
        super.setContentView(R.layout.activity_main);
        // 初始化布局元素
        initViews();
        fragmentManager = getFragmentManager();//用于对Fragment进行管理
        // 设置默认的显示界面
        setTabSelection(0);
    }

    /**
     * 在这里面获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件
     */
    @SuppressLint("NewApi")
    public void initViews() {
        fragmentManager = getFragmentManager();
        View firstLayout = findViewById(R.id.qq_layout);
        View secondLayout = findViewById(R.id.contact_layout);
        View thirdLayout = findViewById(R.id.find_layout);

        qqImg = findViewById(R.id.qq_img);
        contactImg = findViewById(R.id.contact_img);
        findImg = findViewById(R.id.find_img);

        qqText = findViewById(R.id.qq_text);
        contactText = findViewById(R.id.contact_text);
        findText = findViewById(R.id.find_text);

        //处理点击事件
        firstLayout.setOnClickListener(this);
        secondLayout.setOnClickListener(this);
        thirdLayout.setOnClickListener(this);

        /*获取登录activity传过来的微信号*/
        Intent intent = getIntent();
        number = intent.getStringExtra("qq_number");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.qq_layout) {
            setTabSelection(0);
        } else if (v.getId() == R.id.contact_layout){
            setTabSelection(1);
        } else {
            setTabSelection(2);
        }
    }

    @SuppressLint("NewApi")
    private void setTabSelection(int index) {
        clearSelection();// 每次选中之前先清除掉上次的选中状态
        FragmentTransaction transaction = fragmentManager.beginTransaction();// 开启一个Fragment事务
        hideFragments(transaction);// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        switch (index) {
            case 0:
                qqImg.setImageResource(R.drawable.tab_qq_pressed);//修改布局中的图片
                qqText.setTextColor(Color.parseColor("#0090ff"));//修改字体颜色

                if (firstFragment == null) {
                    // 如果FirstFragment为空，则创建一个并添加到界面上
                    firstFragment = new QQFragment(number);
                    transaction.add(R.id.fragment, firstFragment);

                } else {
                    // 如果FirstFragment不为空，则直接将它显示出来
                    transaction.show(firstFragment);//显示的动作
                }
                break;
            // 以下和firstFragment类同
            case 1:
                contactImg.setImageResource(R.drawable.tab_qq_pressed);//修改布局中的图片
                contactText.setTextColor(Color.parseColor("#0090ff"));//修改字体颜色

                if (secondFragment == null) {
                    secondFragment = new ContactFragment(number);
                    transaction.add(R.id.fragment, secondFragment);
                } else {
                    transaction.show(secondFragment);//显示的动作
                }
                break;
            case 2:
                findImg.setImageResource(R.drawable.tab_find_frd_pressed);//修改布局中的图片
                findText.setTextColor(Color.parseColor("#0090ff"));//修改字体颜色

                if (thirdFragment == null) {
                    thirdFragment = new FindFragment(number);
                    transaction.add(R.id.fragment, thirdFragment);
                } else {
                    transaction.show(thirdFragment);//显示的动作
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态
     */
    private void clearSelection() {
        qqImg.setImageResource(R.drawable.tab_qq_normal);
        qqText.setTextColor(Color.parseColor("#82858b"));

        contactImg.setImageResource(R.drawable.tab_qq_normal);
        contactText .setTextColor(Color.parseColor("#82858b"));

        findImg.setImageResource(R.drawable.tab_find_frd_normal);
        findText.setTextColor(Color.parseColor("#82858b"));
    }

    /**
     * 将所有的Fragment都设置为隐藏状态 用于对Fragment执行操作的事务
     */
    @SuppressLint("NewApi")
    private void hideFragments(FragmentTransaction transaction) {
        if (firstFragment != null) {
            transaction.hide(firstFragment);
        }
        if (secondFragment != null) {
            transaction.hide(secondFragment);
        }
        if (thirdFragment != null) {
            transaction.hide(thirdFragment);
        }
    }

    //封装一个AlertDialog
    private void exitDialog() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("温馨提示")
                .setMessage("您确定要退出程序吗?")
                .setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                }).create();
        dialog.show();//显示对话框
    }

    /**
     * 返回菜单键监听事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//如果是返回按钮
            exitDialog();
        }
        return super.onKeyDown(keyCode, event);
    }
}
