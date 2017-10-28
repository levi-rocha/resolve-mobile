package br.unifor.euresolvo.Models;

import java.util.List;

public class User {
    private long id;
    private String username;
    private String email;

    private Permission permission;
    private List<Post> posts;
    private List<Comment> comments;
    private List<Report> reports;
}
