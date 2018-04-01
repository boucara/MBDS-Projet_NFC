package com.mbds.nfcapplicationsmartphone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    EditText nom,prenom,dns,email,mdp,taille,poids,ville,cp,nv,tv;
    int id;
    Button modifier;
    MonHelper  dataBase = new MonHelper(this);
    final Context context=this;
    Utilisateur u;
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
        email=(EditText)findViewById(R.id.EditTextEmail);

        Intent intent = getIntent();
        id=intent.getIntExtra("id",0);


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
       email.setText(intent.getStringExtra("email"));



        modifier=(Button)findViewById(R.id.modifi);
        modifier.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(id!=0){
                    u= new Utilisateur(id,nom.getText().toString(),prenom.getText().toString(),dns.getText().toString(),
                            email.getText().toString(),mdp.getText().toString(),Integer.parseInt(taille.getText().toString()),
                            Integer.parseInt(poids.getText().toString()),ville.getText().toString(),Integer.parseInt(cp.getText().toString()),
                            tv.getText().toString(), Integer.parseInt(nv.getText().toString()));
                    dataBase.updateProfile(u);
                    Toast.makeText(context, "Mise à jour bien effectuée!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(context, "Erreur survenue l'ors du mise à jour!", Toast.LENGTH_LONG).show();
                }



            }
        });
    }
}
