package io.daio.trivapiclient;

import java.io.IOException;

public interface TrivapiHttpClient {

    interface OnFailureCallback {
        void onFailure(String url, IOException exception);
    }

    interface OnSuccessCallback {
        void onSuccess(String url, String resultBody);
    }

    void request(String url, OnFailureCallback onFailureCallback, OnSuccessCallback onSuccessCallback);

}
