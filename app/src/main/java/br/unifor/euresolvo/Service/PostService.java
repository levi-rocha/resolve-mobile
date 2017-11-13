package br.unifor.euresolvo.Service;

import org.json.JSONException;
import org.json.JSONObject;

import br.unifor.euresolvo.DTO.VoteDTO;
import br.unifor.euresolvo.Models.Post;

public class PostService {

    private EuResolvoRestClient client;

    public PostService() {
        this.client = new EuResolvoRestClient();
    }

    public void getPosts(int pageSize, int pageNumber, Callback callback) {
        String request = "posts?page=" + pageNumber + "&size=" + pageSize +
                "&sort=date";
        client.get(request, callback);
    }

    public void getPostsSortedByVotes(int pageSize, int pageNumber,
                                      Callback callback) {
        String request = "posts?page=" + pageNumber + "&size=" + pageSize +
                "&sort=votes";
        client.get(request, callback);
    }

    public void getPostWithId(long id, Callback callback) {
        String request = "posts/" + id;
        client.get(request, callback);
    }

    public void getPostsByAuthor(long authorId, Callback callback) {
        String request = "posts/byAuthor/" + authorId;
        client.get(request, callback);
    }

    public void insertPost(Post post, Callback callback) {
            client.post("posts", postCreate(post), callback);
    }

    public void insertVote(VoteDTO vote, Callback callback) {
        client.post("posts/vote", voteCreate(vote), callback);
    }

    public void updatePost(Post post, Callback callback) {
        String request = "posts/" + post.getId();
        client.patch(request, postUpdate(post), callback);
    }

    public void deletePost(long id, Callback callback) {
        String request = "posts/" + id;
        client.delete(request, callback);
    }

    private JSONObject postCreate(Post post) {
        JSONObject entity = new JSONObject();
        try {
            entity.put("title", post.getTitle());
            entity.put("content", post.getContent());
            JSONObject author = new JSONObject();
            author.put("id", post.getAuthor().getId());
            entity.put("author", author);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entity;
    }

    private JSONObject postUpdate(Post post) {
        JSONObject entity = new JSONObject();
        try {
            entity.put("title", post.getTitle());
            entity.put("content", post.getContent());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entity;
    }

    private JSONObject voteCreate(VoteDTO voteDTO) {
        JSONObject entity = new JSONObject();
        try {
            entity.put("postId", voteDTO.getPostId());
            entity.put("userId", voteDTO.getUserId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
