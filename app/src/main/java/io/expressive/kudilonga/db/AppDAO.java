package io.expressive.kudilonga.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.Map;

import io.expressive.kudilonga.model.Exemplo;
import io.expressive.kudilonga.model.Palavra;
import io.reactivex.rxjava3.core.Single;


@Dao
public interface AppDAO {



    @Query("SELECT * from palavra")
    List<Palavra> getPalavraList();

    @Query("SELECT * from palavra where id = :id_palavra")
    Palavra getPalavra(int id_palavra);

    @Query("SELECT * from exemplo where id_palavra = :id_palavra")
    Single<List<Exemplo>> getAllexamples(int id_palavra);

    @Query("SELECT * From exemplo join palavra where palavra.id =:id and exemplo.id_palavra=:id")
    List<Exemplo> getExemplos(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPalavra(Palavra p);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExemplo(Exemplo e);

    @Update
    void updatePalavra(Palavra p);

    @Delete
    void deletPalavra(Palavra p);
}
