package br.unifor.euresolvo.Bean;

public class CommentBean {

    private String comment;
    private String usuario;

    public CommentBean(String comment, String usuario) {
        this.comment = comment;
        this.usuario = usuario;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
