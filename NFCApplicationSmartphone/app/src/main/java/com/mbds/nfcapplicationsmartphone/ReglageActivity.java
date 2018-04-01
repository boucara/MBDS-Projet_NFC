package com.mbds.nfcapplicationsmartphone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ReglageActivity extends AppCompatActivity {
    ImageView imgEdit;
    TextView nomText ;
    String nom,prenom,dns,mdp,ville,tv ,email;
    int taille,nv,poids,cp,id;
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
    }
}
