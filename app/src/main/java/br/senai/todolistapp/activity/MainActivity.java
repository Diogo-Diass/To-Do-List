package br.senai.todolistapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import br.senai.todolistapp.R;
import br.senai.todolistapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        //instancia o binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //define como content view o binding
        setContentView(binding.getRoot());

        //navega a barra do app a navegação
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}