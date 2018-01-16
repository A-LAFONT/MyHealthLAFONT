package com.example.antoinelafont.myhealthlafont.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.antoinelafont.myhealthlafont.Models.Personne;
import com.example.antoinelafont.myhealthlafont.Helpers.DBInfos.PersonneDB;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoine LAFONT on 17/11/2017.
 */

public class DAO {

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    public DAO(Context context) {
        dbHelper = new MyDbHelper(context, "MyHealthLAFONT", null, 1);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public SQLiteDatabase getBDD() {
        return database;
    }

    //Ecrire toutes les méthodes permettant d'insérer/supprimer/modifier les données de la base
    public long insertBook(Personne p){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(PersonneDB.ID, p.id);
        values.put(PersonneDB.LASTNAME, p.nom);
        values.put(PersonneDB.NAME, p.prenom);
        values.put(PersonneDB.AGE, p.age);
        values.put(PersonneDB.WEIGHT, p.poids);
        values.put(PersonneDB.DATE_MAJ, p.dateMaj.getTime());
        values.put(PersonneDB.LOGIN, p.login);
        //on insère l'objet dans la BDD via le ContentValues
        return database.insert(PersonneDB.TABLE_NAME, null, values);
    }

    public int updatePersonne(int id, Personne p){
        //La mise à jour d'une personne dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quelle personne on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(PersonneDB.ID, p.id);
        values.put(PersonneDB.LASTNAME, p.nom);
        values.put(PersonneDB.NAME, p.prenom);
        values.put(PersonneDB.AGE, p.age);
        values.put(PersonneDB.WEIGHT, p.poids);
        values.put(PersonneDB.DATE_MAJ, p.dateMaj.getTime());
        values.put(PersonneDB.LOGIN, p.login);
        return database.update(PersonneDB.TABLE_NAME, values, PersonneDB.ID + " = " + id, null);
    }

    public int removePersonneWithID(int id){
        //Suppression d'une personne de la BDD grâce à l'ID
        return database.delete(PersonneDB.TABLE_NAME, PersonneDB.ID + " = " + id, null);
    }

    public List<Personne> getAllPersonnes() {
        List<Personne> personnes = new ArrayList<Personne>();

        Cursor cursor = database.query(PersonneDB.TABLE_NAME, dbHelper.TABLE_PERSON_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Personne book = cursorToPersonne(cursor);
            personnes.add(book);
            cursor.moveToNext();
        }
        // Fermeture du curseur
        cursor.close();
        return personnes;
    }

    private Personne cursorToPersonne(Cursor cursor) {
        Personne p = new Personne();

        p.id = cursor.getInt(0);
        p.nom = cursor.getString(1);
        p.prenom = cursor.getString(2);
        p.age = cursor.getInt(3);
        p.poids = cursor.getInt(4);
        p.dateMaj = new Date(cursor.getLong(5));
        p.login = cursor.getString(6);

        return p;
    }
}