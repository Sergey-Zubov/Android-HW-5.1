package com.szubov.android_hw_51;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextNote;
    private SharedPreferences mNoteSharedPref;
    private static String NOTE_TEXT = "note_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        getNoteFromSharedPref();
    }

    private void initViews() {
        mEditTextNote = findViewById(R.id.editTextNote);
        mNoteSharedPref = getSharedPreferences("MyNote", MODE_PRIVATE);
    }

    public void btnSaveNoteOnClick(View view) {
        SharedPreferences.Editor myEditor = mNoteSharedPref.edit();
        if (mEditTextNote.getText().toString().trim().length() > 0) {
            myEditor.putString(NOTE_TEXT, mEditTextNote.getText().toString().trim());
            myEditor.apply();
            Toast.makeText(this, getText(R.string.text_saved), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getText(R.string.edit_text_is_empty), Toast.LENGTH_LONG).show();
        }

    }

    private void getNoteFromSharedPref() {
        String noteTxt = mNoteSharedPref.getString(NOTE_TEXT, "");
        mEditTextNote.setText(noteTxt);
    }
}