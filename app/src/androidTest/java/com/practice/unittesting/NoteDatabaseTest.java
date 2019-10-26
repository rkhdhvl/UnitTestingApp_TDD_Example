package com.practice.unittesting;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.practice.unittesting.persistence.NoteDao;
import com.practice.unittesting.persistence.NoteDatabase;

import org.junit.After;
import org.junit.Before;

// using JUnit4 for Instrumentation tests, since JUnit 5 isn't compatible for device APIs bellow API level 26
// Just to ensure compatibility for devices API level 25 and bellow using JUnit4, or on API level 26 and above
public abstract class NoteDatabaseTest {

    // system under test
    private NoteDatabase noteDatabase;

    public NoteDao getNoteDao()
    {
        return noteDatabase.getNoteDao();
    }

    @Before
    public void init()
    {
        // creating a mock database for testing
        // this will exist as long as the application is alive , which is suitable for the purpose of testing
        noteDatabase = Room.inMemoryDatabaseBuilder(
                // getting a reference to the android framework within the test
                ApplicationProvider.getApplicationContext(),
                NoteDatabase.class
        ).build();
    }

    @After
    public void finish()
    {
        noteDatabase.close();
    }

}
