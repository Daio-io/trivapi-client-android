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

}
