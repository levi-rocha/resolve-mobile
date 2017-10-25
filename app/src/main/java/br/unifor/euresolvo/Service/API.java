package br.unifor.euresolvo.Service;

/**
 * Created by SamuelSantiago on 13/10/2017.
 */

public final class API {
    private static String url = "https://resolve-rest.herokuapp.com/";

    public static String postsGET(){
        return url + "posts";
    }
    public static String postsGET(int id){
        return url + "posts/" + id;
    }

    public static String usersGET(){
        return url + "users";
    }
    public static String usersGET(int id){
        return url + "users/id";
    }

    public static String commentsGET(){
        return url + "comments";
    }
    public static String commentsGET(int id){
        return url + "comments/" + id;
    }

    public static String solutionsGET(){
        return url + "solutions";
    }
    public static String solutionsGET(int id){
        return url + "solutions/" + id;
    }

    public static String reportsGET(){
        return url + "reports";
    }

    public static String loginPOST(){
        return url + "loginEmail";
    }

    public static String reportsGET(int id){
        return url + "reports/" + id;
    }

}
