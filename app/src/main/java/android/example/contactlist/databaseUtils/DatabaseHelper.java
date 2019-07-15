package android.example.contactlist.databaseUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.example.contactlist.Model.Listdata;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.ID;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static String DATABASE_NAME="contacts";
    public static int DATABASE_VERSION= 2;
    public static String COLUMN_ID="id";
    public static String COLUMN_NAME="name";
    public static String COLUMN_MOBILE="mobile";
    public static String COLUMN_EMAIL="email";
    public static String TABLE_NAME="contactinfo";
    public static String COLUMN_ADD="address";
    public Context context;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createtable=" CREATE TABLE "+TABLE_NAME+" ( "+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +" "+COLUMN_NAME+" varchar(50) NOT NULL,"
                +" "+COLUMN_MOBILE+" varchar(200) NOT NULL,"
                +" "+COLUMN_EMAIL+" varchar(200) NOT NULL,"
                +" "+COLUMN_ADD+"  varchar(200) );";
        db.execSQL(createtable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }





    public boolean addContact(Listdata contact){
        SQLiteDatabase db1=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME, contact.getName());
        cv.put(COLUMN_MOBILE, contact.getPhone());
        cv.put(COLUMN_EMAIL, contact.getEmail());
        cv.put(COLUMN_ADD, contact.getCity());
        boolean h=db1.insert(TABLE_NAME,null,cv)!=-1;
        return  h;
    }


    public List<Listdata> getAllData(){
        List<Listdata> data=new ArrayList<>();
        SQLiteDatabase db2=getReadableDatabase();
        Cursor c=db2.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if(c.moveToFirst()){
            do{
//                dat.add(new Contact(c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
                data.add(new Listdata(c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4)));

            }while(c.moveToNext());
        }
        return  data;
    }



    public boolean deleteSingle(int id){

         SQLiteDatabase mSQLiteDatabase = super.getWritableDatabase();//To delete , database should be writable.
//        int rowDeleted = mSQLiteDatabase.delete(TABLE_NAME,id + " =?",
//                new String[] {String.valueOf(id)});
//        mSQLiteDatabase.close();//This is very important once database operation is done.
//        Toast.makeText(context, ""+rowDeleted, Toast.LENGTH_SHORT).show();
//        if(rowDeleted != 0){
//            return true;
//        }
//        return false;


        String where = "id" + "=" +id;

        boolean n=mSQLiteDatabase.delete(TABLE_NAME, where, null) != 0;
        Toast.makeText(context, ""+n, Toast.LENGTH_SHORT).show();
        return  n;
    }
//    public int updateContact(Listdata contact){
//        SQLiteDatabase db1=getWritableDatabase();
//        ContentValues cv=new ContentValues();
//        cv.put(COLUMN_NAME, contact.getName());
//        cv.put(COLUMN_MOBILE, contact.getPhone());
//        cv.put(COLUMN_EMAIL, contact.getEmail());
//        cv.put(COLUMN_ADD, contact.getCity());
//        return db1.update(TABLE_NAME,cv,COLUMN_ID + " = ? ",new String[]{String.valueOf(contact.getUser_id())});
//    }

}
