package br.unifor.euresolvo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.unifor.euresolvo.Bean.UserBeanOLD;
import br.unifor.euresolvo.Bean.UsersBean;
import br.unifor.euresolvo.Dao.UserDao;
import br.unifor.euresolvo.Service.API;
import br.unifor.euresolvo.Service.ServiceLoginPOST;

public class LoginEmail extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);

        email = (EditText) findViewById(R.id.editTextEmailLogin);
        pass = (EditText) findViewById(R.id.editTextSenhaLogin);
        progressBar = (ProgressBar) findViewById(R.id.progressBarLoginEmail);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void onClickLogin(View view) {
        final ServiceLoginPOST serviceLoginPOST = new ServiceLoginPOST(API.loginPOST(), email.getText().toString(), pass.getText().toString());
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UsersBean usersBean = serviceLoginPOST.getUser();
                progressBar.setVisibility(View.INVISIBLE);

                if (usersBean.getEmail() != null) {
                    UserDao dao = new UserDao(getApplicationContext());
                    dao.salve(new UserBeanOLD(usersBean.getId(), usersBean.getUsername(), usersBean.getEmail()));
                    startActivity(new Intent(getApplicationContext(), CadastreActivity.class));
                }else {
                    Toast.makeText(getApplicationContext(), "Tente novamente", Toast.LENGTH_LONG).show();
                }
            }
        }, 3000);

    }

    public void onClickGoCadastreEmail(View view){
        startActivity(new Intent(getApplicationContext(), CadastreEmailActivity.class));
    }


}
