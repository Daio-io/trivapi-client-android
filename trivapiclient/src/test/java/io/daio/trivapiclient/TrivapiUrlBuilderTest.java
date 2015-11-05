package io.daio.trivapiclient;

import junit.framework.TestCase;

public class TrivapiUrlBuilderTest extends TestCase {

    private static final String RANDOM_BASE_URL = "http://trivapi.herokuapp.com/randomise";
    private static final String CATEGORY_BASE_URL = "http://trivapi.herokuapp.com/category/";

    public void testBuildingUrlStringFromRequestWithParams() throws Exception {

        TrivapiRequest request = new TrivapiRequest();
        request.withParam("difficulty", "1");
        request.withParam("amount", "1");

        TrivapiUrlBuilder builder = new TrivapiUrlBuilder();

        String result = builder.build(RANDOM_BASE_URL, request);

        String expected = RANDOM_BASE_URL + "?difficulty=1&amount=1";

        assertEquals(expected, result);

    }

    public void testBuildingUrlStringFromRequestWithNoParams() throws Exception {

        TrivapiRequest request = new TrivapiRequest();

        TrivapiUrlBuilder builder = new TrivapiUrlBuilder();

        String result = builder.build(RANDOM_BASE_URL, request);

        assertEquals(RANDOM_BASE_URL, result);

    }

    public void testBuildingUrlStringFromRequestForGeneralCategory() throws Exception {

        TrivapiRequest request = new TrivapiRequest();

        TrivapiUrlBuilder builder = new TrivapiUrlBuilder();

        String result = builder.build(CATEGORY_BASE_URL, TrivapiCategory.GENERAL, request);

        assertEquals(CATEGORY_BASE_URL + "general", result);

    }

    public void testBuildingUrlStringFromRequestForScienceCategory() throws Exception {

        TrivapiRequest request = new TrivapiRequest();

        TrivapiUrlBuilder builder = new TrivapiUrlBuilder();

        String result = builder.build(CATEGORY_BASE_URL, TrivapiCategory.SCIENCE, request);

        assertEquals(CATEGORY_BASE_URL + "science", result);

    }

    public void testBuildingUrlStringFromRequestForMathsCategory() throws Exception {

        TrivapiRequest request = new TrivapiRequest();

        TrivapiUrlBuilder builder = new TrivapiUrlBuilder();

        String result = builder.build(CATEGORY_BASE_URL, TrivapiCategory.MATHS, request);

        assertEquals(CATEGORY_BASE_URL + "maths", result);

    }

    public void testUrlStringForCategoryAppendsSlashWhenNoForwardSlash() throws Exception {

        TrivapiRequest request = new TrivapiRequest();

        TrivapiUrlBuilder builder = new TrivapiUrlBuilder();

        String baseUrlNoSlash = CATEGORY_BASE_URL.substring(0, CATEGORY_BASE_URL.length() -1);

        String result = builder.build(baseUrlNoSlash, TrivapiCategory.MATHS, request);

        assertEquals(baseUrlNoSlash + "/" + "maths", result);

    }
}