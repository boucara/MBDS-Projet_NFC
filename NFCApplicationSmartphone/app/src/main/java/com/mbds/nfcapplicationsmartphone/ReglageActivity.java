package com.mbds.nfcapplicationsmartphone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ReglageActivity extends AppCompatActivity {
    ImageView imgEdit;
    TextView nomText ;
    String nom,prenom,dns,mdp,ville,tv ,email;
    int taille,nv,poids,cp,id;
    Button modifier ,app;
    SeekBar seekTemp , seekIncl ,seekLm;
    EditText cm ,cv;
    int temp , incl ,lum;
    MonHelper  dataBase = new MonHelper(this);
    final Context context=this;
    Utilisateur u ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglage);
        nomText=(TextView)findViewById(R.id.nom);


        Intent intent = getIntent();
        nomText.setText(intent.getStringExtra("nom"));
        id=intent.getIntExtra("id",0);
        nom=intent.getStringExtra("nom");
        prenom=intent.getStringExtra("prenom");
        dns=intent.getStringExtra("dns");
        mdp=intent.getStringExtra("mdp");
        taille=intent.getIntExtra("taille",0);
        poids=intent.getIntExtra("poids",0);
        ville=intent.getStringExtra("ville");
        cp=intent.getIntExtra("cp",0);
        nv=intent.getIntExtra("nv",0);
        tv=intent.getStringExtra("tv");
        email=intent.getStringExtra("email");

        imgEdit=(ImageView)findViewById(R.id.modifProfil);
        imgEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReglageActivity.this, ProfileActivity.class);
                intent.putExtra("nom", nom);
                intent.putExtra("prenom", prenom);
                intent.putExtra("dns", dns);
                intent.putExtra("mdp", mdp);
                intent.putExtra("taille",taille);
                intent.putExtra("poids", poids);
                intent.putExtra("ville", ville);
                intent.putExtra("cp", cp);
                intent.putExtra("nv", nv);
                intent.putExtra("tv", tv);
                intent.putExtra("email", email);
                intent.putExtra("id", id);
                startActivityForResult(intent, 1);

            }
        });
        cm=(EditText)findViewById(R.id.EditMusic) ;
        cv=(EditText)findViewById(R.id.EditTextTV) ;

        seekTemp=(SeekBar)findViewById(R.id.seekTemp);
        seekTemp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                temp = progressChangedValue;
                Toast.makeText(ReglageActivity.this, "temperature  :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });
        seekIncl=(SeekBar)findViewById(R.id.seekIncli);
        seekIncl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                incl=progressChangedValue;
                Toast.makeText(ReglageActivity.this, "inclinaison:" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });
        seekLm=(SeekBar)findViewById(R.id.seekLumino);
        seekLm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                lum=progressChangedValue;
                Toast.makeText(ReglageActivity.this, "Luminosité :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });


        modifier=(Button)findViewById(R.id.modifi);
        modifier.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              u = new Utilisateur(id, temp, incl, lum,cm.getText().toString(), cv.getText().toString());
                dataBase.updatePreferences(u);
                Toast.makeText(context, "Préferences bien enregistrées sur ISIT!", Toast.LENGTH_LONG).show();

            }});
        app=(Button)findViewById(R.id.app);
        app.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReglageActivity.this, AppareillageActivity.class);
                intent.putExtra("lum", lum);
                intent.putExtra("temp", temp);
                intent.putExtra("inclinaison", incl);
                intent.putExtra("cMusique", cm.getText().toString());
                intent.putExtra("cVideo",cv.getText().toString());
                startActivityForResult(intent, 1);

            }});

    }
}
