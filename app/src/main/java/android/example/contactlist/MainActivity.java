package android.example.contactlist;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.example.contactlist.Adapter.ContactAdapter;
import android.example.contactlist.Model.Listdata;
import android.example.contactlist.databaseUtils.DatabaseHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText name,phone,email,addr;
    List<Listdata> list = new ArrayList<>();
    String[] n;
    Context context=this;
    SQLiteDatabase sqLiteDatabase;
    public static DatabaseHelper myHelper;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        addr=findViewById(R.id.address);
        save=findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                String p=phone.getText().toString();
                String e=email.getText().toString();
                String a=addr.getText().toString();
                if(TextUtils.isEmpty(n) || TextUtils.isEmpty(p) || TextUtils.isEmpty(e) || TextUtils.isEmpty(a)){
                    return;
                }
//
                  myHelper=new DatabaseHelper(MainActivity.this);
                Listdata l=new Listdata();
                l.setName(n);
                l.setEmail(e);
                l.setCity(a);
                l.setPhone(p);
                if(myHelper.addContact(l)){
                Toast.makeText(MainActivity.this, "Contact Saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Home.class));}

            }
        });


    }

}
