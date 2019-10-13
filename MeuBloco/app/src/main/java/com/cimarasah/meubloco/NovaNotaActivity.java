package com.cimarasah.meubloco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Conexao.NotaDAO;

public class NovaNotaActivity extends AppCompatActivity {

    EditText titText;
    EditText descText;
    Button btnCriar;

    private List<Nota> notas = new ArrayList<>();
    AdapterNotas adapter = null;
    private NotaDAO dao;
    NotaActivity actNota = new NotaActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_nota);

        titText = (EditText) findViewById(R.id.titText);
        descText = (EditText) findViewById(R.id.descText);
        btnCriar = (Button) findViewById(R. id.btnSalvar);
        dao = new NotaDAO(this);



    }
    public void salvar(View view){


        if(!titText.getText().toString().isEmpty()){
            Nota nota = new Nota();
            nota.setTitulo(titText.getText().toString());
            nota.setDescricao(descText.getText().toString());
            long id = dao.InserirNota(nota);
            titText.setText("");
            descText.setText("");
            titText.findFocus();
            notas.add(nota);
            if (adapter == null) {
                adapter = new AdapterNotas(this, notas);
               // listView.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }



            Toast.makeText(this, "Nota inserida com ID: "+ id,Toast.LENGTH_SHORT);
        }

        Intent intent = new Intent(this, NotaActivity.class);
        startActivity(intent);
    }
}
