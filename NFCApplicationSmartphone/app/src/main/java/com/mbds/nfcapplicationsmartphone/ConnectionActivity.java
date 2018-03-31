package com.mbds.nfcapplicationsmartphone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConnectionActivity extends AppCompatActivity {
Button btnInscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        btnInscription= (Button) findViewById(R.id.btnIncription);

        btnInscription.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnectionActivity.this, MainActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }
}
