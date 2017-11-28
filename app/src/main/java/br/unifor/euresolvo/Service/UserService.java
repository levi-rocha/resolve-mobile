package br.unifor.euresolvo.Service;

import org.json.JSONException;
import org.json.JSONObject;

import br.unifor.euresolvo.Models.User;

public class UserService {

    private EuResolvoRestClient client;

    public UserService() {
        this.client = new EuResolvoRestClient();
    }

    public void getUsers(int pageSize, int pageNumber, Callback callback) {
        String request = "users?page=" + pageNumber + "&size=" + pageSize;
        client.get(request, callback);
    }

    public void isEmailTaken(String email, Callback callback) {
        String request = "users/verifyEmailTaken?email=" + email;
        client.get(request, callback);
    }

    public void getUserWithId(long id, Callback callback) {
        String request = "users/" + id;
        client.get(request, callback);
    }

    public void getUserWithEmail(String email, Callback callback) {
        String request = "users/search?email=" + email;
        client.get(request, callback);
    }

    public void insertUser(User user, Callback callback) {
        client.post("users", userCreate(user), callback);
    }

    public void updateUser(User user, Callback callback) {
        String request = "users/" + user.getId();
        client.patch(request, userUpdate(user), callback);
    }

    public void deleteUser(long id, Callback callback) {
        String request = "users/" + id;
        client.delete(request, callback);
    }

    private JSONObject userCreate(User user) {
        JSONObject entity = new JSONObject();
        try {
            entity.put("username", user.getUsername());
            entity.put("password", user.getPassword());
            entity.put("email", user.getEmail());
            JSONObject permission = new JSONObject();
            permission.put("id", user.getPermission().getId());
            entity.put("permission", permission);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entity;
    }

    private JSONObject userUpdate(User user) {
        JSONObject entity = new JSONObject();
        try {
            entity.put("username", user.getUsername());
            entity.put("email", user.getEmail());
            JSONObject permission = new JSONObject();
            permission.put("id", user.getPermission().getId());
            entity.put("permission", permission);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entity;
    }

}
