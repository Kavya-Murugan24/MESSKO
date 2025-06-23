package com.example.messko;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    EditText editName, editDays;
    Button btnSave;
    ListView listView;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editDays = findViewById(R.id.editDays);
        btnSave = findViewById(R.id.btnSave);
        listView = findViewById(R.id.listView);
        db = new Database(this);

        btnSave.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            int days = Integer.parseInt(editDays.getText().toString().trim());
            db.insertStudent(name, days);
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
            loadList();
            editName.setText("");
            editDays.setText("");
        });

        loadList();
    }

    private void loadList() {
        ArrayList<String> data = db.getAllStudents();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }
}