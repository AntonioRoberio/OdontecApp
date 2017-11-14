package com.des.odontec.equipe.odontec.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.des.odontec.equipe.odontec.R;

/**
 * Created by Antonio on 26/10/2017.
 */

public class LayoutsAdpater extends BaseAdapter {

    private String[] dados;
    private LayoutInflater layoutInflater;
    private View layout;
    private int img;

    public LayoutsAdpater(Context context, String[] dados, int img) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = layoutInflater.inflate(R.layout.adapter_layout, null);
        this.dados = dados;
        this.img = img;
    }

    @Override
    public int getCount() {
        return dados.length;
    }

    @Override
    public Object getItem(int position) {
        return dados[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            layout = layoutInflater.inflate(R.layout.adapter_layout, null);
        } else {
            layout = convertView;
        }
        layout.setMinimumWidth(1000);
        ImageView imageView = (ImageView) layout.findViewById(R.id.imgAdapter);
        switch (img) {
            case 0:
                imageView.setImageResource(R.drawable.patologia);
                break;
            case 1:
               imageView.setImageResource(R.drawable.alteracao);
                break;
            case 2:
                imageView.setImageResource(R.drawable.anestesico);
                break;
            default:
                imageView.setImageResource(R.drawable.paciente);
        }
        if(img != 3){
            layout.setBackgroundResource(R.drawable.fundolayouts);

        }
        TextView textView = (TextView) layout.findViewById(R.id.va);
        textView.setText(dados[position]);
        return layout;
    }
}
