package br.unifor.euresolvo.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import br.unifor.euresolvo.Bean.UserBeanOLD;

/**
 * Created by SamuelSantiago on 28/08/2017.
 */

public class UserDao extends SQLiteOpenHelper {

    public static final int VERSAO = 1;
    public static final String TABELA = "user";
    public static final String DATABASE = "EURESOLVO.DB";

    public UserDao(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public UserDao(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
    public UserDao(Context context){
        super(context, DATABASE, null, VERSAO);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABELA
                + "(id INTEGER PRIMARY KEY, "
                + "personName TEXT, "
                + "personEmail TEXT, "
                + "personId TEXT, "
                + "personPhoto TEXT, "
                + "personType INTEGER)";
        db.execSQL(sql);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    public void salve(UserBeanOLD userBeanOLD){
        reset();
        ContentValues valores = new ContentValues();

        valores.put("personName", userBeanOLD.getPersonName());
        valores.put("personEmail", userBeanOLD.getPersonEmail());
        valores.put("personId", userBeanOLD.getPersonId());
        valores.put("personPhoto", userBeanOLD.getPersonPhoto().toString());
        valores.put("personType", userBeanOLD.getPersonType());

        getWritableDatabase().insert(TABELA, null, valores);
    }

    public boolean isEmpy(){
        String sql = "Select * from user";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        if(cursor.getCount() == 0){
            return true;
        }else {
            return false;
        }
    }

    public UserBeanOLD consult(){
        UserBeanOLD userBeanOLD = new UserBeanOLD();
        String sql = "Select * from user";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        try {
            while (cursor.moveToNext()) {

                userBeanOLD.setId(cursor.getInt(0));
                userBeanOLD.setPersonName(cursor.getString(1));
                userBeanOLD.setPersonEmail(cursor.getString(2));
                userBeanOLD.setPersonId(cursor.getString(3));
                userBeanOLD.setPersonPhoto(Uri.parse(cursor.getString(4)));
                userBeanOLD.setPersonType(cursor.getInt(5));

            }
        } catch (android.database.SQLException sqle) {
        } finally {
            cursor.close();
        }

        return userBeanOLD;
    }

    public void reset() {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        onCreate(db);
        db.close();
    }

    public void pop(){
        reset();
        salve(new UserBeanOLD("Samuel Santiago","sss.samuel@gmail.com","105715529606084433089", null));
    }

}