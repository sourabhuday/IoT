package edu.uncc.dauti.ninersense_new;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Sourabhpc on 10/26/2016.
 */

public class FrontDoorAsyncTask extends AsyncTask<String, Void, String> {

    private IData activity;
    public FrontDoorAsyncTask(IData activity) {
        this.activity=activity;
    }

    @Override
    protected String doInBackground(String... params) {

        String result = "";
        URL url = null;
        try {
            String str = params[0];
            url = new URL("https://ninersense.mybluemix.net/frontdoor.php");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            String data = "frontdoor=" + str;
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write(data.getBytes("UTF-8"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString().trim();
            Log.d("result", result);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //http postappSpinners
        return result;
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // activity.garageDoorDataSet(s);
    }

    interface IData{

    }
}
