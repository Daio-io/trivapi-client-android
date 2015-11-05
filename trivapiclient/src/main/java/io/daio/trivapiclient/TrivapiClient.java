package io.daio.trivapiclient;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class TrivapiClient {

    public static final String API_KEY_PARAM = "apikey";
    public static final String SEARCH_RANDOM = "randomise";
    public static final String SEARCH_CATEGORY = "category";

    private static final String DEFAULT_BASE_URL = "http://trivapi.herokuapp.com/";

    private String apiKey;
    private String baseUrl;
    private TrivapiHttpClient httpClient;
    private TrivapiUrlBuilder urlBuilder = new TrivapiUrlBuilder();
    private final ResponseTransformer responseTransformer = new ResponseTransformer();

    public TrivapiClient(@NonNull String apiKey, @NonNull TrivapiHttpClient client, @Nullable String baseUrl) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl != null ? baseUrl : DEFAULT_BASE_URL;
        this.httpClient = client;
    }

    public void makeRequest(@NonNull TrivapiRequest request,
                                  final OnSuccessListener successListener,
                                  final OnFailureListener failureListener) {

        request.withParam(API_KEY_PARAM, apiKey);
        String urlString = urlBuilder.build(this.baseUrl + SEARCH_RANDOM, request);

        execute(urlString, successListener, failureListener);

    }

    public void makeRequest(TrivapiCategory category, TrivapiRequest request,
                            OnSuccessListener successListener,
                            OnFailureListener failureListener) {

        request.withParam(API_KEY_PARAM, apiKey);
        String urlString = urlBuilder.build(this.baseUrl + SEARCH_CATEGORY, category, request);

        execute(urlString, successListener, failureListener);

    }

    private void execute(String urlString, final OnSuccessListener successListener, final OnFailureListener failureListener) {

        this.httpClient.request(urlString, new TrivapiHttpClient.OnFailureCallback() {

            @Override
            public void onFailure(IOException ioException) {
                if (failureListener != null) {
                    failureListener.onFailure(new TrivapiException(ioException.getMessage(), ioException));
                }
            }

        }, new TrivapiHttpClient.OnSuccessCallback() {

            @Override
            public void onSuccess(String resultBody) {
                if (successListener != null) {
                    try {
                        List<TrivapiResult> results = responseTransformer.transform(resultBody);
                        successListener.onSuccess(results);
                    } catch (JSONException e) {
                        failureListener.onFailure(new TrivapiException(e.getMessage(), e));
                    }
                }
            }
        });
    }

}
