package com.example.thuan.qltc_ver4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by truon_000 on 12/06/2015.
 */
public class Login extends Activity {
    TextView tv;
    EditText name,pass;
    Button login,create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.login);
        tv= (TextView) findViewById(R.id.textView4);
        name = (EditText) findViewById(R.id.etUser);
        pass = (EditText) findViewById(R.id.etPass_Logup);
        login = (Button) findViewById(R.id.btnDN);
        create = (Button) findViewById(R.id.btnTM);
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.openDataBase();
        final Bundle sendBundle = new Bundle();
        tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                name.setText("thuan");
                pass.setText("111111");
                return false;
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().equals("") && !pass.getText().toString().equals("")) {
                    Users user = databaseHelper.get_UserInfor(name.getText().toString());
                    if (user != null) {
                        if (pass.getText().toString().equals(user.getMatKhau())) {
                            sendBundle.putString("UserID", user.getID());
                            final Intent i = new Intent(Login.this, MainMenu.class);
                            i.putExtras(sendBundle);
                            startActivity(i);
                            finish();
                            Toast.makeText(getApplicationContext(), "Đăng Nhập Thành Công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Sai Mật Khẩu", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Tài Khoản Không Tồn Tại", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(Login.this, Logup.class);
                startActivity(i);
                finish();
            }
        });
    }
}
