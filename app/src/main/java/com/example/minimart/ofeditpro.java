package com.example.minimart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class ofeditpro extends AppCompatActivity {

    private EditText compass, unic, phnumber, pemail, laname, faname,pass;
    private FloatingActionButton savemenu;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private FirebaseAuth Fauth;
    private FirebaseUser user;
    private FirebaseFirestore fstore;
    private static final String USER = "user";
    private String currentUserNIC;


    @Override
    protected void onStart ( ) {

        super.onStart ( );

        FirebaseUser user = Fauth.getInstance ( ).getCurrentUser ( );
        if (user != null) {
            String currentUserNIC = user.getUid ( );
        } else {
            startActivity ( new Intent ( getApplicationContext ( ) , logp.class ) );
            finish ( );
        }
    }



    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_ofeditpro );

        Intent intent = getIntent ( );
        String email = intent.getStringExtra ( "email" );

        pass = findViewById ( R.id.pass );
        unic = findViewById ( R.id.nic );
        phnumber = findViewById ( R.id.phnumber );
        pemail = findViewById ( R.id.email );
        laname = findViewById ( R.id.laname );
        faname = findViewById ( R.id.faname );
        savemenu = findViewById ( R.id.savemenu );

        database = FirebaseDatabase.getInstance ( );
        userRef = database.getReference ( USER );




        DatabaseReference databaseReference = FirebaseDatabase.getInstance ( ).getReference ( "Users" );
        Query checkUser = databaseReference.orderByChild ( "NIC" ).equalTo ( currentUserNIC );

        checkUser.addListenerForSingleValueEvent ( new ValueEventListener ( ) {
            @Override
            public void onDataChange ( @NonNull DataSnapshot datasnapshot ) {
                if (datasnapshot.child ( currentUserNIC ).exists ( )) {
                    String firstName = datasnapshot.child ( currentUserNIC ).child ( "Firs Name" ).getValue (String.class);
                    String lastName = datasnapshot.child ( currentUserNIC ).child ( "Last Name" ).getValue (String.class);
                    String email = datasnapshot.child ( currentUserNIC ).child ( "Email" ).getValue (String.class);
                    String phone = datasnapshot.child ( currentUserNIC ).child ( "Phone" ).getValue (String.class);
                    String nic = datasnapshot.child ( currentUserNIC ).child ( "NIC" ).getValue (String.class);

                    try {
                        faname.setText (firstName );
                        laname.setText ( lastName );
                        pemail.setText ( email);
                        phnumber.setText (phone );
                        unic.setText (nic );

                    } catch (Exception e){
                        Toast.makeText ( ofeditpro.this , e.getMessage () , Toast.LENGTH_SHORT ).show ( );
                    }

                }
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError error ) {

            }





            } );




    }


}