package io.expressive.kudilonga.model;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Exemplo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String descricao;

    private int id_palavra;

    public int getId_palavra() {
        return id_palavra;
    }


    public void setId_palavra(int id_palavra) {
        this.id_palavra = id_palavra;
    }
    @Ignore
    public Exemplo(int id,int id_palavra, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.id_palavra = id_palavra;
    }

    public Exemplo() {
    }

    @Ignore
    public Exemplo(int id_palavra,String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Exemplo{" +
                "descricao='" + descricao + '\'' +
                '}';
    }
}
