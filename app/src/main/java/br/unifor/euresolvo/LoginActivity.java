package br.unifor.euresolvo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.unifor.euresolvo.Bean.UserBeanOLD;
import br.unifor.euresolvo.DTO.UserSimpleDTO;
import br.unifor.euresolvo.Dao.UserDao;
import br.unifor.euresolvo.Models.Permission;
import br.unifor.euresolvo.Service.Callback;
import br.unifor.euresolvo.Service.UserService;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener{


    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private ProgressDialog mProgressDialog;
    private GoogleApiClient mGoogleApiClient;
    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar bar = getSupportActionBar();

        /* ---- EXEMPLO UTILIZAÇÃO DE SERVICE ---- */
        UserService userService = new UserService();
        // Pegar primeiros 5 usuários
        userService.getUsers(5, 0, new Callback() {
            // Método chamado quando request é sucesso
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    // Pegando primeiro objeto retornado
                    // (verificar o doc da API para saber os atributos)
                    JSONObject object = result.getJSONObject(0);
                    // Transformando no DTO adequado
                    UserSimpleDTO dto = new UserSimpleDTO();
                    dto.setId(object.getLong("id"));
                    dto.setUsername(object.getString("username"));
                    dto.setEmail(object.getString("email"));
                    JSONObject permissionJSON = object.getJSONObject("permission");
                    Permission permission = new Permission();
                    permission.setId(permissionJSON.getLong("id"));
                    dto.setPermission(permission);
                    Log.d("exemplo-service", "user retornado: " + dto.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            // Método chamado quando request da erro
            @Override
            public void onFailure(String errorResponse) {
                // Logando o erro
                Log.d("exemplo-service", "erro retornado: " + errorResponse);
            }
        });
        /* ---- FIM DO EXEMPLO ---- */

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        // Configure sign-in to request the user's ID, email address, and basic profile. ID and
        // basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to GoogleSignIn.API and the options above.
        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        bar.hide();
        dao = new UserDao(this);

        if(!dao.isEmpy()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }else {
            try{
                boolean logout = getIntent().getExtras().getBoolean("logout");
                if (logout){
                    new UserDao(getApplicationContext()).reset();
                }
            }catch (Exception e){
                // logout não execultado
            }
        }

    }


    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from
        //   GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            salvarUser(acct);
            hideProgressDialog();
            startActivity(new Intent(LoginActivity.this, CadastreActivity.class));
        }
    }

    private void salvarUser(GoogleSignInAccount acct) {
        UserBeanOLD userBeam = new UserBeanOLD(acct.getDisplayName(), acct.getEmail(),acct.getId(), acct.getPhotoUrl());
        userBeam.setPassword( acct.getIdToken());
        dao.salve(userBeam);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void signIn() {
        boolean logout;

        try {
            logout = getIntent().getExtras().getBoolean("logout");
        }catch (Exception e){
            logout = false;
        }

        if(logout){
            signOut();
        }
        showProgressDialog();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
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

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            // ...
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        dao.close();
        mGoogleApiClient.disconnect();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    public void OnClickGoEmail(View view){
        startActivity(new Intent(getApplicationContext(), LoginEmail.class));
    }

}
