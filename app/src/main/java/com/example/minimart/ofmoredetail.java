package com.example.minimart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ofmoredetail extends AppCompatActivity {
    Button moremanualcon;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_ofmoredetail );


        Button moremanualcon = findViewById ( R.id.moremanualcon);
        moremanualcon.setOnClickListener ( v -> {
            Intent intent = new Intent ( this , ofmoredatilsmanual.class );
            startActivity ( intent );
        } );
    }
}