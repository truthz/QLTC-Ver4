package com.example.thuan.qltc_ver4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by truon_000 on 12/06/2015.
 */
public class MainMenu extends Activity {
    ImageButton thu,chi,thongke,vay,them,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        thu = (ImageButton) findViewById(R.id.btnThu_MainMenu);
        chi = (ImageButton) findViewById(R.id.btnChi_MainMenu);
        thongke = (ImageButton) findViewById(R.id.btnThongKe_MainMenu);
        them = (ImageButton) findViewById(R.id.btnthem_MainMenu);
        logout = (ImageButton) findViewById(R.id.btnlogout_MainMenu);
        vay = (ImageButton) findViewById(R.id.btnVay_MainMenu);
        Bundle receiveBundle = this.getIntent().getExtras();
        final Bundle sendBundle = new Bundle();
        sendBundle.putString("UserID", receiveBundle.getString("UserID"));
        thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(MainMenu.this, ThongKe.class);
                i.putExtras(sendBundle);
                startActivity(i);
                finish();
            }
        });
        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(MainMenu.this, Thu.class);
                i.putExtras(sendBundle);
                startActivity(i);
                finish();
            }
        });
        chi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(MainMenu.this, Chi.class);
                i.putExtras(sendBundle);
                startActivity(i);
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(MainMenu.this, Login.class);
                startActivity(i);
                finish();
            }
        });
        vay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(MainMenu.this, Vay.class);
                i.putExtras(sendBundle);
                startActivity(i);
                finish();
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(MainMenu.this, DoiMatKhau.class);
                i.putExtras(sendBundle);
                startActivity(i);
                finish();
            }
        });
    }
}
