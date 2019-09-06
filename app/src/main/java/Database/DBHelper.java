package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserInfo.db";

    public DBHelper(Context contex) {
        super(contex, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + UserMaster.Users.TABLE_NAME+" (" +
                UserMaster.Users._ID+" INTEGER PRIMARY KEY, "+
                UserMaster.Users.COLUMN_NAME_USERNAME+" TEXT,"+
                UserMaster.Users.COLUMN_NAME_PASSWORD+" TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public long addInfo(String Username , String Passworsd) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_USERNAME , Username);
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD , Passworsd);
        long rownum = db.insert(UserMaster.Users.TABLE_NAME ,null , values);

        /*if(rownum > 0 ) {
            Log.d("Insert" , "Error");
        }
        else
        {
            Log.d("Insert" , "Data added Succesfully");
        }*/
        return rownum;
    }

    public List readAllInfo(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                UserMaster.Users._ID,
                UserMaster.Users.COLUMN_NAME_USERNAME,
                UserMaster.Users.COLUMN_NAME_PASSWORD

        };

        String sortOrder = UserMaster.Users.COLUMN_NAME_USERNAME+ "DESC";

        Cursor cursor = db.query(
            UserMaster.Users.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            sortOrder
        );

        List Username = new ArrayList();
        List Passworsd = new ArrayList();

        while (cursor.moveToNext()){
            String Usernames = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_USERNAME));
            String Passworsds = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_PASSWORD));

            Username.add(Usernames);
            Passworsd.add(Passworsds);
        }

        cursor.close();
        return Username;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int deleteData(String Username){
        SQLiteDatabase db = getReadableDatabase();

        String wherecondition = UserMaster.Users.COLUMN_NAME_USERNAME+ "= ?";

        String[] args = {Username};

        int result = db.delete(UserMaster.Users.TABLE_NAME,wherecondition,args);

        if (result > 0){
            return 1;
        }else{
            return -1;
        }
    }

    public boolean updatePassword(String Username, String Passworsd){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD,Passworsd);

        String whereCondition = UserMaster.Users.COLUMN_NAME_USERNAME+ " = ?";

        String[] args = {Username};

        int result = db.update(UserMaster.Users.TABLE_NAME,values,whereCondition,args);

        if (result > 0){
            return true;
        }else{
            return false;
        }
    }
}

