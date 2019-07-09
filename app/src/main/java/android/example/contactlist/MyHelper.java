package android.example.contactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="USERINFO.DB";
    private static final int DATABASE_VERSION=1;
    SQLiteDatabase db;
    private static final String Create_query=
            "CREATE TABLE "+ User.NewUser.TABLE_NAME+"("+ User.NewUser.USER_NAME+" TEXT,"+
                    User.NewUser.USER_PHONE+" TEXT,"+ User.NewUser.USER_EMAIL+" TEXT,"+ User.NewUser.USER_ADDRESS+" TEXT);";

    public MyHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_query);
        this.db=db;
    }
    public void addContact(String name,String phone,String email,String address,SQLiteDatabase db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(User.NewUser.USER_NAME,name);
        contentValues.put(User.NewUser.USER_PHONE,phone);
        contentValues.put(User.NewUser.USER_EMAIL,email);
        contentValues.put(User.NewUser.USER_ADDRESS,address);
        db.insert(User.NewUser.TABLE_NAME,null,contentValues);

    }
    public Cursor retrieveData(SQLiteDatabase db){
        Cursor cursor;
        String[] image={User.NewUser.USER_NAME, User.NewUser.USER_PHONE, User.NewUser.USER_EMAIL, User.NewUser.USER_ADDRESS};
        cursor=db.query(User.NewUser.TABLE_NAME,image,null,null,null,null,null);
        return cursor;
    }

    public Integer deleteContact(String ur_name){
         SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
         String selection = User.NewUser.USER_NAME+" LIKE?";
         return  sqLiteDatabase.delete(User.NewUser.TABLE_NAME,selection,new String[] {ur_name});
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + User.NewUser.TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
