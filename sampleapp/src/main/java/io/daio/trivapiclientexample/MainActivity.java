package io.daio.trivapiclientexample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import io.daio.trivapiclient.OnFailureListener;
import io.daio.trivapiclient.OnSuccessListener;
import io.daio.trivapiclient.TrivapiCategory;
import io.daio.trivapiclient.TrivapiClient;
import io.daio.trivapiclient.TrivapiException;
import io.daio.trivapiclient.TrivapiRequest;
import io.daio.trivapiclient.TrivapiResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TrivapiClient client = new TrivapiClient("", new ExampleHTTPClient(), "http://trivapi.herokuapp.com/");

        TrivapiRequest request = new TrivapiRequest();
        request.withParam("amount", "2");

        client.makeRequest(TrivapiCategory.GENERAL, request, new OnSuccessListener() {
            @Override
            public void onSuccess(String url, @NonNull List<TrivapiResult> results) {
                for (TrivapiResult result : results) {
                    System.out.println(result.getQuestion());
                }
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(String url, TrivapiException exception) {
                System.out.println("I failed");
            }
        });

    }

}

