package br.unifor.euresolvo.Service;

import android.util.Log;

public class UserService {

    private EuResolvoRestClient client;

    public UserService() {
        this.client = new EuResolvoRestClient();
    }

    public void listUsers(Callback callback) {
        Log.d("bla", "calling get from user service");
        client.get("users", null, callback);
    }

}
