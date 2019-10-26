package com.practice.unittesting.persistence;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

import com.practice.unittesting.models.Note;

@Database(entities = {Note.class} , version =1)
public abstract class NoteDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "notes_db";
   // returning a reference to the NoteDao class
    public abstract NoteDao getNoteDao();
}
