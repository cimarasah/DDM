package com.example.minhasnotas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    NotasController notaCtrol;
    EditText txtNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notaCtrol = new NotasController(this);
        txtNota = findViewById(R.id.txtNota);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(notaCtrol.Salvar(txtNota.getText().toString())){
            Log.i("resultado", txtNota.getText().toString());
            Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtNota.setText(notaCtrol.Recupera());
    }
}
