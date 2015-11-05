package io.daio.trivapiclient;

public interface OnFailureListener {

    void onFailure(String url, TrivapiException exception);

}
