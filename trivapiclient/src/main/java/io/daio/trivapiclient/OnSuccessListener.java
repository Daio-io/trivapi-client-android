package io.daio.trivapiclient;

import android.support.annotation.NonNull;

import java.util.List;

public interface OnSuccessListener {

    void onSuccess(@NonNull List<TrivapiResult> results);

}
