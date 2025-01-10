package com.example.myqq;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    public static final int RESULT_CODE = 0;
    private Button bt;
    private EditText et1,et2,et3;
    private CheckBox cb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = et1.getText().toString();
                String password1 = et2.getText().toString();
                String password2 = et3.getText().toString();
                boolean checkbox = cb.isChecked();
                if (password1.equals(password2) && checkbox) {
                    Register(number,password1,checkbox);
                } else {
                    Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                }
            }

            private void Register(String number, String password, boolean checkbox) {
                SharedPreferences sharedPreferences = getSharedPreferences("Record",MODE_PRIVATE);
                SharedPreferences.Editor edit =sharedPreferences.edit();
                edit.putString("Number",number);
                edit.putString("Password",password);
                edit.putBoolean("Checkbox2",true);
                edit.putBoolean("Checkbox3",checkbox);
                edit.apply();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("Number",number);
                bundle.putString("Password",password);
                intent.putExtras(bundle);
                setResult(RESULT_CODE,intent);
                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void initView() {
        bt = findViewById(R.id.btn_register);
        et1 = findViewById(R.id.et_number);
        et2 = findViewById(R.id.et_password1);
        et3 = findViewById(R.id.et_password2);
        cb = findViewById(R.id.checkBox);
    }
}