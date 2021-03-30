package com.example.minimart;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.minimart.HelperClasses.LoyaltyHelp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ofaddloyalcard extends AppCompatActivity {

    EditText name,nic,number;
    TextView forpword;
    FloatingActionButton save;
    ProgressBar spb;

    //Firebase
    FirebaseAuth fAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String cardnumber, signNIC;

    String savename, savenic, savenumber;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_ofaddloyalcard );

        name = findViewById ( R.id.name);
        nic = findViewById ( R.id.nic);
        number = findViewById ( R.id.number);

        save = findViewById ( R.id.save);
        fAuth = FirebaseAuth.getInstance ();
        spb = findViewById ( R.id.spb);



        save.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                savename = name.getText ().toString ().trim ();
                savenic = nic.getText ().toString ().trim ();
                savenumber = number.getText ().toString ().trim ();
                save = findViewById ( R.id.sinemenu);

                fAuth = FirebaseAuth.getInstance ();
                spb = findViewById ( R.id.spb);


                if(TextUtils.isEmpty ( savename  )){
                    name.setError("First Name Is Required.");
                    return;
                }
                if(TextUtils.isEmpty ( savenic  )){
                    nic.setError("First Name Is Required.");
                    return;
                }
                if(TextUtils.isEmpty ( savenumber )){
                    number.setError("First Name Is Required.");
                    return;
                }
                if(savenumber.length ()<5){
                    number.setError("Please use long name");
                    return;
                }

                checkUserNIC();
            }
        } );
    }

        public void checkUserNIC ( ) {
            spb.setVisibility ( View.VISIBLE );
            firebaseDatabase = FirebaseDatabase.getInstance ();
            databaseReference = firebaseDatabase.getReference ("loyalaty");

            //Get unique user
            cardnumber = number.getText ().toString ().trim ();
            signNIC = nic.getText ().toString ().trim ();

            final LoyaltyHelp loyalaty = new LoyaltyHelp ( signNIC, cardnumber );

            databaseReference.addListenerForSingleValueEvent ( new ValueEventListener ( ) {
                @Override
                public void onDataChange ( @NonNull DataSnapshot snapshot ) {
                    if (snapshot.child ( loyalaty.getUserNIC ()).exists ()){
                        Toast.makeText ( ofaddloyalcard.this , "Sorry.. this NIC already Entered " , Toast.LENGTH_SHORT ).show ( );
                        spb.setVisibility ( View.INVISIBLE );
                    } else{
                        ToDatabase();
                    }
                }
                @Override
                public void onCancelled ( @NonNull DatabaseError error ) {
                    Toast.makeText ( ofaddloyalcard.this , "Sorry.. Please try again.." , Toast.LENGTH_SHORT ).show ( );
                    spb.setVisibility ( View.INVISIBLE );
                }
            } );
        }

    private void ToDatabase ( ) {
        spb.setVisibility ( View.VISIBLE );
                    String userID = FirebaseAuth.getInstance ( ).getCurrentUser ( ).getUid ( );
                    databaseReference = FirebaseDatabase.getInstance ( ).getReference ( "loyalaty" ).child ( signNIC.toString ( ) );

                    HashMap<String, String> hashMap1 = new HashMap<> ( );
                    hashMap1.put ( "Card Name" , savename );
                    hashMap1.put ( "NIC" , savenic );
                    hashMap1.put ( "Card Number" , savenumber);

                    firebaseDatabase.getInstance ( ).getReference ( "loyalaty" ).child ( savenic )
                            .setValue ( hashMap1 ).addOnCompleteListener ( new OnCompleteListener<Void> ( ) {
                        @Override
                        public void onComplete ( @NonNull Task<Void> task ) {
                            spb.setVisibility ( View.INVISIBLE );
/*
                    if(){
                            Toast.makeText ( ofaddloyalcard.this , "Your Account is created Successfully" , Toast.LENGTH_SHORT ).show ( );
                            startActivity ( new Intent ( getApplicationContext (),Mainmenu.class  ) );
                        }



                    else {
                          Toast.makeText ( ofaddloyalcard.this , "Error ! " + task.getException ( ).getMessage ( ) , Toast.LENGTH_SHORT ).show ( );
                          Toast.makeText ( ofaddloyalcard.this , "Error ! Your Account is created Unsuccessfully" , Toast.LENGTH_SHORT ).show ( );
                          spb.setVisibility ( View.GONE );
                }
*/
            }
        } );
    }
}