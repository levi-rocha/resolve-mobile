package br.unifor.euresolvo.Models;

import java.util.List;

public class Post {
    private long id;
    private String title;
    private String content;

    private User author;
    private List<User> votes;
    private List<Comment> comments;
    private List<Report> reports;
}
