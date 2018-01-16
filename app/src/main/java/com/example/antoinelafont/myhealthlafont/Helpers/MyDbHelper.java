package com.example.antoinelafont.myhealthlafont.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.antoinelafont.myhealthlafont.Helpers.DBInfos.PersonneDB;

import java.util.Date;

/**
 * Created by Antoine LAFONT on 17/11/2017.
 */

public class MyDbHelper extends SQLiteOpenHelper {

    public final String[] TABLE_PERSON_COLUMNS = new String[] {
            PersonneDB.ID, PersonneDB.NAME, PersonneDB.LASTNAME, PersonneDB.NAME, PersonneDB.AGE, PersonneDB.WEIGHT, PersonneDB.DATE_MAJ, PersonneDB.LOGIN
    };

    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PersonneDB.TABLE_NAME + " (" +
                PersonneDB.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , " +
                PersonneDB.LASTNAME + " VARCHAR NOT NULL , " +
                PersonneDB.NAME + " VARCHAR NOT NULL , " +
                PersonneDB.AGE + " INTEGER NOT NULL, " +
                PersonneDB.WEIGHT + " INTEGER, " +
                PersonneDB.DATE_MAJ + " REAL, " +
                PersonneDB.LOGIN + " VARCHAR); ");
        insertExampleValues(db);
    }

    public void insertExampleValues(SQLiteDatabase db) {
        long currentTime = new Date().getTime();
        //AJout des livres
        db.execSQL("INSERT INTO " + PersonneDB.TABLE_NAME + " VALUES(1,'Meiller','Kévin',34,120," + currentTime + ",'Kéké'); ");
        db.execSQL("INSERT INTO " + PersonneDB.TABLE_NAME + " VALUES(2,'Lafont','Antoine',24,75," + currentTime + ",'Mamène'); ");
        db.execSQL("INSERT INTO " + PersonneDB.TABLE_NAME + " VALUES(3,'Daclin','Vincent',22,75," + currentTime + ",'Jura'); ");
        db.execSQL("INSERT INTO " + PersonneDB.TABLE_NAME + " VALUES(4,'Bidault','Guillaume',72,33," + currentTime + ",'StringJohanne'); ");
        db.execSQL("INSERT INTO " + PersonneDB.TABLE_NAME + " VALUES(5,'Dereims','Léonard',24,93," + currentTime + ",'Zidane'); ");
        db.execSQL("INSERT INTO " + PersonneDB.TABLE_NAME + " VALUES(6,'Walter','Maxime',23,65," + currentTime + ",'MisterWalt'); ");
        db.execSQL("INSERT INTO " + PersonneDB.TABLE_NAME + " VALUES(7,'Barthélémy','Maxime',23,90," + currentTime + ",'Wasmax'); ");
        db.execSQL("INSERT INTO " + PersonneDB.TABLE_NAME + " VALUES(8,'Cloup','Valentin',23,76," + currentTime + ",'Jimmy'); ");
        db.execSQL("INSERT INTO " + PersonneDB.TABLE_NAME + " VALUES(9,'Kiene','Benjamin',22,60," + currentTime + ",'Benny'); ");
        db.execSQL("INSERT INTO " + PersonneDB.TABLE_NAME + " VALUES(10,'Manca','Ruslan',22,75," + currentTime + ",'Exelion'); ");
        db.execSQL("INSERT INTO " + PersonneDB.TABLE_NAME + " VALUES(11,'Fernandez','Thomas',22,67," + currentTime + ",'Trouelle'); ");
        db.execSQL("INSERT INTO " + PersonneDB.TABLE_NAME + " VALUES(12,'Revenu','Simon',22,78," + currentTime + ",'KFC'); ");
        db.execSQL("INSERT INTO " + PersonneDB.TABLE_NAME + " VALUES(13,'Potherat','Léonard',31,92," + currentTime + ",'DjLeop'); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + PersonneDB.TABLE_NAME + ";");
        onCreate(db);
    }
}