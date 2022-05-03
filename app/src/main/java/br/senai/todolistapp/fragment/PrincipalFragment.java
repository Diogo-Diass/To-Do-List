package br.senai.todolistapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.todolistapp.R;
import br.senai.todolistapp.adapter.TarefaAdapter;
import br.senai.todolistapp.database.AppDatabase;
import br.senai.todolistapp.databinding.FragmentPrincipalBinding;
import br.senai.todolistapp.model.Tarefa;


public class PrincipalFragment extends Fragment {

    private FragmentPrincipalBinding binding;
    //variavel pra database
    private AppDatabase database;
    //variavel para o adapater
    private TarefaAdapter adapter;
    //variavel para a lista
    private List<Tarefa> tarefas;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //instancia o binding
        binding = FragmentPrincipalBinding.inflate(inflater, container, false);
        //clique no botão de adicionar tarefa
        binding.btnAddTarefa.setOnClickListener(view -> {
            NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_principalFragment_to_cadTarefaFragment);
        });

        //retorna a view raiz do Binding
        return binding.getRoot();
    }
}