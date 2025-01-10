package com.example.myqq;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private Button bt;
    private EditText et1, et2;
    private CheckBox cb1, cb2, cb3;

    private void Login(String number, String password, boolean checkbox) {
        SharedPreferences sharedPreferences = getSharedPreferences("Record",MODE_PRIVATE);
        String admin_number = sharedPreferences.getString("Number","admin");
        String admin_password = sharedPreferences.getString("Password","123456");
        if (number.equals(admin_number) && password.equals(admin_password) && checkbox) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (checkbox) {
            AlertDialog dialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
                    .setTitle("账号或密码错误")                 //设置对话框的标题
                    .setIcon(R.mipmap.ic_launcher)               //设置对话框标题图标
                    .setMessage("请输入正确的账号和密码")                //设置对话框的提示信息
                    //添加"确定"按钮
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();                             //关闭对话框
                        }
                    })
                    //添加“取消”按钮
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();                             //关闭对话框
                            LoginActivity.this.finish();                   //关闭LoginActivity
                        }
                    });
            dialog = builder.create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                    Button negativeButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);

                    // 设置确定按钮文本颜色
                    positiveButton.setTextColor(ContextCompat.getColor(LoginActivity.this, android.R.color.background_dark));

                    // 设置取消按钮文本颜色
                    negativeButton.setTextColor(ContextCompat.getColor(LoginActivity.this, android.R.color.background_dark));
                }
            });
            dialog.show();
        } else {
            AlertDialog dialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
                    .setTitle("请勾选用户协议")                 //设置对话框的标题
                    .setIcon(R.mipmap.ic_launcher)               //设置对话框标题图标
                    //添加"确定"按钮
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();                             //关闭对话框
                        }
                    })
                    //添加“取消”按钮
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();                             //关闭对话框
                            LoginActivity.this.finish();                   //关闭LoginActivity
                        }
                    });
            dialog = builder.create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                    Button negativeButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);

                    // 设置确定按钮文本颜色
                    positiveButton.setTextColor(ContextCompat.getColor(LoginActivity.this, android.R.color.background_dark));

                    // 设置取消按钮文本颜色
                    negativeButton.setTextColor(ContextCompat.getColor(LoginActivity.this, android.R.color.background_dark));
                }
            });
            dialog.show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
        String number = et1.getText().toString();
        String password = et2.getText().toString();
        boolean checkbox3 = cb3.isChecked();

        SharedPreferences sharedPreferences = getSharedPreferences("Record",MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor edit =sharedPreferences.edit();
        if (cb1.isChecked()) {
            Login(number,password,checkbox3);
        }

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = et1.getText().toString();
                String password = et2.getText().toString();
                boolean checkbox3 = cb3.isChecked();
                Login(number,password,checkbox3);
                SharedPreferences sharedPreferences = getSharedPreferences("Record",MODE_PRIVATE);
                SharedPreferences.Editor edit =sharedPreferences.edit();
                if (cb1.isChecked()) {
                    edit.putString("Number",number);
                    edit.putString("Password",password);
                    edit.putBoolean("Checkbox1",cb1.isChecked());
                } else if (cb2.isChecked()) {
                    edit.putString("Number",number);
                    edit.putString("Password",password);
                    edit.putBoolean("Checkbox1",false);
                    edit.putBoolean("Checkbox2",cb2.isChecked());
                } else {
                    edit.putBoolean("Checkbox1",false);
                    edit.putBoolean("Checkbox2",false);
                }
                edit.apply();
            }
        });
    }

    private void initData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Record",MODE_PRIVATE);
        boolean checkbox1 = sharedPreferences.getBoolean("Checkbox1", false);
        boolean checkbox2 = sharedPreferences.getBoolean("Checkbox2", false);
        String number = sharedPreferences.getString("Number","");
        String password = sharedPreferences.getString("Password","");
        if (checkbox1) {
            et1.setText(number);
            et2.setText(password);
            cb1.setChecked(true);
            cb3.setChecked(true);
        } else if (checkbox2) {
            et1.setText(number);
            et2.setText(password);
            et2.setSelection(et2.length());
            cb2.setChecked(true);
            cb3.setChecked(true);
        }
    }

    private void initView() {
        bt = findViewById(R.id.btn_login);
        et1 = findViewById(R.id.et_number);
        et2 = findViewById(R.id.et_password);
        cb1 = findViewById(R.id.checkBox2);
        cb2 = findViewById(R.id.checkBox3);
        cb3 = findViewById(R.id.checkBox4);
    }

    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("请重新注册");
        builder.create().show();
    }

    public void Register(View view) {
        startActivityForResult((new Intent(this, RegisterActivity.class)),REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RegisterActivity.RESULT_CODE && data != null) {
            Bundle extras = data.getExtras();
            String number = extras.getString("Number","");
            String password = extras.getString("Password","");
            et1.setText(number);
            et2.setText(password);
        }
    }
}