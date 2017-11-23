package br.unifor.euresolvo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import br.unifor.euresolvo.Adapter.CommentAdapter;
import br.unifor.euresolvo.Bean.CommentBean;
import br.unifor.euresolvo.Service.API;
import br.unifor.euresolvo.Service.ServiceCommentGET;

public class DetailPost extends MainActivity {

    TextView txtTitulo;
    TextView txtDescricao;

    ArrayList<CommentBean> commentBeans;
    private static CommentAdapter commentAdapter;
    RecyclerView mRecyclerView;

    private void setupRecycler() {
        //Criando lista para teste
        commentBeans = new ArrayList<>();

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        commentAdapter = new CommentAdapter(commentBeans);
        mRecyclerView.setAdapter(commentAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.post_line));
        mRecyclerView.addItemDecoration(itemDecorator);

        ServiceCommentGET serviceCommentGET = new ServiceCommentGET();
        serviceCommentGET.toRecyclerView(API.commentsGET(), mRecyclerView, progressBar);

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
        setupRecycler();
    }

}
