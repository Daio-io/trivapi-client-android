package io.daio.trivapiclientexample;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import io.daio.trivapiclient.OnFailureListener;
import io.daio.trivapiclient.OnSuccessListener;
import io.daio.trivapiclient.TrivapiCategory;
import io.daio.trivapiclient.TrivapiClient;
import io.daio.trivapiclient.TrivapiException;
import io.daio.trivapiclient.TrivapiRequest;
import io.daio.trivapiclient.TrivapiResult;

public class MainActivity extends AppCompatActivity {


    private Button option1;
    private Button option2;
    private Button option3;
    private Button option4;
    private TextView resText, quesText;

    private TrivapiResult currentResult;

    private TrivapiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new TrivapiClient("", new ExampleHTTPClient(), "http://trivapi.herokuapp.com/");

        setUpViews();
        makeRequest();
    }

    public void setUpViews() {
        option1 = (Button)findViewById(R.id.option1);
        option2 = (Button)findViewById(R.id.option2);
        option3 = (Button)findViewById(R.id.option3);
        option4 = (Button)findViewById(R.id.option4);
        Button getButton = (Button) findViewById(R.id.getButton);

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
            }
        });

        resText = (TextView)findViewById(R.id.responseText);
        quesText = (TextView)findViewById(R.id.questionText);

        option1.setOnClickListener(new OnClicky());
        option2.setOnClickListener(new OnClicky());
        option3.setOnClickListener(new OnClicky());
        option4.setOnClickListener(new OnClicky());
    }

    public void enableButtons(boolean enable) {
        option1.setEnabled(enable);
        option2.setEnabled(enable);
        option3.setEnabled(enable);
        option4.setEnabled(enable);
    }

    public void checkAnswer(String guess) {

        if (guess.equals(currentResult.getAnswer())) {
            Snackbar.make(findViewById(android.R.id.content), "Correct!", Snackbar.LENGTH_LONG)
                    .show();
            makeRequest();
        }
        resText.setText("Incorrect!");
    }


    // Set question results text to buttons and text views
    public void set() {
        option1.setText(currentResult.getOptions().get(0));
        option2.setText(currentResult.getOptions().get(1));
        option3.setText(currentResult.getOptions().get(2));
        option4.setText(currentResult.getOptions().get(3));
        quesText.setText(currentResult.getQuestion() + "?");

        resText.setText("");
        enableButtons(true);
    }

    public void makeRequest() {
        TrivapiRequest request = new TrivapiRequest();
        request.withParam("amount", "1");

        enableButtons(false);

        client.makeRequest(request, new OnSuccessListener() {
            @Override
            public void onSuccess(String url, @NonNull List<TrivapiResult> results) {
                currentResult = results.get(0);
                set();

            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(String url, TrivapiException exception) {
                Snackbar.make(findViewById(android.R.id.content), "Failed loading question!", Snackbar.LENGTH_LONG)
                        .show();
            }
        });
    }

    private class OnClicky implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            checkAnswer(b.getText().toString());
        }
    }

}

