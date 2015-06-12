package com.example.thuan.qltc_ver4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by truon_000 on 12/06/2015.
 */
public class Vay extends Activity {
    LinearLayout llDiVay,llChoVay;
    ImageButton btnDiVay,btnChoVay;
    Button btnreturn;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vay);
        databaseHelper = new DatabaseHelper(this);
        final Bundle receiveBundle = this.getIntent().getExtras();
        final Bundle sendBundle = new Bundle();
        sendBundle.putString("UserID", receiveBundle.getString("UserID"));
        llChoVay =(LinearLayout) findViewById(R.id.llChoVay_Vay);
        llDiVay =(LinearLayout) findViewById(R.id.llDiVay_Vay);
        btnChoVay = (ImageButton) findViewById(R.id.btnChoVay_Vay);
        btnDiVay = (ImageButton) findViewById(R.id.btnDiVay_Vay);
        btnreturn = (Button) findViewById(R.id.btnreturn_Vay);
        set();
        btnChoVay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(Vay.this, ChoVay.class);
                i.putExtras(sendBundle);
                startActivity(i);
                finish();
            }
        });
        btnDiVay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(Vay.this, DiVay.class);
                i.putExtras(sendBundle);
                startActivity(i);
                finish();
            }
        });
        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(Vay.this, MainMenu.class);
                i.putExtras(sendBundle);
                startActivity(i);
                finish();
            }
        });
    }
    public void set() {
        List<GiaoDich> listgd = databaseHelper.get_GD_Infor();
        for (int i = 0; i < listgd.size(); i++) {
            if (listgd.get(i).getLoai_GD().equals("ChoVay")) {
                createCheckBox(llChoVay, listgd.get(i).getID(), listgd.get(i).BenThu2 + ": " + listgd.get(i).getSoTien());
            } else if (listgd.get(i).getLoai_GD().equals("DiVay")) {
                createCheckBox(llDiVay, listgd.get(i).getID(), listgd.get(i).BenThu2 + ": " + listgd.get(i).getSoTien());
            }
        }
        listgd.clear();
    }
    public void createCheckBox(final LinearLayout layout,final String id,String info){
        final CheckBox CB = new CheckBox(this);
        CB.setText(info);
        CB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(CB);
                databaseHelper.delete_GD(id);
            }
        });
        layout.addView(CB);
    }
}
