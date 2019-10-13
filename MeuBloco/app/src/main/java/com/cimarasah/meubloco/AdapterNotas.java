package com.cimarasah.meubloco;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterNotas extends BaseAdapter{

    private Context context;
    private final List<Nota> notas;

    byte[] imgArray;

    public AdapterNotas(Context context, List<Nota> notas) {
        this.context = context;
        this.notas = notas;
    }

    Bitmap raw;



    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Nota getItem(int position) {
        return notas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return notas.get(position).getId();
    }

    public void removeNota(int position) {
        this.notas.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(context, R.layout.nota, null);
        Nota nota = this.getItem(position);

        //pegando as referências das Views
        TextView nome = (TextView) view.findViewById(R.id.tit_list_nota);
        TextView descricao = (TextView) view.findViewById(R.id.desc_list_nota);
        ImageView imagem = (ImageView) view.findViewById(R.id.img_list_nota);


        nome.setText(nota.getTitulo());
        descricao.setText(nota.getDescricao());
        imgArray = nota.getImg();

        if(imgArray!=null){
            raw  = BitmapFactory.decodeByteArray(imgArray,0,imgArray.length);
            imagem.setImageBitmap(raw);
        }

        return view;
    }
}
