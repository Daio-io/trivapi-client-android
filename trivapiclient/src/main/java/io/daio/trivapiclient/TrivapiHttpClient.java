package io.daio.trivapiclient;

import java.io.IOException;

public interface TrivapiHttpClient {

    interface OnFailureCallback {
        void onFailure(IOException exception);
    }

    interface  OnSuccessCallback {
        void onSuccess(String resultBody);
    }

    void request(String url, OnFailureCallback onFailureCallback, OnSuccessCallback onSuccessCallback);

}
