package br.unifor.euresolvo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import br.unifor.euresolvo.Adapter.CommentAdapter;
import br.unifor.euresolvo.DTO.PostDetailedDTO;
import br.unifor.euresolvo.Service.Callback;
import br.unifor.euresolvo.Service.Conversor;
import br.unifor.euresolvo.Service.PostService;

public class DetailPost extends MainActivity {

    TextView txtTitulo;
    TextView txtDescricao;
    RecyclerView mRecyclerView;
    private PostDetailedDTO post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.comment_recycler_view_list);
        setupRecycler();

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

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
        Long id = intent.getLongExtra("postId", 0);
        new PostService().getPostWithId(id, new Callback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    post = new Conversor().toPostDetailedDTO(result.getJSONObject(0));
                    txtTitulo.setText(post.getTitle());
                    txtDescricao.setText(post.getContent());
                    updateRecycler();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void setupRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.post_line));
        mRecyclerView.addItemDecoration(itemDecorator);
    }

    private void updateRecycler() {
        mRecyclerView.setAdapter(new CommentAdapter(post.getComments()));
    }

}
