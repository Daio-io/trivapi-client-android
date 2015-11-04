package io.daio.trivapiclient;

import junit.framework.TestCase;

public class TrivapiUrlBuilderTest extends TestCase {

    private static final String BASE_URL = "http://trivapi.herokuapp.com/randomise";

    public void testBuildingUrlStringFromRequestWithParams() throws Exception {

        TrivapiRequest request = new TrivapiRequest();
        request.withParam("difficulty", "1");
        request.withParam("amount", "1");

        TrivapiUrlBuilder builder = new TrivapiUrlBuilder();

        String result = builder.build(BASE_URL, request);

        String expected = BASE_URL + "?difficulty=1&amount=1";

        assertEquals(expected, result);

    }

    public void testBuildingUrlStringFromRequestWithNoParams() throws Exception {

        TrivapiRequest request = new TrivapiRequest();

        TrivapiUrlBuilder builder = new TrivapiUrlBuilder();

        String result = builder.build(BASE_URL, request);

        assertEquals(BASE_URL, result);

    }
}