package br.unifor.euresolvo.Bean;


public class PostBean {

    private String titulo;
    private String descricao;
    private String usuario;
    private int qtdLikes;


    public PostBean(String titulo, String descricao, String usuario, int qtdLikes) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.qtdLikes = qtdLikes;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getQtdLikes() {
        return qtdLikes;
    }

    public void setQtdLikes(int qtdLikes) {
        this.qtdLikes = qtdLikes;
    }
}
