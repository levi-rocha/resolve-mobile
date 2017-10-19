package br.unifor.euresolvo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import br.unifor.euresolvo.Adapter.CommentAdapter;
import br.unifor.euresolvo.Adapter.SolucaoAdapter;
import br.unifor.euresolvo.Bean.CommentBean;
import br.unifor.euresolvo.Bean.SolucaoBean;

public class DetailPost extends MainActivity {

    TextView txtTitulo;
    TextView txtDescricao;

    ArrayList<CommentBean> commentBeans;
    private static CommentAdapter commentAdapter;
    ArrayList<SolucaoBean> solucaoBeans;
    private static SolucaoAdapter solucaoAdapter;
    RecyclerView mRecyclerView;
    RecyclerView mRecyclerViewSolucao;

    private void setupRecycler() {
        //Criando lista para teste
        commentBeans = new ArrayList<>();
        solucaoBeans = new ArrayList<>();

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        commentAdapter = new CommentAdapter(commentBeans);
        mRecyclerView.setAdapter(commentAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.post_line));
        mRecyclerView.addItemDecoration(itemDecorator);

        //RECYCLERVIEW SOLUCOES
        LinearLayoutManager layoutManagerSolucao = new LinearLayoutManager(this);
        mRecyclerViewSolucao.setLayoutManager(layoutManagerSolucao);

        solucaoAdapter = new SolucaoAdapter(solucaoBeans);
        mRecyclerViewSolucao.setAdapter(solucaoAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        DividerItemDecoration itemDecoratorSolucao = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoratorSolucao.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.post_line));
        mRecyclerViewSolucao.addItemDecoration(itemDecoratorSolucao);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        txtTitulo = (TextView) findViewById(R.id.txtTitle);
        txtDescricao = (TextView) findViewById(R.id.txtDescription);

        Intent intent = getIntent();

        String titulo = intent.getStringExtra("titulo");
        String descricao = intent.getStringExtra("descricao");

        txtTitulo.setText(titulo);
        txtDescricao.setText(descricao);

        mRecyclerView = (RecyclerView) findViewById(R.id.comment_recycler_view_list);
        mRecyclerViewSolucao = (RecyclerView) findViewById(R.id.solucao_recycler_view_list);
        setupRecycler();
    }

    @Override
    public void onStart() {
        super.onStart();
        //Executa as tasks para recuperaç~ao dos comentarios e soluçoes do Post
        new DetailPost.HttpRequestTask().execute();
        new DetailPost.HttpRequestTaskSolucao().execute();
    }
    //Task para recuperaç~ao de comentarios do post
    private class HttpRequestTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                final String url = "https://resolve-rest.herokuapp.com/comments";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                String jsonResult = restTemplate.getForObject(url, String.class);
                return jsonResult;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(String jsonResult) {
            try {
                JSONArray jsonArray = new JSONArray(jsonResult);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject explrObject = jsonArray.getJSONObject(i);
                    int id = explrObject.getInt("id");
                    String content = explrObject.getString("content");
                    String date = explrObject.getString("date");
                    String authorUsername = explrObject.getString("authorUsername");

                    CommentBean commentBean = new CommentBean(id, content, authorUsername);
                    commentBeans.add(commentBean);
                }

                commentAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("RESULTADO JSON", jsonResult + " - Size: " + commentBeans.size());
        }

    }
    //Task para recuperacao de solucoes do post
    private class HttpRequestTaskSolucao extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                final String url = "https://resolve-rest.herokuapp.com/solutions";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                String jsonResult = restTemplate.getForObject(url, String.class);
                return jsonResult;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(String jsonResult) {
            try {
                JSONArray jsonArray = new JSONArray(jsonResult);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject explrObject = jsonArray.getJSONObject(i);
                    int id = explrObject.getInt("id");
                    String content = explrObject.getString("content");
                    String date = explrObject.getString("date");
                    String authorUsername = explrObject.getString("authorUsername");

                    SolucaoBean solucaoBean = new SolucaoBean(id, content, authorUsername);
                    solucaoBeans.add(solucaoBean);
                }

                solucaoAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("RESULTADO JSON SOLUÇOES", jsonResult + " - Size: " + solucaoBeans.size());
        }

    }

}
