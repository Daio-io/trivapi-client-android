package io.daio.trivapiclient;

import android.support.annotation.NonNull;

import java.util.HashMap;

public class TrivapiRequest {

    private HashMap<String, String> params;

    public TrivapiRequest() {
        this.params = new HashMap<>();
    }

    public TrivapiRequest withParam(@NonNull String key, @NonNull String value) {
        this.params.put(key, value);
        return this;
    }

    public HashMap<String, String> getParams() {
        return this.params;
    }


}
