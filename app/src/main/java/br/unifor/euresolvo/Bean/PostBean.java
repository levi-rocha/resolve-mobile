package br.unifor.euresolvo.Bean;

/**
 * Created by Mbyte on 11/09/2017.
 */

public class PostBean {

    private String titulo;
    private String descricao;

    public PostBean(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
