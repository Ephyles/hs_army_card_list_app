package fr.ephyles.heroscapearmycardlist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class CardDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "cards.db";
    private static final int DATABASE_VERSION = 1;

    public CardDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getArmyCards() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //String [] sqlSelect = {"Name", "General", "Points"};
        String sqlTables = "army_cards";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, null, null, null,
                null, null, "Name");

        c.moveToFirst();
        return c;

    }
}