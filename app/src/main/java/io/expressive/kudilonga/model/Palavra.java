package io.expressive.kudilonga.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Palavra {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "cl_palavra")
    private String palavra;
    @ColumnInfo(name = "cl_significado")
    private String Significado;

    public Palavra(int id, String palavra, String significado) {
        this.id = id;
        this.palavra = palavra;
        Significado = significado;
    }

    public Palavra() {
    }

    @Ignore
    public Palavra(String palavra, String significado) {
        this.palavra = palavra;
        Significado = significado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getSignificado() {
        return Significado;
    }

    public void setSignificado(String significado) {
        Significado = significado;
    }
}
