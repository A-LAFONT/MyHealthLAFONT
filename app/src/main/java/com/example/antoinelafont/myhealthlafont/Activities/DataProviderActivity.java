package com.example.antoinelafont.myhealthlafont.Activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.antoinelafont.myhealthlafont.Helpers.AccessDataProvider;
import com.example.antoinelafont.myhealthlafont.Helpers.DAO;
import com.example.antoinelafont.myhealthlafont.Helpers.DBInfos.PersonneDB;
import com.example.antoinelafont.myhealthlafont.Models.Personne;
import com.example.antoinelafont.myhealthlafont.R;

import java.util.Date;
import java.util.List;

/**
 * Created by Antoine LAFONT on 17/11/2017.
 */

public class DataProviderActivity extends AppCompatActivity {
    private DAO datasource;

    public DataProviderActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Charger les données depuis la base de données
        datasource = new DAO(this);
        datasource.open();

        //Afficher les données sur la page d'accueil
        List<Personne> values = datasource.getAllPersonnes();

        insertRecords();
        displayContentProvider();
    }

    private void displayContentProvider() {
        String columns[] = new String[] {
                PersonneDB.ID,
                PersonneDB.LASTNAME,
                PersonneDB.NAME,
                PersonneDB.AGE,
                PersonneDB.WEIGHT,
                PersonneDB.DATE_MAJ,
                PersonneDB.LOGIN
        };
        Uri mContacts = Uri.parse(AccessDataProvider.URL + "/Personnes");
        Cursor cur = getContentResolver().query(mContacts, columns, null, null, null);
        Toast.makeText(DataProviderActivity.this, cur.getCount() + "",
                Toast.LENGTH_LONG).show();

        if (cur.moveToFirst()) {
            String name = null;
            do {
                name = cur.getString(cur.getColumnIndex(PersonneDB.NAME));
                Toast.makeText(this, name + " ", Toast.LENGTH_LONG).show();
            } while (cur.moveToNext());
        }
    }

    private void insertRecords() {
        ContentValues personne = new ContentValues();
        personne.put(PersonneDB.NAME, "Test");
        personne.put(PersonneDB.LASTNAME, "TEST");
        personne.put(PersonneDB.AGE, 25);
        personne.put(PersonneDB.WEIGHT, 75);
        personne.put(PersonneDB.DATE_MAJ, new Date().getTime());
        personne.put(PersonneDB.LOGIN, "Test");
        getContentResolver().insert(Uri.parse(AccessDataProvider.URL + "/Personnes#"), personne);
    }
}
