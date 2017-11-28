package br.unifor.euresolvo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

import br.unifor.euresolvo.DTO.UserSimpleDTO;
import br.unifor.euresolvo.Models.User;
import br.unifor.euresolvo.Service.Callback;
import br.unifor.euresolvo.Service.Conversor;
import br.unifor.euresolvo.Service.UserService;

public class SignUpActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;

    private SharedPreferences.Editor prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText) findViewById(R.id.editTextName);
        email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPass);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
    }

    public void onClick_Continue (View view){
        User user = new User();
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        user.setUsername(name.getText().toString());
        new UserService().insertUser(user, new Callback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    UserSimpleDTO loggedUser =
                            new Conversor().toUserSimpleDTO(result.getJSONObject(0));
                    prefs.putLong("userId", loggedUser.getId());
                    prefs.putString("username", loggedUser.getUsername());
                    prefs.putString("userEmail", loggedUser.getEmail());
                    prefs.putLong("permissionId", loggedUser.getPermission().getId());
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
