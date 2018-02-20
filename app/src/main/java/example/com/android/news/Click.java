package example.com.android.news;

import android.content.Intent;
import android.view.View;

/**
 * Created by Sharma786 on 08/05/2017.
 */

public class Click {

    public static void execute(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.biz:
                Intent intent = new Intent(view.getContext(), BizActivity.class);
                view.getContext().startActivity(intent);
                break;
            case R.id.all:
                Intent intent1 = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent1);
                break;
            case R.id.politics:
                Intent intent2 = new Intent(view.getContext(), PoliticsActivity.class);
                view.getContext().startActivity(intent2);
                break;
            case R.id.technology:
                Intent intent3 = new Intent(view.getContext(), TechnologyActivity.class);
                view.getContext().startActivity(intent3);
                break;

        }
    }
}
