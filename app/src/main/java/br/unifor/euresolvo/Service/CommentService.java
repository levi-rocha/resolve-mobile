package br.unifor.euresolvo.Service;

import org.json.JSONException;
import org.json.JSONObject;

import br.unifor.euresolvo.Models.Comment;

public class CommentService {

    private EuResolvoRestClient client;

    public CommentService() {
        this.client= new EuResolvoRestClient();
    }

    public void getComments(int pageSize, int pageNumber, Callback callback) {
        String request = "comments?page=" + pageNumber + "&size=" + pageSize;
        client.get(request, callback);
    }

    public void getCommentWithId(long id, Callback callback) {
        String request = "comments/" + id;
        client.get(request, callback);
    }

    public void insertComment(Comment comment, Callback callback) {
        client.post("comments", commentCreate(comment), callback);
    }

    public void updateComment(Comment comment, Callback callback) {
        String request = "comments/" + comment.getId();
        client.patch(request, commentUpdate(comment), callback);
    }

    public void deleteComment(long id, Callback callback) {
        client.delete("comments/" + id, callback);
    }

    private JSONObject commentCreate(Comment comment) {
        JSONObject entity = new JSONObject();
        try {
            entity.put("content", comment.getContent());
            JSONObject author = new JSONObject();
            author.put("id", comment.getAuthor().getId());
            entity.put("author", author);
            JSONObject post = new JSONObject();
            post.put("id", comment.getPost().getId());
            entity.put("post", post);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entity;
    }

    private JSONObject commentUpdate(Comment comment) {
        JSONObject entity = new JSONObject();
        try {
            entity.put("content", comment.getContent());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
