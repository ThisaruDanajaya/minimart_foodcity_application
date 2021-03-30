package com.example.minimart;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class oflocation extends AppCompatActivity {

    ArrayList<String> myArrayList = new ArrayList<>();
    ListView listView;
    DatabaseReference ref;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_oflocation );

        listView = (ListView)findViewById(R.id.ListView);

        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(oflocation.this, android.R.layout.simple_dropdown_item_1line,myArrayList);
        listView.setAdapter(adapter);
        ref= FirebaseDatabase.getInstance().getReference();
        ref.addChildEventListener(new ChildEventListener () {
            @Override
            public  void onChildAdded( DataSnapshot datasnapshot, String s) {
                myArrayList.add(datasnapshot.getValue(String.class));
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged( DataSnapshot dataSnapshot,  String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot datasnapshot) {
                myArrayList.remove(datasnapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved( @NonNull DataSnapshot datasnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseerror) {

            }
        });




    }
}