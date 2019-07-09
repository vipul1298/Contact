package android.example.contactlist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.example.contactlist.Model.Listdata;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Contact_Details extends AppCompatActivity {
    TextView n,p,e,a;
    ImageView c,m,ed,del;
    String ph="";
    MyHelper myHelper;
    SQLiteDatabase sqLiteDatabase;
    String mname;
    Listdata listdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__details);
        n=findViewById(R.id.name);
        p=findViewById(R.id.phone);
        e=findViewById(R.id.email);
        a=findViewById(R.id.address);
        c=findViewById(R.id.call);
        m=findViewById(R.id.msg);
        ed=findViewById(R.id.edit);
        del=findViewById(R.id.del);
        mname=n.getText().toString();
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p.getText().length()>9)
                    ph=p.getText().toString().trim();
                    opencall(ph);

            }
        });
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p.getText().length()>9){
                    String num=p.getText().toString().trim();
                    openchat(num);
                }

            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHelper=new MyHelper(getApplicationContext());
                Integer deletedRows=myHelper.deleteContact(mname);
                if(deletedRows>0){
                Toast.makeText(Contact_Details.this, "Contact deleted", Toast.LENGTH_SHORT).show();
                Intent i =new Intent(Contact_Details.this,Home.class);
//                i.putExtra("name",mname);
                startActivity(i);}
                else{
                    Toast.makeText(Contact_Details.this, "Contact not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent i=getIntent();
        String getname=i.getStringExtra("name");
        String getemail=i.getStringExtra("email");
        String getphone=i.getStringExtra("phone");
        String getaddr=i.getStringExtra("address");
        n.setText(getname);
        p.setText(getphone);
        e.setText(getemail);
        a.setText(getaddr);


    }
    private void opencall(String tel){
        Intent call = new Intent(Intent.ACTION_CALL);
        call.setData(Uri.parse("tel:"+tel));
        if(ActivityCompat.checkSelfPermission(Contact_Details.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},11);
        }
        startActivity(call);
    }
    private void openchat(String s){
        Uri uri = Uri.parse("smsto:" + s);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.putExtra(Intent.EXTRA_TEXT,"Hey There");
        startActivity(i);
    }
}
