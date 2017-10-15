package br.unifor.euresolvo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.unifor.euresolvo.Dao.UserDao;
import br.unifor.euresolvo.Service.API;
import br.unifor.euresolvo.Service.ServicePostsGET;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final long SPLASH_TIME_OUT = 1000;
    private RecyclerView rv;
    public ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        carregarFoto();


        //Montando lista
        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        //Chamando o Service para buscar os dados e preencher lista
        ServicePostsGET servicePostsGET =new ServicePostsGET();
        servicePostsGET.toRecyclerView(API.postsGET(), rv, progressBar);

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_posts) {
            startActivity(new Intent(getApplicationContext(), MyPosts.class));
        } else if (id == R.id.nav_reposts) {
            startActivity(new Intent(getApplicationContext(), Reposts.class));
        /*
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        */
        } else if (id == R.id.nav_logoff) {
            Bundle parametros = new Bundle();
            parametros.putBoolean("logout", true);
            new UserDao(getApplicationContext()).reset();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.putExtras(parametros);
            startActivity(intent);
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
                Picasso.with(getApplicationContext()).load(userDao.consult().getPersonPhoto()).resize(200, 180).centerCrop().into(imageViewPhoto);
                userDao.close();
            }
        }, SPLASH_TIME_OUT);
    }

}