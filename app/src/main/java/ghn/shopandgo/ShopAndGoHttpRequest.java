package ghn.shopandgo;

/**
 * Created by bukbukbukh on 12/15/16.
 */

import com.loopj.android.http.*;

public class ShopAndGoHttpRequest {

    private static final String BASE_URL = "http://shopandgo.herokuapp.com/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler resHandler) {
        client.get(mergeURL(url), params, resHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler resHandler) {
        client.post(mergeURL(url), params, resHandler);
    }

    private static String mergeURL(String path) {
        return BASE_URL + path;
    }

}
