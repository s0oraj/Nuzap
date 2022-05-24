package blog.cosmos.home.nuzap;

import android.content.Context;
import android.text.Html;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.loader.content.Loader;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class does the actual background work of the app
 * It uses Volley library to fetch data from WordPress Blog in the Background thread
 * and returns an {@link ArrayList} list of {@link News} in the Main Ui thread.
 * Extends base {@link Loader} class to implement Loader operations
 **/

public class NewsLoader extends Loader<List<News>> {

    // String url of wordpress api from where ScienceGlass website is being fetched
    private final String mUrl;
    // Context reference of MainActivity of app
    private final Context mainActivityContext;
    // ArrayList of News which we want to return after JSON Parsing using Volley
    private  List<News> mNews;

    private JsonObjectRequest request;

    // Number of news to be shown in main UI Screen. Length of List<News> will depend on this variable.
    private int mNewsCount;


    /**
     * Constructor when NewsLoader object is first created
     * Called from onCreateLoader() callback in the MainActivity
     * @param context used to retrieve the application context.
     * @param url Url to get Wordpress API JSON of ScienceGlass blog
     * @param newsCount number of recent news user wants to see in the app
     */
    public NewsLoader(@NonNull Context context, String url, int newsCount) {
        super(context);
        //Initialize member variables
        mUrl = url;
        mainActivityContext= context;
        mNewsCount = newsCount;

        // Create a new arraylist of news
        mNews = new ArrayList<>();

    }

    /**
     * Called when the loader is first created and then started
     * **/
    @Override
    protected void onStartLoading() {

        // If list of news is not empty and already contains some data,
        // deliever result immediately to onLoadFinished() callback in MainActivity
        if(!mNews.isEmpty()){
            deliverResult(mNews);
        }
        else{
            // If list of news is empty, start fetching new JSON data from the WORDPRESS API
            forceLoad();
        }

    }

    /**
     * Triggered when forceload method is called in the onStartLoading() callback
     * **/
    @Override
    protected void onForceLoad() {
        // Start extracting json from Wordpress API using Volley in a background thread
      extractJson();
    }


    /**
     * Cancel JSON request once onStopLoading is called
     * **/
    @Override
    protected void onStopLoading() {
        request.cancel();
    }

    /**
     * Helper method to start extracting json from Wordpress API using Volley in a background thread
     * once the JSON data is fetched, onResponse() method is triggered in the main thread.
     * onResponse() method extracts the JSON data {@link JSONObject} into  List {@link ArrayList} of news {@link News}
     * and puts this List into delieverRequest() method when is then recieved by onLoadFinished() method in MainAcitivy {@link MainActivity}
     * **/
    public void extractJson(){

        //Use of volley to extract data
        RequestQueue queue = Volley.newRequestQueue(mainActivityContext);

        // Create a new JSON request
         request = new JsonObjectRequest
                (Request.Method.GET, mUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject websiteResponse) {

                        //On response start extracting JSONObject into a List of news
                        JSONArray response = null;
                        try {
                            response = websiteResponse.getJSONArray("articles");

                            // Toast message to show the length of array of response of JSONObject i.e websiteResponse variable
                            Toast.makeText(mainActivityContext, "length of responsearray "+response.length(), Toast.LENGTH_SHORT).show();

                            // If mNewsCount is larger than the total number of news ScienceGlass has, then
                            // put total number of news i.e response.length into mNewsCount value
                            if(mNewsCount >response.length()){

                                mNewsCount = response.length();
                            }

                            // JSON Parsing of response array
                            for(int i = 0; i < mNewsCount; i++){

                                News p = new News();

                                JSONObject jsonObjectData = response.getJSONObject(i);

                                p.setDate(jsonObjectData.getString("publishedAt"));

                                String unformattedTitle= jsonObjectData.getString("title");
                                String title = Html.fromHtml(unformattedTitle).toString();
                                p.setTitle(title);


                                p.setContent(jsonObjectData.getString("content"));


                                p.setDescription(jsonObjectData.getString("description"));


                                p.setUrl(jsonObjectData.getString("url"));



                                p.setFeature_image(jsonObjectData.getString("urlToImage"));
                                //   Log.d(TAG, "featured image "+p.getFeature_image());



                                mNews.add(p);


                            }


                        } catch (JSONException e) {
                            //Catch a json exception
                            e.printStackTrace();
                            Toast.makeText(mainActivityContext, "JsonException occured while parsing, check StackTrace",Toast.LENGTH_SHORT).show();
                        }

                          deliverResult(mNews);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(mainActivityContext, error.getMessage()+"error response ", Toast.LENGTH_SHORT).show();

                    }
                }
         ){

             /**
              * Passing some request headers
              * This block of code removes the following error found at Debug console.
              *  NetworkUtility.shouldRetryException: Unexpected response code 403
              */
             @Override
             public Map<String, String> getHeaders() throws AuthFailureError {
                 HashMap<String, String> headers = new HashMap<String, String>();
                 //headers.put("Content-Type", "application/json");

                 headers.put("User-Agent","Mozilla/5.0");
                 return headers;
             }


         }

         ;

         // add request to the queue
        queue.add(request);
    }


}
