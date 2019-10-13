package Conexao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cimarasah.meubloco.Nota;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotaDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;
     private  String Tb_Nota = "nota";

    public NotaDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();

    }

    public long InserirNota(Nota nota){
        ContentValues values = new ContentValues();
        values.put("titulo", nota.getTitulo());
        values.put("descricao", nota.getDescricao());
        values.put("img", nota.getImg());
        values.put("data_criacao", String.valueOf(new Date()));
        return banco.insert(Tb_Nota, "img", values );
    }
    public List<Nota> BuscarNotas(){

      ArrayList<Nota> list = new ArrayList<Nota>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Tb_Nota;

        SQLiteDatabase db = this.conexao.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Nota obj = new Nota();
                        //only one column
                        obj.setId(cursor.getInt(0));
                        obj.setTitulo(cursor.getString(1));
                        obj.setDescricao(cursor.getString(2));
                        obj.setImg(cursor.getBlob(3));
                        obj.setDataCriacao(new Date(cursor.getLong(4)*1000));
                        //you could add additional columns here..

                        list.add(obj);
                    } while (cursor.moveToNext());
                }

            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }

        } finally {
            try { db.close(); } catch (Exception ignore) {}
        }

        return list;
    }
}
