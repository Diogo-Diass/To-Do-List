package br.senai.todolistapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.todolistapp.R;
import br.senai.todolistapp.databinding.FragmentCadSubTarefaBinding;


public class CadSubTarefaFragment extends Fragment {

    private FragmentCadSubTarefaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentCadSubTarefaBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}