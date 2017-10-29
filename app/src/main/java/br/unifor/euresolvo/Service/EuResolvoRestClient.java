package br.unifor.euresolvo.Service;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

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
        try {
            StringEntity entity = new StringEntity(object.toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            client.post(null, url, entity, "application/json", handler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void patch(String url, JSONObject object, AsyncHttpResponseHandler handler) {
        try {
            StringEntity entity = new StringEntity(object.toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            client.patch(null, url, entity, "application/json", handler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void delete(String url, AsyncHttpResponseHandler handler) {
        client.delete(url, handler);
    }

    private static String makeUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
