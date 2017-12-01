package br.unifor.euresolvo.Service;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class EuResolvoRestClient {
    private static final String BASE_URL = "https://resolve-rest.herokuapp.com/";

    private AsyncHttpClient client;

    public EuResolvoRestClient() {
        this.client = new AsyncHttpClient();
    }

    public void get(String url, AsyncHttpResponseHandler handler) {
        client.get(makeUrl(url), handler);
    }

    public void post(String url, JSONObject object, AsyncHttpResponseHandler handler) {
        StringEntity entity = new StringEntity(object.toString(), "UTF-8");
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        client.post(null, makeUrl(url), entity, "application/json", handler);
    }

    public void patch(String url, JSONObject object, AsyncHttpResponseHandler handler) {
        StringEntity entity = new StringEntity(object.toString(), "UTF-8");
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        client.patch(null, makeUrl(url), entity, "application/json", handler);
    }

    public void delete(String url, AsyncHttpResponseHandler handler) {
        client.delete(makeUrl(url), handler);
    }

    private static String makeUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
