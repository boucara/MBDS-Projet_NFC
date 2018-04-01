package com.mbds.nfcapplicationsmartphone;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ConnectionActivity extends AppCompatActivity {
Button btnInscription , btnLog;
    EditText email,mdp;
    MonHelper  dataBase = new MonHelper(this);
    final Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        email=(EditText)findViewById(R.id.EditTextMail) ;
        mdp=(EditText)findViewById(R.id.EditTextMdp) ;
        btnInscription= (Button) findViewById(R.id.btnIncription);
        btnLog= (Button) findViewById(R.id.boutonLog);

        btnInscription.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnectionActivity.this, MainActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        btnLog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<Utilisateur> u=loadUserInfoFromDatabase();
                int i =0;
                if(u!=null&& !u.isEmpty()){
                    for(Utilisateur user:u){
                        if(user.email.equalsIgnoreCase(email.getText().toString())&&user.mdp.equalsIgnoreCase(mdp.getText().toString())){
                            i=1;
                            Intent intent = new Intent(ConnectionActivity.this,ReglageActivity.class);
                            intent.putExtra("id", user.id);
                            intent.putExtra("nom", user.nom);
                            intent.putExtra("prenom", user.prenom);
                            intent.putExtra("dns", user.date);
                            intent.putExtra("mdp", user.mdp);
                            intent.putExtra("email", user.email);
                            intent.putExtra("taille", user.taille);
                            intent.putExtra("poids", user.poids);
                            intent.putExtra("ville", user.ville);
                            intent.putExtra("cp", user.cp);
                            intent.putExtra("nv", user.nv);
                            intent.putExtra("tv", user.tv);
                            startActivityForResult(intent, 1);

                        }
                    }
                    if(i==0){
                        Toast.makeText(context, "Utilisateur non existant sur ISIT!", Toast.LENGTH_LONG).show();
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
