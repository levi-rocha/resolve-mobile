package br.unifor.euresolvo;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;

import br.unifor.euresolvo.Models.Post;
import br.unifor.euresolvo.Models.User;
import br.unifor.euresolvo.Service.Callback;
import br.unifor.euresolvo.Service.PostService;

public class NewPostActivity extends AppCompatActivity {

    private EditText postTitleTxt, postContentTxt;

    private ProgressDialog mProgressDialog;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        prefs = getApplicationContext().getSharedPreferences("loginPref", MODE_PRIVATE);

        postTitleTxt = (EditText) findViewById(R.id.postTitleTxt);
        postContentTxt = (EditText) findViewById(R.id.postContentTxt);
        Button enviarBtn = (Button) findViewById(R.id.enviarBtn);
        enviarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarPost(v);
            }
        });
    }

    private void salvarPost(final View v) {
        Post post = new Post();
        post.setTitle(postTitleTxt.getText().toString());
        post.setContent(postContentTxt.getText().toString());
        User author = new User();
        author.setId(prefs.getLong("userId", 0));
        post.setAuthor(author);
        showProgressDialog();
        new PostService().insertPost(post, new Callback() {
            @Override
            public void onSuccess(JSONArray result) {
                hideProgressDialog();
                Snackbar.make(v, R.string.postSuccess, Snackbar.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(String errorResponse) {
                hideProgressDialog();
                Snackbar.make(v, R.string.postFailure, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

}
