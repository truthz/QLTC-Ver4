package com.example.thuan.qltc_ver4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by truon_000 on 12/06/2015.
 */
public class DiVay extends Activity {
    Button XN,cancel;
    EditText Sotien,BenT2,Ngay,TK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.divay);
        XN = (Button) findViewById(R.id.btnXN_DiVay);
        cancel = (Button) findViewById(R.id.btnCancel_DiVay);
        Sotien = (EditText) findViewById(R.id.etSoTien_DiVay);
        BenT2 = (EditText) findViewById(R.id.etBent2_DiVay);
        Ngay = (EditText) findViewById(R.id.etdate_DiVay);
        Date date = new Date();
        SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("dd/MM/yyyy ");
        Ngay.setText( dinhDangThoiGian.format(date.getTime()));
        TK = (EditText) findViewById(R.id.etTK_DiVay);
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        final Bundle receiveBundle = this.getIntent().getExtras();
        final Bundle sendBundle = new Bundle();
        sendBundle.putString("UserID", receiveBundle.getString("UserID"));
        XN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Sotien.getText().toString().equals("")&&
                        !BenT2.getText().toString().equals("")&&
                        !Ngay.getText().toString().equals("")
                        ){
                    String sID="GD"+databaseHelper.get_number("GIAODICH");
                    String sUserID= receiveBundle.getString("UserID");
                    String sBenT2 = BenT2.getText().toString();
                    String sTenTK = null;
                    if (TK.getText().toString().equals("")) {sTenTK = "Túi";
                    } else {
                        sTenTK = TK.getText().toString();
                    }
                    if(!databaseHelper.Check_TK(sTenTK)){
                        String sid ="TK"+ databaseHelper.get_number("TAISAN");
                        databaseHelper.Create_TK(sid,sTenTK,0);
                    }
                    String sDate = Ngay.getText().toString();
                    int sotien = Integer.parseInt(Sotien.getText().toString());
                    databaseHelper.GiaoDIch(sID,sUserID,sBenT2,sTenTK,"DiVay",sotien,sDate);
                    databaseHelper.update_TK(sTenTK, databaseHelper.gettong_TK(sTenTK) + sotien);
                    final Intent i = new Intent(DiVay.this, Vay.class);
                    i.putExtras(sendBundle);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Chưa Điền Đầy Đủ Thông Tin", Toast.LENGTH_LONG).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(DiVay.this, Vay.class);
                i.putExtras(sendBundle);
                startActivity(i);
                finish();
            }
        });
    }
}

