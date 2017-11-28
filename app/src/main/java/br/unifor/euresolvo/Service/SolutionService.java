package br.unifor.euresolvo.Service;

import org.json.JSONException;
import org.json.JSONObject;

import br.unifor.euresolvo.Models.Solution;

public class SolutionService {

    private EuResolvoRestClient client;

    public SolutionService() {
        this.client= new EuResolvoRestClient();
    }

    public void getSolutions(int pageSize, int pageNumber, Callback callback) {
        String request = "solutions?page=" + pageNumber + "&size=" + pageSize;
        client.get(request, callback);
    }

    public void getSolutionWithId(long id, Callback callback) {
        String request = "solutions/" + id;
        client.get(request, callback);
    }

    public void insertSolution(Solution solution, Callback callback) {
        client.post("solutions", solutionCreate(solution), callback);
    }

    public void updateSolution(Solution solution, Callback callback) {
        String request = "solutions/" + solution.getId();
        client.patch(request, solutionUpdate(solution), callback);
    }

    public void deleteSolution(long id, Callback callback) {
        client.delete("solutions/" + id, callback);
    }

    private JSONObject solutionCreate(Solution solution) {
        JSONObject entity = new JSONObject();
        try {
            entity.put("content", solution.getContent());
            JSONObject author = new JSONObject();
            author.put("id", solution.getAuthor().getId());
            entity.put("author", author);
            JSONObject post = new JSONObject();
            post.put("id", solution.getPost().getId());
            entity.put("post", post);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entity;
    }

    private JSONObject solutionUpdate(Solution solution) {
        JSONObject entity = new JSONObject();
        try {
            entity.put("content", solution.getContent());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
