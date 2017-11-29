package br.unifor.euresolvo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

import br.unifor.euresolvo.DTO.UserSimpleDTO;
import br.unifor.euresolvo.Models.Permission;
import br.unifor.euresolvo.Models.User;
import br.unifor.euresolvo.Service.Callback;
import br.unifor.euresolvo.Service.Conversor;
import br.unifor.euresolvo.Service.UserService;

public class SignUpActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;
    private ProgressDialog mProgressDialog;
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

    public void onClick_Continue (final View view){
        User user = new User();
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        user.setUsername(name.getText().toString());
        Permission permission = new Permission();
        permission.setId(1);
        user.setPermission(permission);
        showProgressDialog();
        new UserService().insertUser(user, new Callback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    hideProgressDialog();
                    Snackbar.make(view, R.string.signupSuccess, Snackbar.LENGTH_LONG).show();
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
            @Override
            public void onFailure(String errorResponse) {
                hideProgressDialog();
                Log.d("fudeu", errorResponse);
                Snackbar.make(view, R.string.signupFailure, Snackbar.LENGTH_LONG).show();
            }
        });

    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
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
