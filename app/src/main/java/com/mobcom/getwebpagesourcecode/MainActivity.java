package com.mobcom.getwebpagesourcecode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MyActivity";
  private TextView tvPageSource;
  private EditText etWebURL;
  private Spinner spinner;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setTitle("Get Web Page Source Code");

    Button btn = findViewById(R.id.button);
    etWebURL = findViewById(R.id.editTextUrl);
    tvPageSource = findViewById(R.id.pageSourceCode);


    // Instantiate the RequestQueue.
    RequestQueue queue = Volley.newRequestQueue(this);

    spinner = findViewById(R.id.spinner);

    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String rawString = etWebURL.getText().toString();

        String Url = spinner.getSelectedItem().toString() + rawString;
        Log.d(TAG, "onClick: " + spinner.getSelectedItem().toString());

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            tvPageSource.setText(response.toString().replace("\n",""));
          }
        }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            tvPageSource.setText("That didn't work!");
          }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
      }
    });
  }
}