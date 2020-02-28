package com.skillhunternaim.anotherloser.passwordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="PasswordsDB";
    public static final String TABLE_NAME="PasswordsTB";
    public static final String COL0="ID";
    public static final String COL1="Title";
    public static final String COL2="website";
    public static final String COL3="password";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+
                COL0+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL1+" TEXT, "+
                COL2+" TEXT, "+
                COL3+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
    }
    public boolean addPass(String title, String website,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL1,title);
        contentValues.put(COL2,website);
        contentValues.put(COL3,password);
        long res=db.insert(TABLE_NAME,null,contentValues);
        if(res==-1)return false;
        return  true;
    }
    public ArrayList<HashMap<String, String>> getAllNoteList(){
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<HashMap<String, String>> noteslist = new ArrayList<>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        while (cursor.moveToNext()){
            HashMap<String,String> note = new HashMap<>();
            note.put("title",cursor.getString(cursor.getColumnIndex(COL1)).trim());
            note.put("website",cursor.getString(cursor.getColumnIndex(COL2)).trim());
            note.put("password",cursor.getString(cursor.getColumnIndex(COL3)).trim());
            noteslist.add(note);
        }

        return  noteslist;
    }

    public boolean updateInfo(String title, String note, String date, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(COL1,title);
        contentValues.put(COL2,note);
        contentValues.put(COL3,password);
        long res=db.update(TABLE_NAME,contentValues,COL1+" =?",new String[]{title});
        if(res>0)return true;
        return  false;
    }
    public int deleteNote(String title, String web, String pass){
        SQLiteDatabase db= this.getWritableDatabase();
        int res=db.delete(TABLE_NAME,COL1+" =? AND "+COL2+" =? AND "+COL3+" =? " ,new String[]{title,web,pass});
        return res;
    }

}
