package com.practice.unittesting.di;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.practice.unittesting.persistence.NoteDao;
import com.practice.unittesting.persistence.NoteDatabase;
import com.practice.unittesting.repository.NoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.practice.unittesting.persistence.NoteDatabase.DATABASE_NAME;

@Module
class AppModule {

    @Singleton
    @Provides
    static NoteDatabase provideNoteDatabase(Application application) {
        return Room.databaseBuilder(application,
                NoteDatabase.class,
                DATABASE_NAME).build();
    }

    @Singleton
    @Provides
    static NoteDao provideNotesDao(NoteDatabase noteDatabase)
    {
        return noteDatabase.getNoteDao();
    }

    @Singleton
    @Provides
    static NoteRepository provideNoteRepository(NoteDao noteDao)
    {
        return new NoteRepository(noteDao);
    }

}
