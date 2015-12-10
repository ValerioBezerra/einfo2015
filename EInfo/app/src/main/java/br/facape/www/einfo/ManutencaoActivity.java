package br.facape.www.einfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.facape.www.einfo.banco.ParticipanteDB;
import br.facape.www.einfo.modelo.Participante;

public class ManutencaoActivity extends AppCompatActivity {
    private EditText edtNome;

    private Participante participante;
    private ParticipanteDB participanteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manutencao);

        edtNome = (EditText) findViewById(R.id.edtNome);

        participante   = (Participante) getIntent().getSerializableExtra("participante");
        participanteDB = new ParticipanteDB(this);

        if (participante.getId() > 0) {
            edtNome.setText(participante.getNome());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manutencao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            participante.setNome(edtNome.getText().toString());

            if (participante.getId() > 0) {
                participanteDB.alterar(participante);
            } else {
                participanteDB.inserir(participante);
            }

            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
