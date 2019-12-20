package com.mahmoud.dahdouh.archcomponents;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.mahmoud.dahdouh.archcomponents.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.mahmoud.dahdouh.archcomponents.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.mahmoud.dahdouh.archcomponents.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.mahmoud.dahdouh.archcomponents.EXTRA_PRIORITY";

    private EditText ed_title, ed_description;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        ed_title = findViewById(R.id.ed_title);
        ed_description = findViewById(R.id.ed_description);
        numberPicker = findViewById(R.id.number_picker);

        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);


        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            ed_title.setText(intent.getStringExtra(EXTRA_TITLE));
            ed_description.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else setTitle("Add Note");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_icon:
                saveNote();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }

    public void saveNote() {
        String title = ed_title.getText().toString();
        String description = ed_description.getText().toString();
        int priority = numberPicker.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please enter the Tiitle and Desription", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }
}
