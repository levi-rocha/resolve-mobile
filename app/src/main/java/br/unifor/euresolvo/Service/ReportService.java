package br.unifor.euresolvo.Service;

import org.json.JSONException;
import org.json.JSONObject;

import br.unifor.euresolvo.Models.Report;

public class ReportService {

    private EuResolvoRestClient client;

    public ReportService() {
        this.client= new EuResolvoRestClient();
    }

    public void getReports(int pageSize, int pageNumber, Callback callback) {
        String request = "reports?page=" + pageNumber + "&size=" + pageSize;
        client.get(request, callback);
    }

    public void getReportWithId(long id, Callback callback) {
        String request = "reports/" + id;
        client.get(request, callback);
    }

    public void insertReport(Report report, Callback callback) {
        client.post("reports", reportCreate(report), callback);
    }

    public void updateReport(Report report, Callback callback) {
        String request = "reports/" + report.getId();
        client.patch(request, reportUpdate(report), callback);
    }

    public void deleteReport(long id, Callback callback) {
        client.delete("reports/" + id, callback);
    }

    private JSONObject reportCreate(Report report) {
        JSONObject entity = new JSONObject();
        try {
            entity.put("description", report.getDescription());
            JSONObject author = new JSONObject();
            author.put("id", report.getAuthor().getId());
            entity.put("author", author);
            JSONObject post = new JSONObject();
            post.put("id", report.getPost().getId());
            entity.put("post", post);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entity;
    }

    private JSONObject reportUpdate(Report report) {
        JSONObject entity = new JSONObject();
        try {
            entity.put("description", report.getDescription());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
