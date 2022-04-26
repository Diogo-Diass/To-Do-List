package br.senai.todolistapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.todolistapp.R;
import br.senai.todolistapp.databinding.FragmentPrincipalBinding;


public class PrincipalFragment extends Fragment {

private FragmentPrincipalBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //instancia o binding
        binding = FragmentPrincipalBinding.inflate(inflater, container, false);
        //retorna a view raiz do Binding
        return binding.getRoot();
    }
}