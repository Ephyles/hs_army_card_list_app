package fr.ephyles.heroscapearmycardlist

import android.content.Context
import android.database.Cursor
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import fr.ephyles.heroscapearmycardlist.CardDatabase
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder

class CardDatabase(context: Context?) :
    SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    fun createList(callback: () -> Unit) {
        val db = readableDatabase
        val qb = SQLiteQueryBuilder()

        val sqlTables = "army_cards"
        qb.tables = sqlTables
        val c = qb.query(
            db, null, null, null,
            null, null, "Name"
        )
        c.moveToFirst()

        cardList.clear()

        with(c) {
            while (moveToNext()) {
                var newCard = CardModel(
                    getString(0),
                    getString(15),
                    getInt(7),
                    getString(1),
                    getInt(2),
                    getInt(3),
                    getInt(4),
                    getInt(5),
                    getInt(6),
                    getString(8) + " " + getInt(9).toString(),
                    getString(10),
                    getString(11),
                    getString(12),
                    getString(13) + " " + getString(14),
                    getInt(16),
                    getInt(17),
                    getString(18) + "\n\n" + getString(19) + "\n\n" +
                            getString(20) + "\n\n" + getString(21) + "\n\n" +
                            getString(22) + "\n\n" + getString(23) + "\n\n" +
                            getString(24) + "\n\n" + getString(25) + "\n\n",
                    getString(26),
                    0
                )
                cardList.add(newCard)
            }
        }

        callback()
    }

    companion object {
        private const val DATABASE_NAME = "cards.db"
        private const val DATABASE_VERSION = 1
        val cardList = arrayListOf<CardModel>()
    }
}