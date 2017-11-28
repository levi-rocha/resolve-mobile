package br.unifor.euresolvo.Service;

import org.json.JSONException;
import org.json.JSONObject;

import br.unifor.euresolvo.DTO.CredentialsDTO;

public class LoginService {

    private EuResolvoRestClient client;

    public LoginService() {
        this.client = new EuResolvoRestClient();
    }

    public void login(CredentialsDTO credentialsDTO, Callback callback) {
        client.post("login", credentials(credentialsDTO), callback);
    }

    private JSONObject credentials(CredentialsDTO credentialsDTO) {
        JSONObject entity = new JSONObject();
        try {
            entity.put("email", credentialsDTO.getEmail());
            entity.put("password", credentialsDTO.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
