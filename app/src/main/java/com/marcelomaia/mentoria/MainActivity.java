package com.marcelomaia.mentoria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView name;
    private TextView password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name =findViewById(R.id.TextViewName);
        password = findViewById(R.id.TextViewPassword);
        login = findViewById(R.id.buttonLogin);

        Context context = getApplicationContext();
        SharedPreferences prefs = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        isLogged(prefs);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = prefs.edit();
//                Set<String> data = Set.of(name, password);
//                editor.putStringSet("user", data);

                editor.putString("username", name.getText().toString());
                editor.apply();

                isLogged(prefs);
            }
        });
    }

    public void isLogged(SharedPreferences prefs) {
        if (prefs.contains("username")) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);

            name.setText("");
            password.setText("");
        }
    };
}