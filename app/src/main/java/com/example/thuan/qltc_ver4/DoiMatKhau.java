package com.example.thuan.qltc_ver4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by truon_000 on 12/06/2015.
 */
public class DoiMatKhau extends Activity {
    EditText MKC,MKM,XNMK;
    Button XN,Cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doimatkhau);
        MKC =(EditText) findViewById(R.id.etMKC_DoiMatKhau);
        MKM =(EditText) findViewById(R.id.etMKM_DoiMatKhau);
        XNMK =(EditText) findViewById(R.id.etXNMK_DoiMatKhau);
        XN = (Button) findViewById(R.id.btnXN_DoiMatKhau);
        Cancel = (Button) findViewById(R.id.btnCancel_DoiMatKhau);
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        final Bundle receiveBundle = this.getIntent().getExtras();
        final Bundle sendBundle = new Bundle();
        final TextView title = (TextView) findViewById(R.id.textView18);

        sendBundle.putString("UserID", receiveBundle.getString("UserID"));
        XN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MKM.getText().toString().equals("") && !MKC.getText().toString().equals("") && !XNMK.getText().toString().equals("")) {
                    if (MKM.getText().toString().equals(XNMK.getText().toString())) {
                        Users user = databaseHelper.get_UserInforbyid(receiveBundle.getString("UserID"));
                        if (MKC.getText().toString().equals(user.getMatKhau())) {
                            databaseHelper.update_User(user.getID(), MKM.getText().toString(), user.getTen(), user.getLuong());
                            final Intent i = new Intent(DoiMatKhau.this, MainMenu.class);
                            i.putExtras(sendBundle);
                            startActivity(i);
                            finish();
                            Toast.makeText(getApplicationContext(), "Đổi Mật Khẩu Thành Công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Mật Khẩu Cũ Không Chính Xác", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Xác Nhận và Mật Khẩu không trùng nhau", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Chưa Điền Đủ Thông Tin", Toast.LENGTH_LONG).show();
                }
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent i = new Intent(DoiMatKhau.this, MainMenu.class);
                i.putExtras(sendBundle);
                startActivity(i);
                finish();
            }
        });
    }
}
