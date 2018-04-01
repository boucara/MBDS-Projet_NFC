package com.mbds.nfcapplicationsmartphone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by deptinfo on 01/04/2018.
 */

public class MonHelper  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME ="isit";
    private static final String TABLE_Utilisateur = "utilisateur";

    // profile utilisateur
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOM = "nom";
    private static final String COLUMN_PRENOM = "prenom";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_MAIL = "email";
    private static final String COLUMN_MDP = "mdp";
    private static final String COLUMN_SEXE="sexe";
    private static final String COLUMN_TAILLE="taille";
    private static final String COLUMN_POIDS="poids";
    private static final String COLUMN_VILLE="ville";
    private static final String COLUMN_CP="cp";
    private static final String COLUMN_NV="nv";
    private static final String COLUMN_TV="tv";

    //preferences utilisateur
    private static final String COLUMN_TEMP="temp";
    private static final String COLUMN_INCL="incl";
    private static final String COLUMN_LM="lm";
    private static final String COLUMN_CM="cm";
    private static final String COLUMN_CV="CV";



    private static  final String tableUtilisateur="CREATE TABLE "+TABLE_Utilisateur+"("+COLUMN_ID +" "+"INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "  "+COLUMN_NOM +"  "+ "TEXT NOT NULL,"+
            "  "+COLUMN_PRENOM +"  "+"TEXT NOT NULL ,"+
            "  "+COLUMN_DATE +"  "+"TEXT NOT NULL,"+
            "  "+COLUMN_MAIL +"  "+"TEXT NOT NULL,"+
            "  "+COLUMN_MDP +"  "+"TEXT NOT NULL,"+
            "  "+COLUMN_SEXE +"  "+"TEXT NOT NULL,"+
            "  "+COLUMN_TAILLE +"  "+"INTEGER,"+
            "  "+COLUMN_POIDS +"  "+"INTEGER,"+
            "  "+COLUMN_VILLE +"  "+"TEXT,"+
            "  "+COLUMN_CP +"  "+"INTEGER ,"+
            "  "+COLUMN_TV +"  "+"TEXT,"+
            "  "+COLUMN_NV   +"  "+"INTEGER,"+
            "  "+COLUMN_TEMP +"  "+"INTEGER,"+
            "  "+COLUMN_INCL +"  "+"INTEGER,"+
            "  "+COLUMN_LM +"  "+"INTEGER,"+
            "  "+COLUMN_CM +"  "+"TEXT,"+
            "  "+COLUMN_CV  +"  "+"TEXT"+ ")";

    public MonHelper(Context context) {
        super(context,DATABASE_NAME ,null,DATABASE_VERSION );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableUtilisateur);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlUser = "DROP TABLE IF EXISTS " + TABLE_Utilisateur+ ";";
        db.execSQL(sqlUser);
        onCreate(db);
    }

    public int addUtilisateur(int id, String nom,String prenom ,String date, String email, String mdp,String sexe,int taille,int poids,String ville,
                           int cp, int nv ,String tv,int temp, int incl, int lm, String cm,String cv){
        ContentValues content = new ContentValues();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOM, nom);
        contentValues.put(COLUMN_PRENOM, prenom);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_MAIL, email);
        contentValues.put(COLUMN_MDP, mdp);
        contentValues.put(COLUMN_SEXE, sexe);
        contentValues.put(COLUMN_TAILLE, taille);
        contentValues.put(COLUMN_POIDS, poids);
        contentValues.put(COLUMN_VILLE, ville);
        contentValues.put(COLUMN_CP, cp);
        contentValues.put(COLUMN_NV, nv);
        contentValues.put(COLUMN_TV, tv);

        contentValues.put(COLUMN_TEMP, temp);
        contentValues.put(COLUMN_INCL, incl);
        contentValues.put(COLUMN_LM, lm);
        contentValues.put(COLUMN_CM, cm);
        contentValues.put(COLUMN_CV, cv);

        SQLiteDatabase db = getWritableDatabase();
        return (int)db.insert(TABLE_Utilisateur, null, contentValues) ;

    }
    public ArrayList<Utilisateur> getAllUserInfo() {
        ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor user = db.rawQuery("SELECT * FROM" +"  "+ TABLE_Utilisateur, null);

        if (user.moveToFirst())
        {
            do {
                Utilisateur u = new Utilisateur (user.getInt(0),user.getString(1),user.getString(2),user.getString(3),user.getString(4),
                        user.getString(5),user.getString(6), user.getInt(7),user.getInt(8),user.getString(9),user.getInt(10),
                        user.getInt(11),user.getString(12));

                utilisateurs.add(u);

            }while (user.moveToNext());
        }
        user.close();
        db.close();
        return  utilisateurs;


    }
    public ArrayList<Utilisateur> getAllUserPreferences() {
        ArrayList<Utilisateur> preferences = new ArrayList<Utilisateur>();
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor user = db.rawQuery("SELECT * FROM" +"  "+ TABLE_Utilisateur, null);

        if (user.moveToFirst())
        {
            do {
                Utilisateur u = new Utilisateur (user.getInt(0),user.getInt(13),user.getInt(14),user.getInt(15),user.getString(16),user.getString(17));

                preferences.add(u);

            }while (user.moveToNext());
        }
        user.close();
        db.close();
        return  preferences;


    }
    public void updateProfile(Utilisateur u)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOM, u.nom);
        values.put(COLUMN_PRENOM, u.prenom);
        values.put(COLUMN_DATE, u.date);
        values.put(COLUMN_MAIL, u.email);
        values.put(COLUMN_MDP, u.mdp);
        values.put(COLUMN_TAILLE, u.taille);
        values.put(COLUMN_POIDS, u.poids);
        values.put(COLUMN_VILLE, u.ville);
        values.put(COLUMN_CP, u.cp);
        values.put(COLUMN_NV, u.nv);
        values.put(COLUMN_TV, u.tv);

        db.update(DATABASE_NAME, values, COLUMN_ID +" = "+u.id, null);
        db.close();
    }
    public void updatePreferences(Utilisateur u)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEMP, u.temp);
        values.put(COLUMN_INCL, u.incl);
        values.put(COLUMN_LM, u.lm);
        values.put(COLUMN_CM, u.cm);
        values.put(COLUMN_CV, u.cv);

        db.update(DATABASE_NAME, values, COLUMN_ID +" = "+u.id, null);
        db.close();
    }
}
