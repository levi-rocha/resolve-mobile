package br.unifor.euresolvo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;

import br.unifor.euresolvo.DTO.CredentialsDTO;
import br.unifor.euresolvo.DTO.UserSimpleDTO;
import br.unifor.euresolvo.Service.Callback;
import br.unifor.euresolvo.Service.Conversor;
import br.unifor.euresolvo.Service.LoginService;

public class EmailLoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    public ProgressBar progressBar;

    private SharedPreferences.Editor prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);

        email = (EditText) findViewById(R.id.editTextEmailLogin);
        pass = (EditText) findViewById(R.id.editTextSenhaLogin);
        progressBar = (ProgressBar) findViewById(R.id.progressBarLoginEmail);
        progressBar.setVisibility(View.INVISIBLE);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
    }

    public void onClickLogin(View view) {
        progressBar.setVisibility(View.VISIBLE);
        Log.d("login", "email: " + email.getText().toString() + " | pass: " +
                pass.getText().toString());
        CredentialsDTO credentials =
                new CredentialsDTO(email.getText().toString(), pass.getText().toString());
        new LoginService().login(credentials, new Callback() {
            @Override
            public void onSuccess(JSONArray result) {
                progressBar.setVisibility(View.INVISIBLE);
                try {
                    UserSimpleDTO loggedUser =
                            new Conversor().toUserSimpleDTO(result.getJSONObject(0));
                    prefs.putLong("userId", loggedUser.getId());
                    prefs.putString("username", loggedUser.getUsername());
                    prefs.putString("userEmail", loggedUser.getEmail());
                    prefs.putLong("permissionId", loggedUser.getPermission().getId());
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(String errorResponse) {
                progressBar.setVisibility(View.INVISIBLE);
                if (errorResponse != null)
                    Log.d("login", errorResponse);
            }
        });
    }

    public void onClickGoCadastreEmail(View view){
        startActivity(new Intent(getApplicationContext(), SignupActivity.class));
    }


}
