package io.daio.trivapiclient;

import junit.framework.TestCase;

public class TrivapiRequestTest extends TestCase {

    public void testGetParams() throws Exception {

        TrivapiRequest trivapiRequest = new TrivapiRequest();

        trivapiRequest.withParam("some_key", "some_param");
        trivapiRequest.withParam("my_param", "me");
        trivapiRequest.withParam("another", "yey");

        assertTrue(trivapiRequest.getParams().size() == 3);

    }
}