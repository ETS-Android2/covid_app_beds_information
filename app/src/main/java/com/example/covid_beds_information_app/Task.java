package com.example.covid_beds_information_app;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;


public class Task extends AsyncTask{
    String line = "";
    String response = "";
    String active = "";
    String count = "";
    JSONObject main;
    @Override
    protected String doInBackground(Object[] objects) {

        try {
            URL url = new URL((String)objects[0]);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.connect();
            InputStreamReader isr = new InputStreamReader(httpsURLConnection.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            line = br.readLine();
            while(line!=null){
                response = response+line;
                line = br.readLine();
            }
            JSONObject jsonObject =new JSONObject(response);
            JSONObject state = jsonObject.getJSONObject("Maharashtra");
            JSONObject districtdata = state.getJSONObject("districtData");
            JSONObject city = districtdata.getJSONObject(HomeActivity.city);
            active = city.getString("active");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        String msg = "your city "+HomeActivity.city+" has "+this.active+" active cases today ";
        HomeActivity.tvHomeCity.setText(msg);
    }
}
