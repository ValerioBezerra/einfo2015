package br.facape.www.einfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;

import br.facape.www.einfo.adapter.ParticipanteAdapter;
import br.facape.www.einfo.banco.ParticipanteDB;
import br.facape.www.einfo.modelo.Participante;

public class CadastroActivity extends AppCompatActivity {
    private ListView lvParticipantes;

    private ParticipanteDB participanteDB;
    private List<Participante> listaParticipantes;
    private ParticipanteAdapter participanteAdapter;
    private Participante participante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        lvParticipantes = (ListView) findViewById(R.id.lvParticipantes);

        lvParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                participante = listaParticipantes.get(position);
                Intent intent = new Intent(CadastroActivity.this, ManutencaoActivity.class);
                intent.putExtra("participante", participante);
                startActivityForResult(intent, 0);
            }
        });

        lvParticipantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                participante = listaParticipantes.get(position);
                participanteDB.excluir(participante.getId());
                carregarDados();
                return true;
            }
        });

        participanteDB = new ParticipanteDB(this);
        carregarDados();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            participante  = new Participante();
            Intent intent = new Intent(CadastroActivity.this, ManutencaoActivity.class);
            intent.putExtra("participante", participante);
            startActivityForResult(intent, 0);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        carregarDados();
    }

    private void carregarDados() {
        listaParticipantes  = participanteDB.getLista();
        participanteAdapter = new ParticipanteAdapter(this, listaParticipantes);
        lvParticipantes.setAdapter(participanteAdapter);
    }
}
