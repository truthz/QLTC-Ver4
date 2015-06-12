package com.example.thuan.qltc_ver4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by truon_000 on 12/06/2015.
 */
public class ThongKe extends Activity {
    Button Return,OK;
    TextView title;
    EditText nam;
    ArrayAdapter<String > adapter=null;
    ListView LV1,LV2;
    List<String> Thu,Chi,thongke;
    DatabaseHelper databaseHelper;
    Bundle receiveBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongke);
        receiveBundle = this.getIntent().getExtras();
        final Bundle sendBundle = new Bundle();
        sendBundle.putString("UserID", receiveBundle.getString("UserID"));
        databaseHelper = new DatabaseHelper(this);
        Date date = new Date();
        SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("yyyy");
        Return = (Button) findViewById(R.id.btnreturn_ThongKe);
        OK = (Button) findViewById(R.id.btnnam_ThongKe);
        nam = (EditText) findViewById(R.id.etnam_ThongKe);
        title = (TextView) findViewById(R.id.title_ThongKe);
        LV1=(ListView) findViewById(R.id.LV1_ThongKe);
        //1. Tạo ArrayList object
        nam.setText(dinhDangThoiGian.format(date.getTime()));
        nam.requestFocus();
        thongke = new ArrayList<String>();
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nam.getText().toString().equals("")){

                    thuchi_thang(nam.getText().toString());
                    Thu = new ArrayList<String>();
                    phanloai(Thu,"Thu");
                    Chi = new ArrayList<String>();
                    phanloai(Chi, "Chi");
                    setListView(LV1, thongke);
                }
            }
        });



        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(ThongKe.this, MainMenu.class);
                i.putExtras(sendBundle);
                startActivity(i);
                finish();
            }
        });
    }
    public void phanloai(List<String> list,String obj){
        String A=null,B=null;
        if(obj.equals("Thu")){
            A=" Từ ";B="Thêm Vào ";
        }else
        if(obj.equals("Chi")) {
            A=" Cho ";B="Rút Từ ";
        }
        List<GiaoDich> listgd  = databaseHelper.get_GD_Infor();
        for(int i=0;i<(listgd.size());i++){
            if(listgd.get(i).getLoai_GD().equals(obj)){
                list.add("Ngày: "+listgd.get(i).getNgay() +"\n"+ listgd.get(i).getLoai_GD()+": "+listgd.get(i).getSoTien()+" VNĐ "+A+listgd.get(i).getBenThu2()+"\n"+B+ listgd.get(i).getTen_TK());
            }
        }
        listgd.clear();
    }
    public void setListView(ListView L,List<String> List) {

        ArrayAdapter<String> A = new ArrayAdapter<String>
                (this,
                        android.R.layout.simple_list_item_1,
                        List);
        L.setAdapter(A);
        L.setOnItemClickListener(new AdapterView
                .OnItemClickListener() {
            public void onItemClick(
                    AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
                List<GiaoDich> listgd  = databaseHelper.get_GD_Infor();
                String info="";

                for (int i =0;i<listgd.size();i++) {
                    if (listgd.get(i).getNgay().substring(6, 10).equals(nam.getText().toString())) {
                        if (Integer.parseInt(listgd.get(i).getNgay().substring(3, 5)) == (arg2 + 1)) {
                            if(listgd.get(i).getLoai_GD().equals("Thu")||listgd.get(i).getLoai_GD().equals("Chi")) {
                                info += "Ngày: " + listgd.get(i).getNgay() + "\n" + listgd.get(i).getLoai_GD() + ": " + listgd.get(i).getSoTien() + "\n";
                            }
                        }
                    }
                }
                final AlertDialog alertDialog = new AlertDialog.Builder(ThongKe.this).create();
                alertDialog.setTitle("Tháng "+(arg2+1));
                alertDialog.setMessage(info);
                alertDialog.show();
                listgd.clear();
            }
        });
    }
    public void thuchi_thang(String nam){
        int [] thu= new int[13];
        int [] chi= new int[13];
        thongke.clear();
        List<GiaoDich> listgd  = databaseHelper.get_GD_Infor();
        for (int i =0;i<listgd.size();i++) {
            title.setText("Thống Kê Năm "+listgd.get(i).getNgay().substring(6, 10));
            if (listgd.get(i).getNgay().substring(6, 10).equals(nam)) {
                if (listgd.get(i).getLoai_GD().equals("Thu")) {
                    thu[Integer.parseInt(listgd.get(i).getNgay().substring(3, 5))] += listgd.get(i).getSoTien();
                } else if (listgd.get(i).getLoai_GD().equals("Chi")) {
                    chi[Integer.parseInt(listgd.get(i).getNgay().substring(3, 5))] += listgd.get(i).getSoTien();
                }
            }
        }
        for (int j=1;j<=12;j++){
            thongke.add("Tháng " + j + ":\n        Thu: " + thu[j] + " VNĐ\n" + "        Chi: " + chi[j] + " VNĐ\n" + "        Dư: " + (thu[j] - chi[j]) + " VNĐ ");
        }
        listgd.clear();
    }
}
