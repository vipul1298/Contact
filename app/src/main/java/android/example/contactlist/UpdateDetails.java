package android.example.contactlist;

import android.content.Intent;
import android.example.contactlist.Model.Listdata;
import android.example.contactlist.databaseUtils.DatabaseHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class UpdateDetails extends AppCompatActivity {
    Button up;
    EditText mn,mp,me,ma;
    String Mn,Mp,Me,Ma;
    DatabaseHelper myhelper;
    int ID;
    List<Listdata> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);
        up=findViewById(R.id.update);
        mn=findViewById(R.id.name);
        mp=findViewById(R.id.phone);
        me=findViewById(R.id.email);
        ma=findViewById(R.id.address);

        Intent i=getIntent();
        String getn=i.getStringExtra("mname");
        String getp =i.getStringExtra("mphone");
        String gete =i.getStringExtra("memail");
        String geta =i.getStringExtra("maddr");
        ID=i.getIntExtra("mid",0);

        mn.setText(getn);
        mp.setText(getp);
        me.setText(gete);
        ma.setText(geta);



//        up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Mn=mn.getText().toString();
//                Mp=mp.getText().toString();
//                Me=me.getText().toString();
//                Ma=ma.getText().toString();
//                myhelper = new DatabaseHelper(UpdateDetails.this);
//                Listdata listdata = new Listdata();
//                listdata.setName(Mn);
//                listdata.setPhone(Mp);
//                listdata.setEmail(Me);
//                listdata.setCity(Ma);
//                myhelper.updateContact(listdata);
//
//            }
//        });

    }
}
