package com.mbds.nfcapplicationsmartphone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ReglageActivity extends AppCompatActivity {
    ImageView imgEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglage);
        imgEdit=(ImageView)findViewById(R.id.modifProfil);
        imgEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReglageActivity.this, ProfileActivity.class);
                startActivityForResult(intent, 1);

            }
        });
    }
}
