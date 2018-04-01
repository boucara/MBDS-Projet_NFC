package com.mbds.nfcapplicationsmartphone;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class AppareillageActivity extends AppCompatActivity {

    public static final String FRAGTAG = "BeamLargeFilesFragment";
    TextView sample_output;
    private int lum;
    private int temp;
    private int inclinaison;
    private String cMusique;
    private String cVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appareillage);
        sample_output = (TextView) findViewById(R.id.sample_output);
        sample_output.setText(Html.fromHtml(getString(R.string.intro_message)));

        Intent intent = getIntent();
        lum = intent.getExtras().getInt("lum");
        temp = intent.getExtras().getInt("temp");
        inclinaison = intent.getExtras().getInt("inclinaison");
        cMusique = intent.getExtras().getString("cMusique");
        cVideo = intent.getExtras().getString("cVideo");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        BeamLargeFilesFragment fragment = new BeamLargeFilesFragment();
        transaction.add(fragment, FRAGTAG);
        transaction.commit();
    }
}
