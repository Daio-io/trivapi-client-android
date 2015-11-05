package io.daio.trivapiclient;

import android.support.annotation.NonNull;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

public class TrivapiClientTest extends TestCase {

    public void testMakeRandomRequestSuccess() throws Exception {

        TrivapiClient trivapiClient = new TrivapiClient("", new MockHttpClientGoodResponse(), "");

        final boolean[] called = {false};
        trivapiClient.makeRequest(new TrivapiRequest(), new OnSuccessListener() {
            @Override
            public void onSuccess(@NonNull List<TrivapiResult> results) {
                called[0] = true;
            }
        }, null);

        assertTrue(called[0]);

    }

    public void testMakeRandomRequestFailure() throws Exception {

        TrivapiClient trivapiClient = new TrivapiClient("", new MockHttpClientFailedResponse(), "");

        final boolean[] called = {false};
        trivapiClient.makeRequest(new TrivapiRequest(), null, new OnFailureListener() {
            @Override
            public void onFailure(TrivapiException exception) {
                called[0] = true;
            }
        });

        assertTrue(called[0]);

    }

    public void testMakeCategoryRequestSuccess() throws Exception {

        TrivapiClient trivapiClient = new TrivapiClient("", new MockHttpClientGoodResponse(), "");

        final boolean[] called = {false};
        trivapiClient.makeRequest(TrivapiCategory.GENERAL, new TrivapiRequest(), new OnSuccessListener() {
            @Override
            public void onSuccess(@NonNull List<TrivapiResult> results) {
                called[0] = true;
            }
        }, null);

        assertTrue(called[0]);

    }

    public void testMakeCategoryRequestFailure() throws Exception {

        TrivapiClient trivapiClient = new TrivapiClient("", new MockHttpClientFailedResponse(), "");

        final boolean[] called = {false};
        trivapiClient.makeRequest(TrivapiCategory.GENERAL, new TrivapiRequest(), null, new OnFailureListener() {
            @Override
            public void onFailure(TrivapiException exception) {
                called[0] = true;
            }
        });

        assertTrue(called[0]);

    }

    private class MockHttpClientGoodResponse implements TrivapiHttpClient {

        @Override
        public void request(String url, OnFailureCallback onFailureCallback, OnSuccessCallback onSuccessCallback) {

            onSuccessCallback.onSuccess("");

        }
    }

    private class MockHttpClientFailedResponse implements TrivapiHttpClient {

        @Override
        public void request(String url, OnFailureCallback onFailureCallback, OnSuccessCallback onSuccessCallback) {

            onFailureCallback.onFailure(new IOException());

        }
    }
}