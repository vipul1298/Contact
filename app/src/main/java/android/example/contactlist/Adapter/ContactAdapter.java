package android.example.contactlist.Adapter;

import android.content.Context;
import android.content.Intent;
import android.example.contactlist.Contact_Details;
import android.example.contactlist.Model.Listdata;
import android.example.contactlist.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyHolder> {
    List<Listdata> name;
    Context context;

    public ContactAdapter(List<Listdata> name, Context context) {
        this.name = name;
        this.context = context;
    }



    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        MyHolder myHolder=new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Listdata data=name.get(i);
       myHolder.textView.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
          TextView textView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.contact);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Listdata listdata = name.get(getAdapterPosition());
                    Intent i=new Intent(context, Contact_Details.class);
                    i.putExtra("id",listdata.getUser_id());
                    i.putExtra("name",listdata.getName());
                    i.putExtra("phone",listdata.getPhone());
                    i.putExtra("email",listdata.getEmail());
                    i.putExtra("address",listdata.getCity());
                    context.startActivity(i);
                }
            });
        }
    }

}
