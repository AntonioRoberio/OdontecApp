package com.des.odontec.equipe.odontec.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.des.odontec.equipe.odontec.R;

/**
 * Created by Antonio on 26/10/2017.
 */

public class LayoutsAdpater extends BaseAdapter{

    private String[] dados;
    private LayoutInflater layoutInflater;
    private View layout;

    public LayoutsAdpater(Context context,String[] dados){
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout=layoutInflater.inflate(R.layout.adapter_layout,null);
        this.dados=dados;
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
        if(convertView == null){
            layout=layoutInflater.inflate(R.layout.adapter_layout,null);
        }else {
            layout=convertView;
        }
        layout.setMinimumWidth(1000);
        TextView textView=(TextView) layout.findViewById(R.id.va);
        textView.setText(dados[position]);
        return layout;
    }
}
