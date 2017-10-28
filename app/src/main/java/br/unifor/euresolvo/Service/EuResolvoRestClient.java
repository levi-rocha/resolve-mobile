package br.unifor.euresolvo.Service;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class EuResolvoRestClient {
    private static final String BASE_URL = "https://resolve-rest.herokuapp.com/";

    private AsyncHttpClient client;

    public EuResolvoRestClient() {
        this.client = new AsyncHttpClient();
    }

    public void get(String url, RequestParams params, AsyncHttpResponseHandler handler) {
        client.get(makeUrl(url), params, handler);
    };

    private static String makeUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
