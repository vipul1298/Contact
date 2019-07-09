package android.example.contactlist;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.example.contactlist.Adapter.ContactAdapter;
import android.example.contactlist.Model.Listdata;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] n;
    List<Listdata> list =new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;
    MyHelper myHelper;
    Cursor cursor;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myHelper=new MyHelper(getApplicationContext());
        sqLiteDatabase=myHelper.getReadableDatabase();
        cursor=myHelper.retrieveData(sqLiteDatabase);
        if(cursor.moveToFirst()){
            do{
               String name=cursor.getString(0);
               String phone=cursor.getString(1);
               String email=cursor.getString(2);
               String address=cursor.getString(3);
               Listdata listdata = new Listdata(name,phone,email,address);
               list.add(listdata);

            }while(cursor.moveToNext());
        }

                final ContactAdapter contactAdapter=new ContactAdapter(list,this);
        recyclerView.setAdapter(contactAdapter);


       //Contact delete


        progressDialog = new ProgressDialog(Home.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Here you can send the extras.
//                Intent i = new Intent(this, NextActivity.class);
//                startActivity(i);
//
//                // close this activity
//                finish();
                progressDialog.dismiss();
            }
        }, 2000);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
