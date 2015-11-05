package io.daio.trivapiclient;

import java.util.HashMap;

public final class TrivapiUrlBuilder {

    public String build(String baseUrl, TrivapiRequest trivapiRequest) {

        HashMap<String, String> requestParams = trivapiRequest.getParams();

        String builtParams = "";

        for (String paramKey : requestParams.keySet()) {

            String param = requestParams.get(paramKey);
            if (builtParams.isEmpty()) {
                builtParams += "?" + paramKey + "=" + param;
            } else {
                builtParams += "&" + paramKey + "=" + param;
            }
        }
        return baseUrl + builtParams;
    }

    public String build(String baseUrl, TrivapiCategory category, TrivapiRequest trivapiRequest) {

        String categoryBaseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";

        categoryBaseUrl += stringFromCategory(category);

        return build(categoryBaseUrl, trivapiRequest);
    }

    private String stringFromCategory(TrivapiCategory category) {
        switch (category){
            case SCIENCE:
                return "science";
            case MATHS:
                return "maths";
            default:
                return "general";
        }
    }

}
