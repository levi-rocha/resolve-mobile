package br.unifor.euresolvo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

import br.unifor.euresolvo.DTO.CredentialsDTO;
import br.unifor.euresolvo.DTO.UserSimpleDTO;
import br.unifor.euresolvo.Service.Callback;
import br.unifor.euresolvo.Service.Conversor;
import br.unifor.euresolvo.Service.LoginService;

public class SignInActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private ProgressDialog mProgressDialog;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);

        email = (EditText) findViewById(R.id.editTextEmailLogin);
        pass = (EditText) findViewById(R.id.editTextSenhaLogin);

        prefs = getApplicationContext().getSharedPreferences("loginPref", MODE_PRIVATE);
    }

    public void onClickLogin(View view) {
        Log.d("login", "email: " + email.getText().toString() + " | pass: " +
                pass.getText().toString());
        CredentialsDTO credentials =
                new CredentialsDTO(email.getText().toString(), pass.getText().toString());
        showProgressDialog();
        new LoginService().login(credentials, new Callback() {
            @Override
            public void onSuccess(JSONArray result) {
                hideProgressDialog();
                try {
                    UserSimpleDTO loggedUser =
                            new Conversor().toUserSimpleDTO(result.getJSONObject(0));
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putLong("userId", loggedUser.getId());
                    editor.putString("username", loggedUser.getUsername());
                    editor.putString("userEmail", loggedUser.getEmail());
                    editor.putLong("permissionId", loggedUser.getPermission().getId());
                    editor.commit();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(String errorResponse) {
                hideProgressDialog();
            }
        });
    }

    public void onClickGoCadastreEmail(View view){
        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.signinLoading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }


}
