package example.com.android.news;

import android.content.Context;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sharma786 on 04/05/2017.
 */

public class Utility {

    public static String jsonCreator(String x, Context context) throws JSONException {
        HttpURLConnection conn = null;
        String temp="";


        switch (x)
        {
            case "all" :  temp=context.getResources().getString(R.string.allUrl);
                break;
            case "business" : temp=context.getResources().getString(R.string.bizUrl);
                break;
            case "politics" : temp=context.getResources().getString(R.string.politicsUrl);
                break;
            case "technology" : temp=context.getResources().getString(R.string.techUrl);
                break;

        }

        try {

            URL url = new URL(temp);

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

    public static ArrayList<News> createListItem(String x, ArrayList<News> newsArrayList) throws JSONException, ParseException {
        News news;
        JSONObject baseJsonResponse = new JSONObject(x);
        JSONObject response=baseJsonResponse.getJSONObject("response");
        JSONArray results = response.getJSONArray("results");

        for(int i=0;i<results.length();i++)
        {
            JSONObject newsObject=results.getJSONObject(i);

            String title=newsObject.getString("webTitle");
            String section=newsObject.getString("sectionName");
            String dateString=newsObject.getString("webPublicationDate");
            String newsUrl=newsObject.getString("webUrl");

            Date last_date_date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);


            news=new News(newsUrl,title,section,new SimpleDateFormat("yyyy-MM-dd").format(last_date_date));
            newsArrayList.add(news);
        }
        return newsArrayList;
    }

    public static ArrayList<String> createUrlList(ArrayList<News> newsArrayList)
    {
        ArrayList<String> urlArrayList=new ArrayList<String>();
        for(int i=0;i<newsArrayList.size();i++)
        {
            News news=newsArrayList.get(i);
            String newsUrl=news.getNewsUrl();
            urlArrayList.add(newsUrl);
        }

        return urlArrayList;
    }
}
