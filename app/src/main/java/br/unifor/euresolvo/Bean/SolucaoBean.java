package br.unifor.euresolvo.Bean;

import java.util.Date;

public class SolucaoBean {

    private int id;
    private String content;
    private String authorUsername;
    private Date data;

    public SolucaoBean(int id, String content, String authorUsername) {
        this.id = id;
        this.content = content;
        this.authorUsername = authorUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
