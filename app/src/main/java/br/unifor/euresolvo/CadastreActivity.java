package br.unifor.euresolvo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.unifor.euresolvo.Bean.UserBeanOLD;
import br.unifor.euresolvo.Dao.UserDao;

public class CadastreActivity extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 500;
    private int idType;
    private UserBeanOLD userBeanOLD;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastre);
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.hide();

        idType = 0;
        userDao = new UserDao(getApplicationContext());
        userBeanOLD = userDao.consult();
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
            userBeanOLD.setPersonType(idType);
            userDao.reset();
            userDao.salve(userBeanOLD);
            userDao.close();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        }
    }

    private void carregarFoto() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    ImageView imageViewPhoto = (ImageView) findViewById(R.id.imageView_CadastroFoto);
                    if(userBeanOLD.getPersonPhoto() != null)
                    Picasso.with(getApplicationContext()).load(userBeanOLD.getPersonPhoto()).resize(500, 500).centerCrop().into(imageViewPhoto);

            }
        }, SPLASH_TIME_OUT);
    }


}
