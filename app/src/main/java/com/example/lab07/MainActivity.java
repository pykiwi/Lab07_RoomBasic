package com.example.lab07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView ls;
    private Button btnAdd, btnRemove, btnCancel;
    private EditText editText;
    private List<Person> persons;
    private PersonDAO personDAO;
    private PersonAdapter personAdapter;
    private ConnectDatabase db;
    private int deletedId  = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = ConnectDatabase.getInstance(getApplicationContext());
        personDAO = db.personDAO();

        ls = findViewById(R.id.listview);
        editText = findViewById(R.id.name);
        btnAdd = findViewById(R.id.btnAdd);
        btnRemove = findViewById(R.id.btnRemove);
        btnCancel = findViewById(R.id.btnCancel);

        persons = personDAO.getAll();
        personAdapter = new PersonAdapter(MainActivity.this, persons, R.layout.item_view );
        ls.setAdapter(personAdapter);
        btnAdd.setOnClickListener(v -> {
            String name  = editText.getText().toString();
            Person person = new Person(name);
            personDAO.addPerson(person);
            updateData();
            editText.setText("");
        });
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deletedId = (int) id;
                editText.setText(personDAO.getPersonById((int)id).getName());
            }
        });
        btnRemove.setOnClickListener(v -> {

            if(deletedId != -1){
                personDAO.deletePerson(personDAO.getPersonById(deletedId));
                updateData();
                editText.setText("");
                deletedId = -1;
            }
        });
        btnCancel.setOnClickListener(v -> {
            deletedId = -1;
            editText.setText("");
        });

    }
    private  void updateData(){

        ls.setAdapter(new PersonAdapter(MainActivity.this, personDAO.getAll(), R.layout.item_view ));
    }
}