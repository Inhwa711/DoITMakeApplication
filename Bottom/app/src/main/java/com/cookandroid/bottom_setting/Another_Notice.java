package com.cookandroid.bottom_setting;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Another_Notice extends AppCompatActivity {

    int[] id;
    String[] title;
    String[] writer;
    String[] date;
    String[] content;
    int length;

    private ListView noticeListView;
    private Another_Notice_Adapter noticeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_notice);

        String IP = getString(R.string.web_IP);

        //Intent 받아오기
        Intent intent_notice = new Intent(this.getIntent());

        String url = "http://" + IP + "/notification.php";
        selectDatabase selectDatabase = new selectDatabase(url, null);
        selectDatabase.execute();

        // 리스트뷰 어댑터 설정
        noticeAdapter = new Another_Notice_Adapter();
        noticeListView = (ListView) findViewById(R.id.noticeListView);

        noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int pos = length - position - 1;

                Intent recycle_intent = new Intent(getApplicationContext(), Another_Recycle_Notification.class);
                recycle_intent.putExtra("title", title[pos]);
                recycle_intent.putExtra("writer", writer[pos]);
                recycle_intent.putExtra("date", date[pos]);
                recycle_intent.putExtra("content", content[pos]);
                startActivity(recycle_intent);

            }
        });
    }

    class selectDatabase extends AsyncTask<Void, Void, String> {
        private String url1;
        private ContentValues values1;
        String result1;

        public selectDatabase(String url, ContentValues contentValues) {
            this.url1 = url;
            this.values1 = contentValues;
        }

        @Override
        protected String doInBackground(Void... params) {
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result1 = requestHttpURLConnection.request(url1, values1);
            return result1;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            doJSONParser(s);
        }

        private void doJSONParser(String string) {
            try {
                String result = "";
                JSONObject jsonObject = new JSONObject(string);
                JSONArray jsonArray = jsonObject.getJSONArray("notification");

                length = jsonArray.length();

                id = new int[length];
                title = new String[length];
                writer = new String[length];
                date = new String[length];
                content = new String[length];

                for (int i = 0; i < length; i++) {
                    JSONObject output = jsonArray.getJSONObject(i);
                    id[i] = output.getInt("id");
                    title[i] = output.getString("title");
                    writer[i] = output.getString("writer");
                    date[i] = output.getString("date");
                    content[i] = output.getString("content");
                }
                noticeListView.setAdapter(noticeAdapter);


                for (int i = length-1; i >= 0; i--) {
                    noticeAdapter.addItem(id[i], title[i], writer[i], date[i]);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}