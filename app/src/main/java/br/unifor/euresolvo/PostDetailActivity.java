package br.unifor.euresolvo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class PostDetailActivity extends AppCompatActivity {

    TextView txtTitulo;
    TextView txtDescricao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        txtTitulo = (TextView) findViewById(R.id.txtTitle);
        txtDescricao = (TextView) findViewById(R.id.txtDescription);

        Intent intent = getIntent();

        String titulo = intent.getStringExtra("titulo");
        String descricao = intent.getStringExtra("descricao");

        txtTitulo.setText(titulo);
        txtDescricao.setText(descricao);
    }
}
