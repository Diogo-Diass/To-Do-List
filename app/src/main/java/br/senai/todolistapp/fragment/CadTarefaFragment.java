package br.senai.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.senai.todolistapp.R;
import br.senai.todolistapp.database.AppDatabase;
import br.senai.todolistapp.databinding.FragmentCadTarefaBinding;
import br.senai.todolistapp.model.Tarefa;


public class CadTarefaFragment extends Fragment {

    private FragmentCadTarefaBinding binding;
    //variável para o datepicker
    DatePickerDialog datePicker;
    //variaveis para o dia mes e ano
    int year, month, day;

    //variável para a data atual
    Calendar dataAtual;
    //variavel para a data formatada
    String dataEscolhida = "";

    //variável para acessar a database

    AppDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //instancia a app database
        database = AppDatabase.getDatabase(getActivity());

        //instancia o binding
        binding = FragmentCadTarefaBinding.inflate(inflater, container, false);

        //instancia a data atual
        dataAtual = Calendar.getInstance();

        //descobre o dia mês e ano atual
        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);

        //instanciar o datepicker
        datePicker = new DatePickerDialog(getContext(), (view, ano, mes, dia) -> {
                //cai aqui toda vez que clica no botao de datepickerdialog
                //passa para as variávéis globais
            year = ano;
            month = mes;
            day = dia;

            //formata a String da dataEscolhida
            dataEscolhida = String.format("%02d/%02d/%04d", day, month + 1, year);
            binding.btData.setText(dataEscolhida);

            }, year, month, day);

        //listener do botao de data
        binding.btData.setOnClickListener(v -> {
            //abre o datepicker
        datePicker.show();
        });
        //listener do botão salvar
        binding.btSalvar.setOnClickListener(v ->{
            //validar os campos
            if(binding.textTitulo.getText().toString().isEmpty()){
                binding.textTitulo.setError(getString(R.string.informeTitulo));
                binding.textTitulo.requestFocus();
            }else if(dataEscolhida.isEmpty()){
                Toast.makeText(getActivity().getBaseContext(), R.string.informeData, Toast.LENGTH_SHORT).show();

            }else {
                //criar um objeto tarefa
                Tarefa tarefa = new Tarefa();
                //popular a tarefa
                tarefa.setTitulo(binding.textTitulo.getText().toString());
                tarefa.setDescricao(binding.textDescricao.getText().toString());
                //cria um calendar e popula com a data que foi selecionada
                Calendar dataRealizacao = Calendar.getInstance();
                dataRealizacao.set(year, month, day);
                //passa para a tarefa os milisegundos da data
                tarefa.setDataPrevista(dataRealizacao.getTimeInMillis());
                //cria um calendar para a data atual
                Calendar dataAtual = Calendar.getInstance();
                tarefa.setDataCriacao(dataAtual.getTimeInMillis());

                //salvar a Tarefa no BD
                new InsertTarefa().execute(tarefa);
            }
        });


        //retorna a view raiz do binding
        return binding.getRoot();
    }
    //classe para a Task de inserir tarefa
    private class InsertTarefa extends AsyncTask<Tarefa, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.w( "Passou ", "No onPreExecute");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.w( "Passou ", "No onProgressUpdate");

        }

        @Override
        protected String doInBackground(Tarefa... tarefas) {
            Log.w( "Passou ", "no doInBackground");
            //extrair a tarefa do vetor
            Tarefa t = tarefas[0];
            try {
                database.getTarefaDao().insert(t);
                //retorna ok caso tenha passado sem erro
                return "ok";
            }catch (Exception e){
                e.printStackTrace();
                //retorna a mensagem caso tenha dado erro
                return e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String msg) {
            if(msg.equals("ok")){
                Log.w("MSG", "Deu certo ;) ");
                //aciona o botão de voltar
                getActivity().onBackPressed();
            }else {
                Log.w( "MSG", msg);
                Toast.makeText(getContext(),"DEU RUIM"+msg, Toast.LENGTH_SHORT).show();
            }



        }
    }

}