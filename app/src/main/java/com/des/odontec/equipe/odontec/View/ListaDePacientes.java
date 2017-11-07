package com.des.odontec.equipe.odontec.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.des.odontec.equipe.odontec.Controller.PacienteController;
import com.des.odontec.equipe.odontec.Model.Paciente;
import com.des.odontec.equipe.odontec.R;

import java.util.ArrayList;

public class ListaDePacientes extends AppCompatActivity {
    private ListView listView;
    private int i;
    private PacienteController pacienteController;
    private String[] nomes;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_pacientes);
        listView = (ListView) findViewById(R.id.lsvPass);
        pacienteController = new PacienteController(this);
        final ArrayList<Paciente> pacientes = pacienteController.listaPacientes();
        nomes = new String[pacientes.size()];
        for (Paciente p : pacientes) {
            nomes[i] = p.getNome();
            i++;
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, nomes);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bundle=new Bundle();
                bundle.putInt("id",pacientes.get(position).getId());
                Intent intent=new Intent(ListaDePacientes.this,VizualizarDadosPaciente.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,9);
            }
        });
    }

}
