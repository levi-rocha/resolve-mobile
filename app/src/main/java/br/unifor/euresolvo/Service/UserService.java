package br.unifor.euresolvo.Service;

public class UserService {

    private EuResolvoRestClient client;

    public UserService() {
        this.client = new EuResolvoRestClient();
    }

    public void getUsers(Callback callback) {
        client.get("users", null, callback);
    }

    public void getUsers(int pageNumber, int pageSize, Callback callback) {
        String request = "users?page=" + pageNumber + "&size=" + pageSize;
        client.get(request, null, callback);
    }

    public void isEmailTaken(String email, Callback callback) {
        String request = "users/verifyEmailTaken?email=" + email;
        client.get(request, null, callback);
    }

    public void getUserWithId(long id, Callback callback) {
        String request = "users/" + id;
        client.get(request, null, callback);
    }

    public void insertUser(User user, Callback callback) {

    }

}
