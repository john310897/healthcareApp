package com.example.ifyoucanhelp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

class Helper extends SQLiteOpenHelper {
    public static String DATABASE_NAME="mydb.db";
    public static String TABLE_NAME="info";
    public static String COL_1="id";
    public  static String COL_2="name";
    public static String COL_3="marks";
    public static final String TABLE_CREATE="CREATE TABLE inifo(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,marks INT(2))";
    private Context context;

    public Helper(Context context)
    {
        super(context,DATABASE_NAME,null,3);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(context,"in oncreate",Toast.LENGTH_SHORT).show();
        try {
            db.execSQL(TABLE_CREATE);
        }
        catch (Exception e)
        {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context,"im in upgrade",Toast.LENGTH_SHORT).show();
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }

    public boolean insert_data(String name,String marks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL_2,name);
        cv.put(COL_3,marks);
        long result=db.insert(TABLE_NAME,null,cv);
        if(result==-1)
            return
                    false;
        else
            return true;
    }

    protected Cursor getALLData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return (res);
    }

    public boolean update_data(String id,String name,String marks)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues cv=new ContentValues();
        Cursor c=db.rawQuery("SELECT * from " + TABLE_NAME+"WHERE id="+id,null);
        if (c.moveToNext())
        {
            cv.put(COL_2,name);
            cv.put(COL_3,marks);
            long res=db.update(TABLE_NAME,cv,"id="+id,null);
            if(res==-1) return false;
            else
                return true;
        }
        else
        {
            return false;
        }
    }

    public boolean delete_data(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        Cursor c = db.rawQuery("SELECT * from " + TABLE_NAME + "WHERE id=" + id, null);
        if (c.moveToNext()) {
            long res = db.delete(TABLE_NAME, "id=" + id, null);
            if (res == -1) return false;
            else {
                return true;
            }
        }
        return false;

    }
        protected Cursor getSpecificData(String id)
        {
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues cv = new ContentValues();

            Cursor res=db.rawQuery("SELECT * FROM " +TABLE_NAME+"WHERE id " +id,null);
            return (res);
        }

         /*public boolean updatedata(String id,String name,String marks)
        {
            SQLiteDatabase
        }*/
    }



