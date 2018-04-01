package com.mbds.nfcapplicationsmartphone;

/**
 * Created by deptinfo on 01/04/2018.
 */

public class Utilisateur {
    // profile utilisateur
    public int id;
    public String nom;
    public String prenom;
    public String date;
    public String email;
    public String mdp;
    public String sexe;
    public int taille;
    public int poids;
    public String  ville;
    public int cp;
    public int nv;
    public  String tv;

    //preferences utilisateur
    public  int temp;
    public int incl;
    public int  lm;
    public  String cm;
    public String cv;

    public Utilisateur(int id, String nom,String prenom ,String date, String email, String mdp,String sexe,int taille,int poids,String ville,
                       int cp, int nv ,String tv){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.date=date;
        this.email=email;
        this.mdp=mdp;
        this.sexe=sexe;
        this.taille=taille;
        this.poids=poids;
        this.ville=ville;
        this.cp=cp;
        this.nv=nv;
        this.tv=tv;

    }
    public Utilisateur(int id,int temp, int incl, int lm, String cm,String cv){
        this.id=id;
        this.temp=temp;
        this.incl=incl;
        this.lm=lm;
        this.cm=cm;
        this.cv=cv;
    }
    public Utilisateur(int id, String nom,String prenom ,String date, String email, String mdp,String sexe,int taille,int poids,String ville,
                       int cp, int nv ,String tv,int temp, int incl, int lm, String cm,String cv){

        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.date=date;
        this.email=email;
        this.mdp=mdp;
        this.sexe=sexe;
        this.taille=taille;
        this.poids=poids;
        this.ville=ville;
        this.cp=cp;
        this.nv=nv;
        this.tv=tv;


        this.temp=temp;
        this.incl=incl;
        this.lm=lm;
        this.cm=cm;
        this.cv=cv;


    }
    public Utilisateur( String nom,String prenom ,String date, String email, String mdp,String sexe,int taille,int poids,String ville,
                       int cp, String tv ,int nv,int temp, int incl, int lm, String cm,String cv){
        this.nom=nom;
        this.prenom=prenom;
        this.date=date;
        this.email=email;
        this.mdp=mdp;
        this.sexe=sexe;
        this.taille=taille;
        this.poids=poids;
        this.ville=ville;
        this.cp=cp;
        this.nv=nv;
        this.tv=tv;


        this.temp=temp;
        this.incl=incl;
        this.lm=lm;
        this.cm=cm;
        this.cv=cv;


    }
}
