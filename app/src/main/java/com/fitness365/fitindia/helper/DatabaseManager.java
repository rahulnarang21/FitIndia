package com.fitness365.fitindia.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fitness365.fitindia.activities.CreateAccountActivity;
import com.fitness365.fitindia.activities.LoginActivity;
import com.fitness365.fitindia.models.ListViewDialogModel;
import com.fitness365.fitindia.models.StateDistrictBlockModel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "fitindia.db";
    public static final int DB_VERSION = 1;
    public static final String TBL_STATE_DISTRICT_BLOCK_MASTER = "state_district_block_master";
    public static final String TBL_STATE_MASTER = "state_master";
    public static final String TBL_DISTRICT_MASTER = "district_master";
    public static final String TBL_BLOCK_MASTER = "block_master";
    public static final String CREATE_TBL_STATE_MASTER = "create table " + TBL_STATE_MASTER + "(state_id int(30) PRIMARY KEY,state_name varchar(30))";
    public static final String CREATE_TBL_DISTRICT_MASTER = "create table " + TBL_DISTRICT_MASTER + "(district_id int(255) PRIMARY KEY,district_name varchar(30),state_id int(30))";
    public static final String CREATE_TBL_BLOCK_MASTER = "create table " + TBL_BLOCK_MASTER + "(block_id int(255) PRIMARY KEY,block_name varchar(30),district_id int(255),state_id int(30))";
    public static final String CREATE_TBL_STATE_DISTRICT_BLOCK_MASTER = "create table "+TBL_STATE_DISTRICT_BLOCK_MASTER + "(row_id integer primary key autoincrement,id integer(255),value varchar(255),parent_id integer(255),type integer(30))";

    public DatabaseManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL(CREATE_TBL_STATE_MASTER);
//        sqLiteDatabase.execSQL(CREATE_TBL_DISTRICT_MASTER);
//        sqLiteDatabase.execSQL(CREATE_TBL_BLOCK_MASTER);
        sqLiteDatabase.execSQL(CREATE_TBL_STATE_DISTRICT_BLOCK_MASTER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<Object> getAllTableData(Context context, String TBL_NAME, String ARGS1, String ARGS2, String ARGS3, String ARGS4) {
        ArrayList<Object> aData = new ArrayList<Object>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        if (TBL_NAME.equalsIgnoreCase(TBL_STATE_DISTRICT_BLOCK_MASTER)){
            try{
                Cursor cursor = null;
                String query = "";
                if (ARGS4.equals(""))
                    query = "select * from " + TBL_STATE_DISTRICT_BLOCK_MASTER+" where "+ARGS1+"="+ARGS2;
                else
                    query = "select * from " + TBL_STATE_DISTRICT_BLOCK_MASTER+" where "+ARGS1+"="+ARGS2+" and "+ARGS3+"="+ARGS4;
                cursor = sqLiteDatabase.rawQuery(query,null);
                if (cursor.moveToFirst()) {
                    do {
                        ListViewDialogModel stateModel = new ListViewDialogModel(
                                cursor.getInt(cursor.getColumnIndex(AppConfig.ID)),
                                cursor.getString(cursor.getColumnIndex(AppConfig.VALUE)),false);
                        aData.add(stateModel);

                    } while (cursor.moveToNext());
                }

                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return aData;
    }

    public void insertMultipleValues(String TBL_NAME, List<StateDistrictBlockModel.Result> data, CreateAccountActivity.saveStateDistrictDataTask reference){
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            String query = "";
            if (TBL_NAME.equals(TBL_STATE_DISTRICT_BLOCK_MASTER)) {
                int count=0;
                for (Object object : data) {
                    query = "insert into " + TBL_NAME;
                    StateDistrictBlockModel.Result stateModel = (StateDistrictBlockModel.Result) object;
                    query += "(id,value,parent_id,type) values(" + stateModel.getId() + ",'" + stateModel.getValue() + "'," + stateModel.getParentId() + "," + stateModel.getType() + ")";
                    sqLiteDatabase.execSQL(query);
                    count++;
                    reference.doProgress(count,data.size());
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

//        else if (TBL_NAME.equals(TBL_STATE_MASTER)){
//            ListViewDialogModel stateModel = (ListViewDialogModel) data;
//            query += " values("+stateModel.getId()+","+stateModel.getValue()+")";
//        }
//        else if (TBL_NAME.equals(TBL_DISTRICT_MASTER)){
//            ListViewDialogModel stateModel = (ListViewDialogModel) data;
//            query += " values("+stateModel.getId()+","+stateModel.getValue()+","+stateModel.getParentId()+")";
//        }
//        else if (TBL_NAME.equals(TBL_BLOCK_MASTER)){
//            ListViewDialogModel stateModel = (ListViewDialogModel) data;
//            query += " values("+stateModel.getId()+","+stateModel.getValue()+","+stateModel.getParentId()+")";
//        }


    }
}
