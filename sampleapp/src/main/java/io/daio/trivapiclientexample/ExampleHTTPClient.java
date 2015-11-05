package io.daio.trivapiclientexample;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import io.daio.trivapiclient.TrivapiHttpClient;


public class ExampleHTTPClient implements TrivapiHttpClient {
    OkHttpClient ok = new OkHttpClient();

    @Override
    public void request(final String url,
                        final OnFailureCallback onFailureCallback,
                        final OnSuccessCallback onSuccessCallback) {

        final Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = ok.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                onFailureCallback.onFailure(url, e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                System.out.println(request);
                onSuccessCallback.onSuccess(url, response.body().string());
            }
        });

    }
}