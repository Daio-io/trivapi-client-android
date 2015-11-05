package io.daio.trivapiclient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class ResponseTransformer {

    public List<TrivapiResult> transform(String response) throws JSONException {

        ArrayList<TrivapiResult> results = new ArrayList<>();

        JSONObject responseObj = new JSONObject(response);

        if (responseObj != null) {
            return results;
        }
        JSONArray jsonArray = responseObj.getJSONArray("response");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                String question = object.getString("question");
                String answer = object.getString("answer");
                TrivapiCategory category = getCategoryForStringValue(object.getString("category"));
                TrivapiDifficulty difficulty = getDifficultyForStringValue(object.getString("difficulty"));
                ArrayList<String> options = buildOptionsFromResponseArray(object.getJSONArray("options"));

                TrivapiResult result = new TrivapiResult(question, answer, options, category, difficulty);

                results.add(result);
            }

        return results;

    }

    private ArrayList<String> buildOptionsFromResponseArray(JSONArray responseArray) throws JSONException {
        ArrayList<String> options = new ArrayList<>();
        for (int i = 0; i < responseArray.length(); i++) {
            options.add(responseArray.getString(i));
        }
        return options;
    }

    private TrivapiCategory getCategoryForStringValue(String category){

        switch (category) {
            case "science":
                return TrivapiCategory.SCIENCE;
            case "maths":
                return TrivapiCategory.MATHS;
            default:
                return TrivapiCategory.GENERAL;
        }
    }

    private TrivapiDifficulty getDifficultyForStringValue(String difficulty){
        switch (difficulty) {
            case "2":
                return TrivapiDifficulty.MEDIUM;
            case "3":
                return TrivapiDifficulty.HARD;
            default:
                return TrivapiDifficulty.EASY;
        }
    }

}
