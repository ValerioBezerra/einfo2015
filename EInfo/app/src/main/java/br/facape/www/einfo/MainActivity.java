package br.facape.www.einfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.facape.www.einfo.banco.DBHelper;
import br.facape.www.einfo.banco.ParticipanteDB;
import br.facape.www.einfo.modelo.Participante;

public class MainActivity extends AppCompatActivity {
    private TextView txtTexto;
    private Button btnCadastros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTexto     = (TextView) findViewById(R.id.txtTexto);
        btnCadastros = (Button) findViewById(R.id.btnCadastros);

        btnCadastros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

        ParticipanteDB participanteDB = new ParticipanteDB(this);

//        Participante participante = new Participante();
//        participante.setId(4);
//        participante.setNome("Cynara");
//        participanteDB.inserir(participante);
//        participanteDB.alterar(participante);
//        participanteDB.excluir(3);

        for (Participante p: participanteDB.getLista()) {
            Log.i("Nome", p.getNome());
        }
    }
}
