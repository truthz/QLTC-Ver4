package com.example.thuan.qltc_ver4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by truon_000 on 12/06/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    Logger myLog = Logger.getLogger(MainActivity.class.getName());
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "QLTC.db";
    private static final String DB_PATH_SUFFIX = "/databases/";
    static Context ctx;

    private static final String USER_TABLE = "USERS";
    private static final String USER_ID = "ID";
    private static final String USER_PASS = "MATKHAU";
    private static final String USER_NAME = "TEN";
    private static final String USER_LUONG = "LUONG";

    private static final String TK_TABLE = "TAISAN";
    private static final String TK_ID = "ID";
    private static final String TK_NAME = "TEN";
    private static final String TK_TONG = "TONG";

    private static final String GD_TABLE = "GIAODICH";
    private static final String GD_ID = "ID";
    private static final String GD_USER_ID = "USERID";
    private static final String GD_BENT2 = "BENT2";
    private static final String GD_TK = "TK";
    private static final String GD_TYPE = "LOAIGD";
    private static final String GD_SOTIEN = "SOTIEN";
    private static final String GD_DATE = "NGAY";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx = context;
    }
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    public void Create_User(String id,String matkhau,String ten,int luong){
        try
        {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(USER_ID,id);
            values.put(USER_PASS,matkhau);
            values.put(USER_NAME,ten);
            values.put(USER_LUONG, luong);
            long newRowID = database.insert(USER_TABLE,null,values);
            myLog.severe("Create_User - Thanh Cong");
        }
        catch (Exception e){myLog.severe("Create_User - That Bai" +e.toString());}
    }
    public Users get_UserInfor(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String  selection = USER_NAME +" =?";
        String [] arg = {String.valueOf(name)};
        String [] columns = {USER_ID,USER_PASS,USER_NAME,USER_LUONG};
        Cursor cursor = db.query(USER_TABLE,columns,selection,arg,null,null,null);
        if (cursor != null && cursor.moveToFirst()){
            Users user = new Users(cursor.getString(0),cursor.getString(1), cursor.getString(2),cursor.getInt(3));
            // return user
            cursor.close();
            db.close();
            return user;
        }
        return null;
    }
    public Users get_UserInforbyid(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String  selection = USER_ID +" =?";
        String [] arg = {String.valueOf(id)};
        String [] columns = {USER_ID,USER_PASS,USER_NAME,USER_LUONG};
        Cursor cursor = db.query(USER_TABLE,columns,selection,arg,null,null,null);
        if (cursor != null && cursor.moveToFirst()){
            Users user = new Users(cursor.getString(0),cursor.getString(1), cursor.getString(2),cursor.getInt(3));
            // return user
            cursor.close();
            db.close();
            return user;
        }
        return null;
    }
    public void update_User(String id,String matkhau,String ten,int luong){
        try{
            SQLiteDatabase database = this.getReadableDatabase();
            String selection = USER_ID + " =?";
            String[] selectionArgs = { String.valueOf(id)};
            ContentValues values =new ContentValues();
            values.put(USER_ID,id);
            values.put(USER_PASS,matkhau);
            values.put(USER_NAME,ten);
            values.put(USER_LUONG,luong);
            database.update(USER_TABLE, values, selection, selectionArgs);
            myLog.info("update_User - Thanh Cong");
        }catch (Exception e){myLog.severe("update_User - That Bai" +e.toString());}

    }
    public void Create_TK(String id,String ten,int tong){
        try
        {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TK_ID,id);
            values.put(TK_NAME,ten);
            values.put(TK_TONG, tong);
            long newRowID = database.insert(TK_TABLE,null,values);
            myLog.severe("Create_TK - Thanh Cong");
        }
        catch (Exception e){myLog.severe("Create_TK - That Bai" +e.toString());}
    }
    public List<String> get_TK(){
        List<String> TK_list = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM "+TK_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        try{
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    long lID = cursor.getInt(0);
                    String sTen = cursor.getString(1);
                    String sTong = cursor.getString(2);
                    TK_list.add("Ten TK: "+sTen+"\nTong : "+sTong);

                } while (cursor.moveToNext());
            }
        }catch(Exception e){
        }
        return TK_list;
    }
    public boolean Check_TK(String tentk){
        boolean check=false;
        List<String> list = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM "+TK_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        try{
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    String sTen = cursor.getString(1);
                    list.add(sTen);

                } while (cursor.moveToNext());
            }
        }catch(Exception e){
        }
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals(tentk)){
                check = true;
            }
        }
        return check;
    }
    public void update_TK(String tentk,int tong){
        try{
            SQLiteDatabase database = this.getReadableDatabase();
            String selection = TK_NAME + " =?";
            String[] selectionArgs = { tentk};
            ContentValues values =new ContentValues();
            values.put(TK_TONG,tong);
            database.update(TK_TABLE, values, selection, selectionArgs);
            myLog.info("update_TK - Thanh Cong");
        }catch (Exception e){myLog.severe("update_TK - That Bai" +e.toString());}

    }
    public void delete_TK(String id){
        try{
            SQLiteDatabase database = this.getReadableDatabase();
            String selection = TK_ID + " =?";
            String[] selectionArgs = { id};
            database.delete(TK_TABLE, selection, selectionArgs);
            myLog.info("delete_TK - Thanh Cong");
        }catch (Exception e){myLog.severe("delete_TK - That Bai" +e.toString());}
    }
    public int gettong_TK(String tentk){
        SQLiteDatabase db = this.getReadableDatabase();
        String  selection = USER_NAME +" =?";
        String [] arg = {String.valueOf(tentk)};
        String [] columns = {TK_TONG};
        Cursor cursor = db.query(TK_TABLE,columns,selection,arg,null,null,null);
        if (cursor != null && cursor.moveToFirst()){
            int Tong = cursor.getInt(0);
            // return user
            cursor.close();
            db.close();
            return Tong;
        }
        return 0;
    }
    public void GiaoDIch(String id,String userid,String benT2,String tentk,String loaiGD,int sotien,String date) {
        try
        {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(GD_ID,id);
            values.put(GD_USER_ID,userid);
            values.put(GD_BENT2,benT2);
            values.put(GD_TK,tentk);
            values.put(GD_TYPE,loaiGD);
            values.put(GD_SOTIEN, sotien);
            values.put(GD_DATE, date);
            long newRowID = database.insert(GD_TABLE,null,values);
            myLog.severe("GiaoDich - Thanh Cong");
        }
        catch (Exception e){myLog.severe("GiaoDich - That Bai" +e.toString());}
    }
    public void update_GD(String id,String benT2,String tentk,String loaiGD,int sotien,String date){
        try{
            SQLiteDatabase database = this.getReadableDatabase();
            String selection = GD_ID + " =?";
            String[] selectionArgs = { id};
            ContentValues values =new ContentValues();
            values.put(GD_ID,id);
            values.put(GD_BENT2,benT2);
            values.put(GD_TK,tentk);
            values.put(GD_TYPE,loaiGD);
            values.put(GD_SOTIEN, sotien);
            values.put(GD_DATE, date);
            database.update(GD_TABLE, values, selection, selectionArgs);
            myLog.info("update_GD - Thanh Cong");
        }catch (Exception e){myLog.severe("update_GD - That Bai" +e.toString());}
    }
    public void delete_GD(String id){
        try{
            SQLiteDatabase database = this.getReadableDatabase();
            String selection = GD_ID + " =?";
            String[] selectionArgs = { id};
            database.delete(GD_TABLE, selection, selectionArgs);
            myLog.info("delete_GD - Thanh Cong");
        }catch (Exception e){myLog.severe("delete_GD - That Bai" +e.toString());}
    }
    public int get_number(String tablename){
        List<String> list = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM "+tablename;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        try{
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    long lID = cursor.getInt(0);
                    list.add(lID+"");

                } while (cursor.moveToNext());
            }
        }catch(Exception e){
        }

        return list.size();
    }
    public List<String> get_GDInfor(){
        List<String> GD_list = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + GD_TABLE;//+" WHERE "+ column+"="+obj;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        try{
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    String sID= cursor.getString(0);
                    String sUSERID= cursor.getString(1);
                    String benT2 = cursor.getString(2);
                    String taikhoan = cursor.getString(3);
                    String loaiGD = cursor.getString(4);
                    String sotien = cursor.getString(5);
                    String ngay = cursor.getString(6);
                    if(loaiGD.equals("Thu")) {benT2 = "t? " +benT2;taikhoan= "Thêm vào: " +taikhoan;}
                    else {benT2 = "cho " +benT2;taikhoan=  "Rút t?: " +taikhoan;}
                    GD_list.add(sUSERID+"\nNgày: "+ngay+"\n"+loaiGD+": "+sotien+" "+benT2+"\n"+taikhoan);

                } while (cursor.moveToNext());
            }
        }catch(Exception e){
        }
        return GD_list;
    }
    public List<GiaoDich> get_GD_Infor(){
        List<GiaoDich> GD_list = new ArrayList<GiaoDich>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + GD_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        try{
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    String sID= cursor.getString(0);
                    String sUSERID= cursor.getString(1);
                    String benT2 = cursor.getString(2);
                    String taikhoan = cursor.getString(3);
                    String loaiGD = cursor.getString(4);
                    int sotien = cursor.getInt(5);
                    String ngay = cursor.getString(6);
                    try {
                        GD_list.add(new GiaoDich(sID,sUSERID,benT2,taikhoan,loaiGD,sotien,ngay));
                        myLog.severe("Add List Thanh Cong");
                    }catch (Exception e){}


                } while (cursor.moveToNext());
            }
        }catch(Exception e){
        }
        return GD_list;
    }
    public void CopyDataBaseFromAsset() throws IOException {

        InputStream myInput = ctx.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = getDatabasePath();

        // if the path doesn't exist first, create it
        File f = new File(ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }
    private static String getDatabasePath() {
        return ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DATABASE_NAME;
    }
    public SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = ctx.getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                System.out.println("Copying sucess from Assets folder");
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
