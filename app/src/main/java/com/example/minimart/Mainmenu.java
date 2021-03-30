package com.example.minimart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class Mainmenu extends AppCompatActivity {
    FloatingActionButton ofmenu, onmenu;
    FirebaseAuth Fauth;
    TextView semail;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_mainmenu );

        FloatingActionButton ofmenu = findViewById ( R.id.ofmenu );
        ofmenu.setOnClickListener ( v -> {
            Intent intent = new Intent ( this , ofmenu.class );
            startActivity ( intent );
        } );

        FloatingActionButton onmenu = findViewById ( R.id.onmenu );
        onmenu.setOnClickListener ( v -> {
            Intent intent = new Intent ( this , Onlinewel.class );
            startActivity ( intent );
            semail = findViewById ( R.id.semail );
            Fauth = getInstance ( );
        } );
    }

    @Override
    protected void onStart ( ) {

        super.onStart ( );

        FirebaseUser user = Fauth.getInstance ( ).getCurrentUser ( );
        if (user != null) {
            String email = user.getEmail ( );
        } else {
            startActivity ( new Intent ( getApplicationContext ( ) , logp.class ) );
            finish ( );
        }
    }

    public void logout ( View view ) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder ( Mainmenu.this );
            builder.setTitle ( "Are you sure?" );
            builder.setMessage ( " Please Confirm before you logout" );
            builder.setPositiveButton ( "Logout" , new DialogInterface.OnClickListener ( ) {
                @Override
                public void onClick ( DialogInterface dialog , int which ) {
                    FirebaseAuth.getInstance().signOut();
                    finish ( );
                    Toast.makeText ( Mainmenu.this , "You are logging out successfully" , Toast.LENGTH_SHORT ).show ( );
                }
            } );
            builder.setNegativeButton ( "Cancel" , new DialogInterface.OnClickListener ( ) {
                @Override
                public void onClick ( DialogInterface dialog , int which ) {
                    Toast.makeText ( Mainmenu.this , "Oopss" , Toast.LENGTH_SHORT ).show ( );
                }
            } );
            AlertDialog alertDialog = builder.create ( );
            alertDialog.show ( );
        } catch (Exception e) {
            Toast.makeText ( this , e.getMessage ( ) , Toast.LENGTH_SHORT ).show ( );
        }

    }

}
