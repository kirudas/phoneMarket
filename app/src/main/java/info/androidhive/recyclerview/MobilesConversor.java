package info.androidhive.recyclerview;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MobilesConversor {

    private MobilesSqlLiteHelper helper;

    /**
     * Consructor per defecte
     */
    public MobilesConversor() {

    }

    /**
     * Constructor amb paràmetres
     * @param helper l'ajudant de la BD de Mobiles
     */
    public MobilesConversor(MobilesSqlLiteHelper helper) {
        this.helper = helper;
    }

    /**
     * Desa un nou titular a la taula
     * @param Mobile l'objecte a desar
     * @return l'id del nou titular desat
     */
    public long save(Mobile mobile) {
        long index = -1;
        // s'agafa l'objecte base de dades en mode escriptura
        SQLiteDatabase db = helper.getWritableDatabase();
        // es crea un objecte de diccionari (clau,valor) per indicar els valors a afegir
        ContentValues dades = new ContentValues();

        dades.put("titol", mobile.getTitol());
        dades.put("subtitol", mobile.getSubtitol());
        try {
            index = db.insertOrThrow("Mobiles", null, dades);
            // volem veure en el log el que passa
            Log.i("Mobiles", dades.toString() + " afegit amb codi " + index);
        }
        catch(Exception e) {
            // volem reflectir en ellog que hi ha hagut un error
            Log.e("Mobiles", e.getMessage());
        }
        return index;
    }

    /**
     * Retorna un cursor amb totes les dades de la taula
     * @return
     */
    public Cursor getAll() {
        SQLiteDatabase db = helper.getReadableDatabase();

        return db.query(true, "Mobiles",
                new String[]{"codi","titol","subtitol"},
                null, null, null, null, null, null);
    }

    /**
     * Esborra el Mobile passat per paràmetre
     * @param t el Mobile a esborrar
     * @return la quantitat de Mobiles eliminats
     */
    public boolean remove(Mobile t) {
        // obtenir l'objecte BD en mode esriptura
        SQLiteDatabase db = helper.getWritableDatabase();

        return db.delete("Titulars", "codi=" + t.getCodi(),null ) > 0;
    }
    /**
     * Esborra tots els titulars de la taula
     * @return
     */
    public boolean removeAll() {
        // obtenir l'objecte BD en mode escriptura
        SQLiteDatabase db = helper.getWritableDatabase();

        return db.delete("Mobiles", null, null ) > 0;
    }
}