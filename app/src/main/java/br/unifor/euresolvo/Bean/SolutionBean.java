package br.unifor.euresolvo.Bean;

/**
 * Created by Mbyte on 28/11/2017.
 */

public class SolutionBean {

    private String content;
    private String authorUsername;

    public SolutionBean(String content, String authorUsername) {
        this.content = content;
        this.authorUsername = authorUsername;
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
}
