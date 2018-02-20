package example.com.android.news;

import java.util.Date;

/**
 * Created by Sharma786 on 08/05/2017.
 */

public class News {

    String title;
    String section;
    String date;
    String newsUrl;

    public News(String w, String x, String y, String z) {
        title = x;
        section = y;
        date = z;
        newsUrl = w;
    }

    public String getTitle() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public String getDate() {
        return date;
    }

    public String getNewsUrl() {
        return newsUrl;
    }
}
