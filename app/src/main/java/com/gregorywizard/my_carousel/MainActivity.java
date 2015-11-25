package com.gregorywizard.my_carousel;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    String firstURL = "http://getyourfreeapp.topaz-analytics.com/landing/img/app_images/flight_pilot/screen1.jpeg";
    //String firstURL = "http://androidexample.com/media/webservice/LazyListView_images/image10.png";
    String nextURL;
    ArrayList<String> arrayOfString = new ArrayList<String>();
    //ProgressDialog progressDialog;

    ListView list;
    LazyImageLoadAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=(ListView)findViewById(R.id.list);

        // Find new images from firstURL
        new UrlChecker().execute(firstURL);

        //ArrayList<String> arrayList = generateArrayOfString(firstURL);
        //String[] newStrings = new String[arrayList.size()];
        //newStrings = arrayList.toArray(newStrings);
        //Log.d("count",Integer.toString(arrayList.size()));

        // Create custom adapter for listview
        //adapter=new LazyImageLoadAdapter(this, newStrings);
        //adapter=new LazyImageLoadAdapter(this, mStrings);

        //Set adapter to listview
        //list.setAdapter(adapter);

        //Button b=(Button)findViewById(R.id.button1);
        //b.setOnClickListener(listener);

    }

    @Override
    public void onDestroy()
    {
        // Remove adapter refference from list
        list.setAdapter(null);
        super.onDestroy();
    }

    public OnClickListener listener=new OnClickListener(){
        @Override
        public void onClick(View arg0) {

            //Refresh cache directory downloaded images
            adapter.imageLoader.clearCache();
            adapter.notifyDataSetChanged();
        }
    };


    public void onItemClick(int mPosition)
    {
        String tempValues = mStrings[mPosition];

        Toast.makeText(MainActivity.this,
                "Image URL : "+tempValues,
                Toast.LENGTH_LONG).show();
    }

    // Image urls used in LazyImageLoadAdapter.java file

    private String[] mStrings={
            "http://getyourfreeapp.topaz-analytics.com/landing/img/app_images/flight_pilot/screen1.jpeg",
            "http://getyourfreeapp.topaz-analytics.com/landing/img/app_images/flight_pilot/screen2.jpeg",
            "http://androidexample.com/media/webservice/LazyListView_images/image2.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image3.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image4.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image5.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image6.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image7.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image8.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image9.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image10.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image0.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image1.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image2.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image3.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image4.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image5.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image6.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image7.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image8.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image9.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image10.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image0.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image1.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image2.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image3.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image4.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image5.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image6.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image7.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image8.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image9.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image10.png"

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //------------------------- Next URL from String ---------------------------
    public static String nextStringFromURL(String s) {
        String result = null;
        char[] chars = s.toCharArray();

        int index=0; int indexOfLast=0;

        String pathStart="",pathName="",pathNumber="",pathExtension="";
        for(char i: chars) {
            if(i == '/') {
                indexOfLast=index;
            }
            index++;
        }

        indexOfLast++;
        pathStart = s.substring(0, indexOfLast);
        //_("pathStart",pathStart);

        String nextString = s.substring(indexOfLast);
        //_("nextString",nextString);
        index=0;
        int indexOfNewLast=0;
        for(char a: nextString.toCharArray()) {
            if(a == '.') {
                indexOfNewLast=index;
                //_(Integer.toString(indexOfNewLast));
                pathExtension = s.substring(indexOfLast+indexOfNewLast);
            }
            index++;
        }
        if(!pathExtension.equals("")) {
            //_("pathExtension",pathExtension);
        } else {
            //_("ERROR in PATH EXTENSION!");
            return null;
        }

        String otherString = s.substring(indexOfLast,indexOfLast+indexOfNewLast);
        //_("otherString",otherString);

        String num = "";

        boolean first = true;
        index=0; int indexOfCopy=0;
        for(char b: otherString.toCharArray()) {
            if(first) {
                if(isNum(b)) {
                    num += b;
                    indexOfCopy = index;
                    first = false;
                }
            } else {
                if(isNum(b)) {
                    num += b;
                }
            }

            index++;
        }
        pathName = s.substring(indexOfLast,indexOfLast+indexOfCopy);
        //_("pathName",pathName);

        //_("num",num);
        int realNum = Integer.parseInt(num);
        realNum++;
        //_("realNum",Integer.toString(realNum));

        result = pathStart + pathName + Integer.toString(realNum) + pathExtension;
        //_("result",result);
        return result;
    }

    public static boolean isNum(char ch) {
        char[] num = { '0','1','2','3','4','5','6','7','8','9' };

        for(int i=0;i<num.length;i++) {
            if(ch == num[i]) return true;
        }

        return false;
    }

    public class UrlChecker extends AsyncTask<String, Void, Boolean> {
        String nextURL;

        @Override
        protected Boolean doInBackground(String... params) {
            String strurl = params[0];

            //arrayOfString.add(strurl);
            nextURL = strurl;
            for(;;) {

                try {
                    URL url = new URL(nextURL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("HEAD");
                    con.connect();
                    Log.i("OK", "con.getResponseCode() IS : " + con.getResponseCode());
                    if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        Log.i("OK", "Success");
                        arrayOfString.add(nextURL);
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("BAD", "fail url");
                    return false;
                }

                nextURL = nextStringFromURL(nextURL);
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
                Log.i("OK2", "Success");
                String[] newStrings = new String[arrayOfString.size()];
                newStrings = arrayOfString.toArray(newStrings);
                Log.d("count",Integer.toString(arrayOfString.size()));

                // Create custom adapter for listview
                adapter=new LazyImageLoadAdapter(MainActivity.this, newStrings);
                //adapter=new LazyImageLoadAdapter(this, mStrings);

                //Set adapter to listview
                list.setAdapter(adapter);
                Log.i("BAD2", "fail url");

                //progressDialog.dismiss();
                //progressDialog = null;
        }

        @Override
        protected void onPreExecute() {
            //progressDialog = new ProgressDialog(MainActivity.this);
            //progressDialog.setMessage("Loading...");
            //progressDialog.show();
        }
    }

    //--------------------------------------------------------------------------
}