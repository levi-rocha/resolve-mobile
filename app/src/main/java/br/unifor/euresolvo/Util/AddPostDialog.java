package br.unifor.euresolvo.Util;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import br.unifor.euresolvo.R;

//Dialog de criaçao de novo Post - Layout ainda sera alterado
public class AddPostDialog extends Dialog implements
        android.view.View.OnClickListener  {

    public Activity c;
    public Dialog d;
    public Button create, cancelar;

    String titulo;
    String problema;

    public AddPostDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Pegando as referencias dos botoes do Dialog
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_post_dialog);
        create = (Button) findViewById(R.id.btn_create);
        cancelar = (Button) findViewById(R.id.btn_cancelar);
        //Seta o clique dos botoes
        create.setOnClickListener(this);
        cancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //Se clicar no botao de criar
            case R.id.btn_create:
                //Pega as referencias dos textviews
                TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
                TextView txtProblema = (TextView) findViewById(R.id.txtProblema);

                titulo = txtTitulo.getText().toString();
                problema = txtProblema.getText().toString();
                //Chama a Task de criacao de Post
                new HttpRequestTaskCreatePost().execute();
                c.finish();
                break;
            case R.id.btn_cancelar:
                //Se for cancelar, fecha o dialog
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    private class HttpRequestTaskCreatePost extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                //Monta o JSON com os dados do novo post para envio
                String requestJson = "{ \"title\": \"" + titulo + "\", \"content\": \"" + problema + "\", \"author\": { \"username\": \"Usuario Android\" } }";
                //Cria os headers para setar o tipo de dado que esta sendo enviado, no caso, JSON
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);

                final String url = "https://resolve-rest.herokuapp.com/posts";
                //Cria o RestTemplate para realizar o post
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                //Executa o POst para o servidor com os dados do novo post
                String jsonResult = restTemplate.postForObject(url, entity, String.class);


                return jsonResult;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(String jsonResult) {

        }

    }
}
