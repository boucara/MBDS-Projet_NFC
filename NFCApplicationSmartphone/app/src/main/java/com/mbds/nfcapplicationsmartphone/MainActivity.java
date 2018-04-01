package com.mbds.nfcapplicationsmartphone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.ems;
import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {
Button btnEnvoie;
    EditText nom,prenom,dns,email,mdp,taille,poids,ville,cp,nv,tv;
    RadioGroup radioSexeGroup;
    RadioButton radioSexeButton;
    final Context context=this;
    MonHelper  dataBase = new MonHelper(this);
    String sexe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnEnvoie= (Button) findViewById(R.id.envoi);
        nom=(EditText)findViewById(R.id.EditTextNom);
        prenom=(EditText)findViewById(R.id.EditTextPrenom);
        dns=(EditText)findViewById(R.id.EditTextDns);
        email=(EditText)findViewById(R.id.EditTextEmail);
        mdp=(EditText)findViewById(R.id.EditTextMdp);
        radioSexeGroup = (RadioGroup) findViewById(R.id.radioSexe);
        taille=(EditText)findViewById(R.id.EditTextTaille);
        poids=(EditText)findViewById(R.id.EditTextPoids);
        ville=(EditText)findViewById(R.id.EditTextVille);
        cp=(EditText)findViewById(R.id.EditTextCP);
        nv=(EditText)findViewById(R.id.EditTextNV);
        tv=(EditText)findViewById(R.id.EditTextTV);

        btnEnvoie.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int selectedId = radioSexeGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioSexeButton = (RadioButton) findViewById(selectedId);
               sexe=radioSexeButton.getText().toString();
             if(nom.getText().toString().matches(" ")|| prenom.getText().toString().matches(" ") || dns.getText().toString().matches(" ")
                     || email.getText().toString().matches(" ") || mdp.getText().toString().matches(" ")) {
                 Toast.makeText(context, "vous devez renseigner les champs avec *!", Toast.LENGTH_LONG).show();
                 return;

             }
               else if(nom.getText().toString().equals("\t")|| prenom.getText().toString().equals("\t") || dns.getText().toString().equals("\t")
                        || email.getText().toString().equals("\t") || mdp.getText().toString().equals("\t")) {
                    Toast.makeText(context, "vous devez renseigner les champs avec *!", Toast.LENGTH_LONG).show();
                    return;

                }

                else{
                Utilisateur u= new Utilisateur(nom.getText().toString(),prenom.getText().toString(),dns.getText().toString(),
                        email.getText().toString(),mdp.getText().toString(),sexe,Integer.parseInt(taille.getText().toString()),
                        Integer.parseInt(poids.getText().toString()),ville.getText().toString(),Integer.parseInt(cp.getText().toString()),
                       tv.getText().toString(), Integer.parseInt(nv.getText().toString()),0,0,0," "," ");
                    u.id=dataBase.addUtilisateur(u);
                    if(!loadUserInfoFromDatabase().isEmpty()){
                        Toast.makeText(context, "Inscription réussit sur ISIT!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(context, "erreur survenu à l'incription sur ISIT!", Toast.LENGTH_LONG).show();
                    }


             }

            }
        });

    }
    private ArrayList<Utilisateur> loadUserInfoFromDatabase()
    {
        ArrayList<Utilisateur> u = this.dataBase.getAllUserInfo();
        return u ;
    }
}
