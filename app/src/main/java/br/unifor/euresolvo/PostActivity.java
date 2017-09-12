package br.unifor.euresolvo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.unifor.euresolvo.Adapter.PostAdapter;
import br.unifor.euresolvo.Bean.PostBean;

public class PostActivity extends AppCompatActivity {

    ArrayList<PostBean> postBeans;
    ListView listView;
    private static PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        listView=(ListView)findViewById(R.id.post_list);

        postBeans = new ArrayList<>();

        postBeans.add(new PostBean("Lorem Ipsum", "Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI, quando um impressor desconhecido pegou uma bandeja de tipos e os embaralhou para fazer um livro de modelos de tipos."));
        postBeans.add(new PostBean("De onde ele vem?", "Ao contrário do que se acredita, Lorem Ipsum não é simplesmente um texto randômico."));
        postBeans.add(new PostBean("Nullam sed sagittis felis.","Pellentesque quis orci quis sem finibus suscipit. Nunc laoreet, ante a posuere scelerisque, nisi ex convallis augue, id dictum neque mauris vitae lectus. Nunc mauris felis, porta non congue et, varius at mauris. Praesent facilisis massa in dolor vehicula, sed aliquet massa cursus. Vestibulum quis elementum lectus. Sed dignissim ante eget porta molestie. Nunc at interdum orci. Phasellus molestie augue ut condimentum feugiat. Mauris quis augue ut nisl tempus finibus eget blandit justo. Nam porta dolor sit amet nisi consequat, a vehicula quam rutrum. Maecenas felis augue, auctor quis nisi eget, faucibus tristique lacus. Maecenas est risus, pretium vel volutpat vitae, venenatis non diam. Sed non feugiat massa. Mauris a est euismod, tincidunt leo ut, tempor nulla."));
        postBeans.add(new PostBean(" hagsduig?","gdkegfegfl skqdogqw kdbqowdgoef"));

        postAdapter = new PostAdapter(postBeans,getApplicationContext());

        listView.setAdapter(postAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                PostBean postBean = postBeans.get(position);

                Intent intent = new Intent(getApplicationContext(), PostDetailActivity.class);
                intent.putExtra("titulo", postBean.getTitulo());
                intent.putExtra("descricao", postBean.getDescricao());
                startActivity(intent);

//                Snackbar.make(view, dataModel.getTitulo()+"\n"+dataModel.getDescricao(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }


}
