package br.unifor.euresolvo.Bean;

import android.net.Uri;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.security.Permission;
import java.util.ArrayList;

/**
 * Created by SamuelSantiago on 16/10/2017.
 */

public class UsersBean {

    private int id;
    private String username;
    private String email;
    private String password;
    private Permission permission;
    private Uri photo;
    private ArrayList<PostsBean> postList;

    public ArrayList<PostsBean> getPostList() {
        return postList;
    }

    public void setPostList(ArrayList<PostsBean> postList) {
        this.postList = postList;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }
    public void setPhoto(String photo) {

        this.photo =  Uri.parse(photo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public void setPostList(JSONObject posts) {
        Gson gson = new Gson();
        postList = new ArrayList<>();
        postList.add(gson.fromJson(String.valueOf(posts), PostsBean.class));
    }
}
