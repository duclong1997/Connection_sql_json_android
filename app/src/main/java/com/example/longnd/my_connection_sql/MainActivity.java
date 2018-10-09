package com.example.longnd.my_connection_sql;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    private String TAG = MainActivity.class.getSimpleName();
    private  MyHttpHandler myhttp = new MyHttpHandler();
    private ArrayList<Student> arrayList = new ArrayList<>();
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
        //  click in iteam
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,arrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyAsyncTask extends AsyncTask<Void,Void,Void > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            Toast.makeText(MainActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String url = "http://192.168.0.104:8082/select.php";
            String jsonStr = myhttp.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray aray = new JSONArray(jsonStr);
                    for (int i = 0; i < aray.length(); i++) {
                        JSONObject object = aray.getJSONObject(i);
                        String id = object.getString("id");
                        String name= object.getString("name");
                        Student stu = new Student(id, name);
                        arrayList.add(stu);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // set value in list view iteam - > view main
            ControlStudent con = new ControlStudent(MainActivity.this, R.layout.view_enty, arrayList);
            list.setAdapter(con);
        }
    }
}
