package br.senai.todolistapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.senai.todolistapp.R;
import br.senai.todolistapp.model.Tarefa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    //lista de tarefas
    private List<Tarefa> tarefas;
    private Context context;




    //construtor pra receber os valores
    public TarefaAdapter(List<Tarefa> lista, Context contexto){
        this.tarefas = lista;
        this.context = contexto;


    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //infla o layout do adapter
        View view = LayoutInflater.from(context).inflate(R.layout.item_tarefa, parent, false);
        //retorna um novo viewHolder com a view
        return new TarefaViewHolder(view);
    }

    //chamado pra cada quantidade de tarefas, exemplo: 30 tarefas, chamado 30 vezes
    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        //obtem a tarefa pela positionn
        Tarefa t = tarefas.get(position);

        //exibe o titulo da tarefa no text view
        holder.tvTitulo.setText(t.getTitulo());

        //se estiver concluida
        if(t.isConcluida()){
            holder.tvStatus.setText(R.string.concluida);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.verde));
        }else {
            holder.tvStatus.setText(R.string.aberta);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.vermelho));
        }
    //Formata a data de long pra string
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvData.setText(formatador.format(t.getDataPrevista()));
    }

    //chamado apenas uma vez na tarefa em geral
    @Override
    //retorna a quantidade de elementos a serem exibidos na lista
    public int getItemCount() {
        if(tarefas != null){
            return tarefas.size();
        }
        return 0;
    }

    class TarefaViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvData, tvStatus;

        public TarefaViewHolder(View view) {
            super(view);

            //variáveis para acessar os componentes ao XMl


            tvTitulo = view.findViewById(R.id.titulo_tarefa);
            tvData = view.findViewById(R.id.data_tarefa);
            tvStatus = view.findViewById(R.id.status_tarefa);




        }
    }
}


