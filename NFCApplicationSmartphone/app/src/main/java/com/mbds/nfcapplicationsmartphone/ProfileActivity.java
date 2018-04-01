package com.mbds.nfcapplicationsmartphone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {
    EditText nom,prenom,dns,email,mdp,taille,poids,ville,cp,nv,tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nom=(EditText)findViewById(R.id.EditTextNom);
        prenom=(EditText)findViewById(R.id.EditTextPrenom);
        dns=(EditText)findViewById(R.id.EditTextDns);

        mdp=(EditText)findViewById(R.id.EditTextMdp);
        taille=(EditText)findViewById(R.id.EditTextTaille);
        poids=(EditText)findViewById(R.id.EditTextPoids);
        ville=(EditText)findViewById(R.id.EditTextVille);
        cp=(EditText)findViewById(R.id.EditTextCP);
        nv=(EditText)findViewById(R.id.EditTextNV);
        tv=(EditText)findViewById(R.id.EditTextTV);
        email=(EditText)findViewById(R.id.EditTextMail);

        Intent intent = getIntent();


        nom.setText(intent.getStringExtra("nom"));
        prenom.setText(intent.getStringExtra("prenom"));
        dns.setText(intent.getStringExtra("dns"));
        mdp.setText(intent.getStringExtra("mdp"));
        taille.setText(""+intent.getIntExtra("taille",0));
        poids.setText(""+intent.getIntExtra("poids",0));
        ville.setText(intent.getStringExtra("ville"));
        cp.setText(""+intent.getIntExtra("cp",0));
        nv.setText(""+intent.getIntExtra("nv",0));
        tv.setText(intent.getStringExtra("tv"));
       // email.setText(intent.getStringExtra("email"));
    }
}
