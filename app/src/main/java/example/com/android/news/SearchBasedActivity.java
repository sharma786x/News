package example.com.android.news;

import android.content.Context;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SearchBasedActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    News news;
    ArrayList<News> newsArrayList;
    ListView listView;
    NewsAdapter newsAdapter;
    static String query;
    ArrayList<String> urlArrayList;
    TextView emptyView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_based);
        Intent intent=getIntent();
        urlArrayList=new ArrayList<String>();
        emptyView = (TextView) findViewById(R.id.emptyView);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        query=intent.getStringExtra("query");

        listView = (ListView) findViewById(R.id.searchBasedListView);
        listView.setEmptyView(emptyView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0 : Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(urlArrayList.get(0)));
                        startActivity(i);
                        break;
                    case 1 : Intent i1 = new Intent(Intent.ACTION_VIEW);
                        i1.setData(Uri.parse(urlArrayList.get(1)));
                        startActivity(i1);
                        break;
                    case 2 : Intent i2 = new Intent(Intent.ACTION_VIEW);
                        i2.setData(Uri.parse(urlArrayList.get(2)));
                        startActivity(i2);
                        break;
                    case 3 : Intent i3 = new Intent(Intent.ACTION_VIEW);
                        i3.setData(Uri.parse(urlArrayList.get(3)));
                        startActivity(i3);
                        break;
                    case 4 : Intent i4 = new Intent(Intent.ACTION_VIEW);
                        i4.setData(Uri.parse(urlArrayList.get(4)));
                        startActivity(i4);
                        break;
                    case 5 : Intent i5 = new Intent(Intent.ACTION_VIEW);
                        i5.setData(Uri.parse(urlArrayList.get(5)));
                        startActivity(i5);
                        break;
                    case 6 : Intent i6 = new Intent(Intent.ACTION_VIEW);
                        i6.setData(Uri.parse(urlArrayList.get(6)));
                        startActivity(i6);
                        break;
                    case 7 : Intent i7 = new Intent(Intent.ACTION_VIEW);
                        i7.setData(Uri.parse(urlArrayList.get(7)));
                        startActivity(i7);
                        break;
                    case 8 : Intent i8 = new Intent(Intent.ACTION_VIEW);
                        i8.setData(Uri.parse(urlArrayList.get(8)));
                        startActivity(i8);
                        break;
                    case 9 : Intent i9 = new Intent(Intent.ACTION_VIEW);
                        i9.setData(Uri.parse(urlArrayList.get(9)));
                        startActivity(i9);
                        break;
                }
            }
        });

        newsArrayList = new ArrayList<News>();

        newsAdapter = new NewsAdapter(this, newsArrayList);

        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                getSupportLoaderManager().initLoader(0, null, this).forceLoad();
            } else {
                emptyView.setText(getResources().getString(R.string.noInternet));
                progressBar.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new FetchData(this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

        newsArrayList.clear();

        try {
            News news;
            JSONObject baseJsonResponse = new JSONObject(data);
            JSONObject response=baseJsonResponse.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");



            for(int i=0;i<results.length();i++)
            {
                JSONObject newsObject=results.getJSONObject(i);
                String title=newsObject.getString("webTitle");
                String section=newsObject.getString("sectionName");
                String url=newsObject.getString("webUrl");
                news=new News("",title,section,"");
                urlArrayList.add(url);
                newsArrayList.add(news);

            }


        } catch (JSONException e) {
            e.printStackTrace();


        } finally {
            listView.setAdapter(newsAdapter);
            emptyView.setText(getResources().getString(R.string.noItems));
            progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    private static class FetchData extends AsyncTaskLoader<String> {

        public FetchData(Context context) {
            super(context);
        }
        String s;

        @Override
        public String loadInBackground() {
            HttpURLConnection conn;
            String x="";

            try {

                URL url = new URL("http://content.guardianapis.com/tags?q="+query+"&api-key=test");

                conn = (HttpURLConnection) url.openConnection();

                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String webPage = "", data = "";

                while ((data = reader.readLine()) != null) {
                    webPage += data + "\n";
                }
                x = webPage;

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return x;
        }

        @Override
        public void deliverResult(String data) {
            super.deliverResult(data);
        }
    }



    public void click(View view)
    {
        Click.execute(view);

    }


}

