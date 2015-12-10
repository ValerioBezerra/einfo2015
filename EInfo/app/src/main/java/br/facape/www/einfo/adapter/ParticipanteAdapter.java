package br.facape.www.einfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.facape.www.einfo.R;
import br.facape.www.einfo.modelo.Participante;

public class ParticipanteAdapter extends BaseAdapter {
    private List<Participante> listaParticipantes;
    private LayoutInflater inflater;

    public ParticipanteAdapter(Context context, List<Participante> listaParticipantes) {
        this.listaParticipantes = listaParticipantes;
        this.inflater           = (LayoutInflater) context.getSystemService(
                                                    Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listaParticipantes.size();
    }

    @Override
    public Object getItem(int position) {
        return listaParticipantes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaParticipantes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.adapter_participante, parent, false);

        TextView txtNome = (TextView) view.findViewById(R.id.txtNome);
        txtNome.setText(listaParticipantes.get(position).getNome());

        return view;
    }
}
