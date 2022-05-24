package blog.cosmos.home.nuzap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * This class shows detailed information about a news when a news is clicked
 * from the main ui of screen i.e MainActivity {@link blog.cosmos.home.nuzap.MainActivity}
 * **/

public class DetailsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Enables back button so users can get back to previous activity i.e MainAcitvity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Receive intent
        Intent data = getIntent();

        // Receive title from the specific news user clicked in order to enter Details activity
        String title = data.getStringExtra("title");
        // Set ActionBar title as the news title, instead of the default title "ScienceGlass"
        getSupportActionBar().setTitle(title);

        // Set title of the news received from intent as the title in the details activity
        TextView detailsTitle = findViewById(R.id.details_title);
        detailsTitle.setText(title);

        String subtitle = data.getStringExtra("description");
        TextView detailsSubtitle = findViewById(R.id.details_subtitle);
        detailsSubtitle.setText(subtitle);

        String url = data.getStringExtra("news_url");
        Button urlBotton =  findViewById(R.id.url_button);
        urlBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsUri = Uri.parse(url);

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);
            }
        });




        // Receive content from intent and load content inside a WebView using loadDataWithBaseUrl method
        String pas = "</body></html>";
        String content = getHtmlFromAsset()+ data.getStringExtra("content")+pas;
        final String mimeType = "text/html";
        final String encoding = "UTF-8";
        WebView contentView = findViewById(R.id.content_view);
        contentView.getSettings().setJavaScriptEnabled(true);
        contentView.getSettings().setDefaultFontSize(20);
           contentView.loadDataWithBaseURL("",content,mimeType,encoding,"");

        // Set title image from the featured image received from intent using Picasso Library
        ImageView detailsFeatureImage = findViewById(R.id.details_feature_image);
        Picasso.get().load(data.getStringExtra("feature_image")).into(detailsFeatureImage);


    }

    /** Works in conjunction with  this code written in onCreate method
     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     Calls onBackPressed() method when back arrow is clicked
     This redirects user back to the mainActivity**/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()== android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Gets html content from the assets folder.
     */
    private String getHtmlFromAsset() {
        InputStream is;
        StringBuilder builder = new StringBuilder();
        String htmlString = null;
        try {
            is = getAssets().open(getString(R.string.news_html));
            if (is != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                htmlString = builder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return htmlString;

    }


}


