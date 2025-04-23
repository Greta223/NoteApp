package com.example.noteapp;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NoteStorage {
    private static final String FILE_NAME = "notes.json";

    public static void saveNotes(Context context, List<Note> notes) {
        Gson gson = new Gson();
        String json = gson.toJson(notes);
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(json.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Note> loadNotes(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Type type = new TypeToken<List<Note>>() {}.getType();
            return new Gson().fromJson(isr, type);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
