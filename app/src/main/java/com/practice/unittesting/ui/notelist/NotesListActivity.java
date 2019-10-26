package com.practice.unittesting.ui.notelist;

import android.os.Bundle;
import android.util.Log;

import com.practice.unittesting.R;
import com.practice.unittesting.repository.NoteRepository;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class NotesListActivity extends DaggerAppCompatActivity {

    public static final String TAG = NotesListActivity.class.getName();

    @Inject
    NoteRepository noteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_list_activity);
        Log.d(TAG,"onCreate : "+ noteRepository );
    }
}
