package br.senai.todolistapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.senai.todolistapp.model.Tarefa;

@Database(entities = {Tarefa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //atributo para acessar a database
    private static AppDatabase database;

    //método para tarefaDao
    public abstract TarefaDao getTarefaDao();

    //método para aeessar o atributo que acessa a database
    public static AppDatabase getDatabase(Context context){
        //verifica se não foi instanciada
        if(database == null){
            //instancia a data base
            database = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "todoLIst").build();


        }
        //retorna a database
        return database;
    }

}
