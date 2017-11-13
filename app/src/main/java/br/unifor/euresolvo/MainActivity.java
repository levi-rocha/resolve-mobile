package br.unifor.euresolvo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import br.unifor.euresolvo.Adapter.PostsAdapter;
import br.unifor.euresolvo.DTO.PostSimpleDTO;
import br.unifor.euresolvo.Dao.UserDao;
import br.unifor.euresolvo.Service.Callback;
import br.unifor.euresolvo.Service.Conversor;
import br.unifor.euresolvo.Service.PostService;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final long SPLASH_TIME_OUT = 1000;
    private RecyclerView rv;
    public ProgressBar progressBar;

    private SharedPreferences.Editor prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewPostActivity.class));
            }
        });

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        carregarFoto();

        //Montando lista
        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        //Chamando o Service para buscar os posts e preencher lista
        loadPosts();
    }

    private void loadPosts() {
        new PostService().getPosts(20, 0, new Callback() {
            @Override
            public void onSuccess(JSONArray result) {
                progressBar.setVisibility(View.INVISIBLE);
                rv.setAdapter(new PostsAdapter(
                        new Conversor().toListOfPostSimpleDTO(result),
                        new PostsAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(PostSimpleDTO item) {
                                Intent intent = new Intent(MainActivity.this, PostDetailActivity.class);
                                intent.putExtra("postId", item.getId());
                                startActivity(intent);
                            }
                        }));
            }
            @Override
            public void onFailure(String errorResponse) {
                super.onFailure(errorResponse);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPosts();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadPosts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_my_posts) {
            startActivity(new Intent(getApplicationContext(), MyPosts.class));
        } else if (id == R.id.nav_reposts) {
            startActivity(new Intent(getApplicationContext(), Reports.class));
        } else if (id == R.id.nav_logoff) {
            //prefs.clear();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void carregarFoto() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UserDao userDao = new UserDao(getApplicationContext());
                TextView textViewName = (TextView) findViewById(R.id.textView_navUserName);
                TextView textViewEmail = (TextView) findViewById(R.id.textView_navUserEmail);
                ImageView imageViewPhoto = (ImageView) findViewById(R.id.imageView_navUserPhoto);
                textViewName.setText(userDao.consult().getPersonName());
                textViewEmail.setText(userDao.consult().getPersonEmail());
                if(userDao.consult().getPersonPhoto() != null) {
                    Picasso.with(getApplicationContext()).load(userDao.consult().getPersonPhoto()).resize(200, 180).centerCrop().into(imageViewPhoto);
                }
                userDao.close();
            }
        }, SPLASH_TIME_OUT);
    }

}