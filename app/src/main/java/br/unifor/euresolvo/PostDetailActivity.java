package br.unifor.euresolvo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import br.unifor.euresolvo.Adapter.CommentAdapter;
import br.unifor.euresolvo.Adapter.SolutionAdapter;
import br.unifor.euresolvo.DTO.PostDetailedDTO;
import br.unifor.euresolvo.DTO.VoteDTO;
import br.unifor.euresolvo.Models.Comment;
import br.unifor.euresolvo.Models.Post;
import br.unifor.euresolvo.Models.User;
import br.unifor.euresolvo.Service.Callback;
import br.unifor.euresolvo.Service.CommentService;
import br.unifor.euresolvo.Service.Conversor;
import br.unifor.euresolvo.Service.PostService;

public class PostDetailActivity extends HomeActivity {

    private TextView txtTitulo, txtDescricao, txtVotos;
    private RecyclerView mCommentsRV, mSolutionsRV;
    private PostDetailedDTO post;
    private ProgressDialog mProgressDialog;
    private ImageButton btnVotar;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefs = getApplicationContext().getSharedPreferences("loginPref", MODE_PRIVATE);

        initCommentList();
        initSolutionList();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addCommentSolution);
        fab.setImageResource(R.drawable.ic_add_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCommentDialog(view);
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
        txtVotos = (TextView) findViewById(R.id.txtVotos);
        btnVotar = (ImageButton) findViewById(R.id.btnVotar);
        btnVotar.setEnabled(false);
        btnVotar.setVisibility(View.GONE);
        btnVotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (!post.getVoteIds().contains(prefs.getLong("userId", 0))) {
                    VoteDTO vote = new VoteDTO();
                    vote.setUserId(prefs.getLong("userId", 0));
                    vote.setPostId(post.getId());
                    new PostService().insertVote(vote, new Callback() {
                        @Override
                        public void onSuccess(JSONArray result) {
                            hideProgressDialog();
                            Snackbar.make(view, R.string.voteSuccess,
                                    Snackbar.LENGTH_LONG).show();
                            btnVotar.setEnabled(false);
                            btnVotar.setVisibility(View.GONE);
                            loadPost();
                        }

                        @Override
                        public void onFailure(String errorResponse) {
                            hideProgressDialog();
                            Log.d("AAA", errorResponse);
                            Snackbar.make(view, R.string.voteFailure,
                                    Snackbar.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        loadPost();
    }

    private void showCommentDialog(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.novoComentario);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String content = input.getText().toString();
                Comment comment = new Comment();
                Post onPost = new Post();
                onPost.setId(post.getId());
                comment.setPost(onPost);
                User byAuthor = new User();
                byAuthor.setId(prefs.getLong("userId", 0));
                comment.setAuthor(byAuthor);
                comment.setContent(content);
                showProgressDialog();
                new CommentService().insertComment(comment, new Callback() {
                    @Override
                    public void onSuccess(JSONArray result) {
                        hideProgressDialog();
                        Snackbar.make(view, R.string.commentSuccess,
                                Snackbar.LENGTH_LONG).show();
                        loadPost();
                    }

                    @Override
                    public void onFailure(String errorResponse) {
                        hideProgressDialog();
                        Snackbar.make(view, R.string.commentFailure,
                                Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.savingComment));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void loadPost() {
        Long id = getIntent().getLongExtra("postId", 0);
        new PostService().getPostWithId(id, new Callback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    post = new Conversor().toPostDetailedDTO(result.getJSONObject(0));
                    txtTitulo.setText(post.getTitle());
                    txtDescricao.setText(post.getContent());
                    txtVotos.setText(post.getVoteIds().size() + " usuários têm este problema");
                    if (!post.getVoteIds().contains(prefs.getLong("userId", 0))) {
                        btnVotar.setEnabled(true);
                        btnVotar.setVisibility(View.VISIBLE);
                    } else {
                        btnVotar.setEnabled(false);
                        btnVotar.setVisibility(View.GONE);
                    }
                    updateCommentList();
                    updateSolutionList();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initSolutionList() {
        mSolutionsRV = (RecyclerView) findViewById(R.id.solutionList);
        mSolutionsRV.setLayoutManager(new LinearLayoutManager(this));
        mSolutionsRV.addItemDecoration(postLine());
    }

    private void initCommentList() {
        mCommentsRV = (RecyclerView) findViewById(R.id.comment_recycler_view_list);
        mCommentsRV.setLayoutManager(new LinearLayoutManager(this));
        mCommentsRV.addItemDecoration(postLine());
    }

    private DividerItemDecoration postLine() {
        DividerItemDecoration itemDecorator =
                new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(
                ContextCompat.getDrawable(getApplicationContext(), R.drawable.post_line));
        return itemDecorator;
    }

    private void updateSolutionList() {
        mSolutionsRV.setAdapter(new SolutionAdapter(post.getSolutions()));
    }

    private void updateCommentList() {
        mCommentsRV.setAdapter(new CommentAdapter(post.getComments()));
    }

}
