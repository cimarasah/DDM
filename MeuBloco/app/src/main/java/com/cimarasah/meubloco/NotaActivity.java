package com.cimarasah.meubloco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Conexao.NotaDAO;

public class NotaActivity extends AppCompatActivity {

    EditText titText;
    EditText descText;
    Button btnCriar;
    ListView listView;

    private List<Nota> notas = new ArrayList<>();
    AdapterNotas adapter = null;
    private NotaDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);



        titText = (EditText) findViewById(R.id.titText);
        descText = (EditText) findViewById(R.id.descText);
        btnCriar = (Button) findViewById(R. id.btnSalvar);
        listView = (ListView) findViewById(R.id.listView);
        dao = new NotaDAO(this);

       // adapter = new ArrayAdapter<Nota>(this, android.R.layout.simple_list_item_1, notas);
        adapter = new AdapterNotas(NotaActivity.this, notas);
        listView.setAdapter(adapter);


      /*  btnCriar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                String nota = ntText.getText().toString();

                if(!nota.isEmpty()){

                    ntText.setText("");
                    ntText.findFocus();
                    notas.add(nota);
                    adapter.notifyDataSetChanged();
                }
            }
        });*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(NotaActivity.this);
                adb.setTitle("Excluir Nota");
                adb.setMessage("Deseja excluir essa nota?");
                final int positionToRemote = position;
                adb.setNegativeButton("Não", null);
                adb.setPositiveButton("Sim", new AlertDialog.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        notas.remove(positionToRemote);
                        adapter.notifyDataSetChanged();
                    }
                });
                adb.show();
            }
        });

    }
    public void novaNota(View view){

        Intent intent = new Intent(this, NovaNotaActivity.class);
        startActivity(intent);

    }

}
