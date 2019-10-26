package com.practice.unittesting;

import android.database.sqlite.SQLiteConstraintException;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.practice.unittesting.models.Note;
import com.practice.unittesting.util.LiveDataTestUtil;
import com.practice.unittesting.util.TestUtil;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NoteDaoTest extends NoteDatabaseTest {

    public static final String TEST_TITLE = "This is a test title";
    public static final String TEST_CONTENT = "This is some test content";
    public static final String TEST_TIMESTAMP = "08-2018";

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Test
    public void insertReadDelete() throws Exception
    {
        Note note = new Note(TestUtil.TEST_NOTE_1);
        // insert
        getNoteDao().insertNote(note).blockingGet(); // wait until its inserted

        // read
        LiveDataTestUtil<List<Note>> listLiveDataTestUtil = new LiveDataTestUtil<>();
        List<Note> insertedNotes = listLiveDataTestUtil.getValue(getNoteDao().getNotes());

        assertNotNull(insertedNotes);

        // checking that the contents of the inserted notes match
        assertEquals(note.getContent(),insertedNotes.get(0).getContent());
        assertEquals(note.getTitle(),insertedNotes.get(0).getTitle());
        assertEquals(note.getTimestamp(),insertedNotes.get(0).getTimestamp());

        note.setId(insertedNotes.get(0).getId());
        assertEquals(note,insertedNotes.get(0));

        // delete the note and then test if the database is empty
        getNoteDao().deleteNote(note).blockingGet();

        // confirming that the database is empty
        insertedNotes = listLiveDataTestUtil.getValue(getNoteDao().getNotes());
        assertEquals(0,insertedNotes.size());
    }


    @Test
    public void insertReadUpdateReadDelete() throws Exception
    {
        Note note = new Note(TestUtil.TEST_NOTE_1);
        // insert
        getNoteDao().insertNote(note).blockingGet(); // wait until its inserted

        // read
        LiveDataTestUtil<List<Note>> listLiveDataTestUtil = new LiveDataTestUtil<>();
        List<Note> insertedNotes = listLiveDataTestUtil.getValue(getNoteDao().getNotes());

        assertNotNull(insertedNotes);

        // checking that the contents of the inserted notes match
        assertEquals(note.getContent(),insertedNotes.get(0).getContent());
        assertEquals(note.getTitle(),insertedNotes.get(0).getTitle());
        assertEquals(note.getTimestamp(),insertedNotes.get(0).getTimestamp());

        note.setId(insertedNotes.get(0).getId());
        assertEquals(note,insertedNotes.get(0));

        // update the note and read it again
        note.setTitle(TEST_TITLE);
        note.setContent(TEST_CONTENT);
        note.setTimestamp(TEST_TIMESTAMP);
        getNoteDao().updateNote(note).blockingGet();

        // verify the contents of the updated note
        insertedNotes = listLiveDataTestUtil.getValue(getNoteDao().getNotes());
        assertEquals(TEST_TITLE,insertedNotes.get(0).getTitle());
        assertEquals(TEST_CONTENT,insertedNotes.get(0).getContent());
        assertEquals(TEST_TIMESTAMP,insertedNotes.get(0).getTimestamp());

        note.setId(insertedNotes.get(0).getId());
        assertEquals(note,insertedNotes.get(0));


        // delete the note and then test if the database is empty
        getNoteDao().deleteNote(note).blockingGet();

        // confirming that the database is empty
        insertedNotes = listLiveDataTestUtil.getValue(getNoteDao().getNotes());
        assertEquals(0,insertedNotes.size());
    }

    // inserting a note with null title , which is expected to throw an exception
    @Test(expected = SQLiteConstraintException.class)
    public void insert_nullTitle_throwsSQLiteConstraintException() throws Exception
    {
      final Note note = new Note(TestUtil.TEST_NOTE_1);
      note.setTitle(null);
      //
        getNoteDao().insertNote(note).blockingGet();
    }

    @Test(expected = SQLiteConstraintException.class)
    public void updateNote_nullTitle_throwSQLiteConstraintException() throws Exception
    {
        Note note = new Note(TestUtil.TEST_NOTE_1);
        // inserting the note
        getNoteDao().insertNote(note).blockingGet();

        // reading the note
        LiveDataTestUtil<List<Note>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Note> insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes());
        assertNotNull(insertedNotes);

        //update
        note = new Note(insertedNotes.get(0));
        note.setTitle(null);
        getNoteDao().updateNote(note).blockingGet();
    }



}
