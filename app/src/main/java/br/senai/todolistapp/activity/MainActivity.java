package br.senai.todolistapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.senai.todolistapp.R;
import br.senai.todolistapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        //instancia o binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //define como content view o binding
        setContentView(binding.getRoot());
    }
}