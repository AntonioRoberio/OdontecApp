package com.des.odontec.equipe.odontec.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.des.odontec.equipe.odontec.Model.PlacarQuiz;
import com.des.odontec.equipe.odontec.R;

import java.util.ArrayList;



/**
 * Created by Antonio on 14/11/2017.
 */

public class AdapterPlacar extends BaseAdapter {
    private ArrayList<PlacarQuiz> p;
    private LayoutInflater layoutInflater;
    private View layout;


    public AdapterPlacar(Context context, ArrayList<PlacarQuiz> p) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = layoutInflater.inflate(R.layout.placar_jogo, null);
        this.p=p;

    }

    @Override
    public int getCount() {
        return p.size();
    }

    @Override
    public Object getItem(int position) {
        return p.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlacarQuiz placarQuiz=p.get(position);
        if (convertView == null) {
            layout = layoutInflater.inflate(R.layout.placar_jogo, null);
        } else {
            layout = convertView;
        }
        TextView nome=(TextView) layout.findViewById(R.id.nomeJogo);
        TextView pontos=(TextView) layout.findViewById(R.id.pontosJogo);
        TextView acertos=(TextView) layout.findViewById(R.id.acertosJogo);
        TextView erros=(TextView) layout.findViewById(R.id.errosJogo);
        nome.setText(placarQuiz.getNome());
        pontos.setText(placarQuiz.getPontos());
        acertos.setText(placarQuiz.getAcertos());
        erros.setText(placarQuiz.getErros());
        return layout;
    }
}


