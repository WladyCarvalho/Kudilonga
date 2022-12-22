package io.expressive.kudilonga.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import io.expressive.kudilonga.model.Exemplo;
import io.expressive.kudilonga.model.Palavra;

@Database(entities = {Palavra.class, Exemplo.class},exportSchema = false,version = 2)
public abstract class KudiDB extends RoomDatabase {

    private static final String nome_bd = "kudi_bd";
    private static KudiDB instancia;

    public abstract AppDAO appDao();

    public static synchronized KudiDB getInstance(Context context){
        if (instancia==null){
            instancia = Room.databaseBuilder(context.getApplicationContext(),KudiDB.class,nome_bd)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instancia;
    }
}
