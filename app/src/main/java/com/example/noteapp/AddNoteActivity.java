package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextName, editTextContent;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextName = findViewById(R.id.editTextName);
        editTextContent = findViewById(R.id.editTextContent);
        buttonSave = findViewById(R.id.buttonSaveNote);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String content = editTextContent.getText().toString().trim();

                if (name.isEmpty() || content.isEmpty()) {
                    Toast.makeText(AddNoteActivity.this, "Užpildyk abu laukus", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Note> notes = NoteStorage.loadNotes(AddNoteActivity.this);
                notes.add(new Note(name, content));
                NoteStorage.saveNotes(AddNoteActivity.this, notes);

                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
