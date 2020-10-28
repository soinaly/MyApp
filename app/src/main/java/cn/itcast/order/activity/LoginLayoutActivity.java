package cn.itcast.order.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

import cn.itcast.order.R;
import cn.itcast.order.utils.SPUtil;

public class LoginLayoutActivity extends AppCompatActivity {

    //1.定义对象
    private EditText etUsername;
    private EditText etPassword;
    private CheckBox cbAutoLogin;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        //2.初始化控件对象
        etUsername = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_password);
        cbAutoLogin = findViewById(R.id.cb_auto_login);
        btnLogin = findViewById(R.id.btn_login);

        Map<String,String> data = SPUtil.getAll(this);
        if(data.size()>0){
            etUsername.setText(data.get("account"));
            etPassword.setText(data.get("password"));
        }


        //3.设置按钮的监听器
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    private void login(){
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(this,"用户名和密码都不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        if("soina".equals(username) && "123456".equals(password)){
            Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,ShopActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
            if (cbAutoLogin.isChecked()){
                SPUtil.saveAccont(this,username,password);
                Toast.makeText(LoginLayoutActivity.this,"存储且登陆成功",
                        Toast.LENGTH_LONG).show();
            }else {
                SPUtil.clear(this);
            }
        }else{
            Toast.makeText(this,"用户名或密码不正确",Toast.LENGTH_LONG).show();
        }

    }


}