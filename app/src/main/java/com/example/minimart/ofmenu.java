package com.example.minimart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ofmenu extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_ofmenu );

        Button calbt = findViewById (R.id.calbt );
        calbt.setOnClickListener ( v -> {
            Intent intent = new Intent (this,ofcal.class);
            startActivity ( intent );
        } );

        Button loyalbt = findViewById (R.id.loyalbt );
        loyalbt.setOnClickListener ( v -> {
            Intent intent = new Intent (this,ofloyalty.class);
            startActivity ( intent );
        } );

        Button editebt = findViewById (R.id.editebt);
        editebt.setOnClickListener ( v -> {
            Intent intent = new Intent (this,ofeditpro.class);
            startActivity ( intent );
        } );

        Button locatbt = findViewById (R.id.locatbt );
        locatbt.setOnClickListener ( v -> {
            Intent intent = new Intent (this,oflocation.class);
            startActivity ( intent );
        } );

        Button moredbt = findViewById (R.id.moredbt );
        moredbt.setOnClickListener ( v -> {
            Intent intent = new Intent (this, QRscanner.class);
            startActivity ( intent );
        } );


    }
}