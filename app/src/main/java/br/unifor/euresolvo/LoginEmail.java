package br.unifor.euresolvo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginEmail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);
    }

    public void onClickGoCadastreEmail(View view){
        startActivity(new Intent(getApplicationContext(), CadastreEmailActivity.class));
    }
}
