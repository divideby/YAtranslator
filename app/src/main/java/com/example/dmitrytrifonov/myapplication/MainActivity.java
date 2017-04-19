package com.example.dmitrytrifonov.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static YAService yaserv;
    private Retrofit retrofit;
    private String key = "trnsl.1.1.20170419T123712Z.0f809200ad0f727d.c87bf576d6446901f1291c7c449898bc449d73a0";

    Button switchbtn;
    Button translatebtn;

    TextView slang;
    TextView rlang;

    EditText stext;
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        switchbtn = (Button)findViewById(R.id.switchbtn);
        translatebtn = (Button)findViewById(R.id.translatebtn);
        slang = (TextView)findViewById(R.id.slang);
        rlang = (TextView)findViewById(R.id.rlang);
        stext = (EditText)findViewById(R.id.source);
        result = (TextView)findViewById(R.id.result);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://translate.yandex.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        yaserv = retrofit.create(YAService.class);

    }



    public static YAService getApi() {
        return yaserv;
    }

    public void onTranslate(View view)
    {
        String lang = rlang.getText().toString();
        String text = stext.getText().toString();
        MainActivity.getApi().translate(key, text, lang).enqueue(new Callback<YAResponse>() {
            @Override
            public void onResponse(Call<YAResponse> call, Response<YAResponse> response) {
                result.setText(response.body().getTranslate().get(0));
            }
            @Override
            public void onFailure(Call<YAResponse> call, Throwable t) {
                System.out.println(t.toString());
                Toast toast = Toast.makeText(getApplicationContext(),
                        t.toString(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public void onSwitch(View view)
    {
        String lang = rlang.getText().toString();
        rlang.setText(slang.getText());
        slang.setText(lang);
    }

}
