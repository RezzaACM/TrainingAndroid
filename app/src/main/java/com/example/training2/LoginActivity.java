package com.example.training2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    String var_username, var_password;
//    RestProcess merupakan class dari rest proses dan fungsi untuk memanggil classnya
    private RestProcess rest_class;
//    Mendeklarasikan Array List
    ArrayList<HashMap<String,String>> arrayLogin = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rest_class = new RestProcess();

        final EditText username = (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);

        Button btnLogin = (Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                var_username = username.getText().toString();
                var_password = password.getText().toString();

                if (var_username.length() < 7) {
//                    Toast untuk menampilkan pesan keasalahan
                    Toast.makeText(LoginActivity.this, (R.string.toast_username), Toast.LENGTH_SHORT).show();
                } else if (var_password.length() < 6 ) {
                    Toast.makeText(LoginActivity.this, (R.string.toast_password), Toast.LENGTH_SHORT).show();
                } else {
//                    Intent main_activity = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(main_activity);
                    loginProcess(view);
                }
            }
        });

    }
    public void loginProcess(final View view) {

        HashMap<String, String> apiData = new HashMap<String, String>();
        apiData = rest_class.apiSetting();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        String base_url;

        base_url = apiData.get("str_ws_addr") + "/api/training/auth/format/json";
        params.put("var_cell_phone", var_username);
        params.put("var_password", var_password);

        client.setBasicAuth(apiData.get("str_ws_user"), apiData.get("str_ws_pass"));
        client.post(base_url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String resp_content = "";
                try {
                    resp_content = new String(responseBody, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    displayLogin(view, resp_content);
                } catch (Throwable t) {
                    Toast.makeText(LoginActivity.this, "Koneksi Gagal !", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(LoginActivity.this, "Koneksi Gagal !", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void displayLogin(View view, String resp_content) {
        try {
            arrayLogin = rest_class.getJsonData(resp_content);
            if (arrayLogin.get(0).get("var_result").equals("1")) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            } else if (arrayLogin.get(0).get("var_result").equals("0")) {

                Toast.makeText(LoginActivity.this,"Koneksi Gagal !" , Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            Toast.makeText(LoginActivity.this, "Koneksi Gagal !", Toast.LENGTH_SHORT).show();
        }
    }


}


