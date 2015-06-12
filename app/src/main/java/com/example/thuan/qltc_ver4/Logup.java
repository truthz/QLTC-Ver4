package com.example.thuan.qltc_ver4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by truon_000 on 12/06/2015.
 */
public class Logup extends Activity {
    EditText Name,Pass,XN,Luong;
    Button Create,Cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logup);
        Name = (EditText) findViewById(R.id.etName_Logup);
        Pass = (EditText) findViewById(R.id.etPass_Logup);
        XN = (EditText) findViewById(R.id.etXN_Logup);
        Luong = (EditText) findViewById(R.id.etLuong_Logup);
        Create = (Button) findViewById(R.id.btnCreate_Logup);
        Cancel = (Button) findViewById(R.id.btnCancel_Logup);
        final DatabaseHelper databaseHelper =new DatabaseHelper(this);
        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Name.getText().toString().equals("")&&!Pass.getText().toString().equals("")&&!XN.getText().toString().equals("")&&!Luong.getText().toString().equals("")){
                    if(Pass.getText().toString().equals(XN.getText().toString())){
                        String sID = "USER"+(databaseHelper.get_number("USERS")+1);
                        String sPass = Pass.getText().toString();
                        String sName = Name.getText().toString();
                        databaseHelper.Create_User(sID, sPass, sName, Integer.parseInt(Luong.getText().toString()));
                        final Intent i = new Intent(Logup.this, Login.class);
                        startActivity(i);
                        finish();
                        Toast.makeText(getApplicationContext(), "Tạo Mới Thành Công", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Xác Nhận và Mật Khẩu không trùng nhau",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Chưa Điền Đủ Thông Tin",Toast.LENGTH_LONG).show();
                }
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(Logup.this, Login.class);
                startActivity(i);
                finish();
            }
        });
    }
}
