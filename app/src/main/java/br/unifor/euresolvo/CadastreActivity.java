package br.unifor.euresolvo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.unifor.euresolvo.Bean.UserBean;
import br.unifor.euresolvo.Dao.UserDao;
import br.unifor.euresolvo.R;

public class CadastreActivity extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 1000;
    private int idType;
    private UserBean userBean;
    private UserDao userDao;
    private boolean frist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastre);
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.hide();

        idType = 0;
        userDao = new UserDao(getApplicationContext());
        userBean = userDao.consult();
        frist = true;
        carregarFoto();
    }

    public void button_cPadrao(View view){
        idType = 1;
    }

    public void button_cEmpresa(View view){
        idType = 2;
    }

    public void finalizarCadastro(View view){
        if(idType == 0){
            Toast.makeText(getApplicationContext(),"Favor escolher tipo de conta antes de continuar", Toast.LENGTH_SHORT).show();
        }else {
            userBean.setPersonType(idType);
            userDao.reset();
            userDao.salve(userBean);
            userDao.close();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        }
    }

    private void carregarFoto() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(frist) {
                    ImageView imageViewPhoto = (ImageView) findViewById(R.id.imageView_CadastroFoto);
                    Picasso.with(getApplicationContext()).load(userBean.getPersonPhoto()).resize(500, 500).centerCrop().into(imageViewPhoto);
                    frist = false;
                }
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();


    }
}
