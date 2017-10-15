package br.unifor.euresolvo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import br.unifor.euresolvo.Adapter.PostAdapter;
import br.unifor.euresolvo.Bean.PostBeanOLD;

public class MyPosts extends MainActivity {

    ArrayList<PostBeanOLD> postBeanOLDs;
    private static PostAdapter postAdapter;

    RecyclerView mRecyclerView;

    private void setupRecycler() {
        //Criando lista para teste
        postBeanOLDs = new ArrayList<>();

        postBeanOLDs.add(new PostBeanOLD(
                "Lorem Ipsum", "Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos.",
                "Joao", 10));
        postBeanOLDs.add(new PostBeanOLD(
                "Lorem Ipsum 2", "Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos.",
                "Antonio", 15));
        postBeanOLDs.add(new PostBeanOLD(
                "Lorem Ipsum 3", "Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos.",
                "José", 25));

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        postAdapter = new PostAdapter(postBeanOLDs);
        mRecyclerView.setAdapter(postAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.post_line));
        mRecyclerView.addItemDecoration(itemDecorator);

        //mRecyclerView.addItemDecoration(
          //      new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);
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

        mRecyclerView = (RecyclerView) findViewById(R.id.post_recycler_view_list);
        setupRecycler();
    }



}
