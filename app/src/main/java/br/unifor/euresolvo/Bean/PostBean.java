package br.unifor.euresolvo.Bean;

/**
 * Created by SamuelSantiago on 15/10/2017.
 */

public class PostBean {

    private int id;
    private String title;
    private String authorUsername;
    private int voteCount;
    private String date;
    private String contentPreview;

    public PostBean(){

    }

    public PostBean(String title, String content, String author) {
        this.title = title;
        this.contentPreview = content;
        this.authorUsername = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContentPreview() {
        return contentPreview;
    }

    public void setContentPreview(String contentPreview) {
        this.contentPreview = contentPreview;
    }
}