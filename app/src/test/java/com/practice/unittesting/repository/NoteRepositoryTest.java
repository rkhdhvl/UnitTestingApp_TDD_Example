package com.practice.unittesting.repository;
import com.practice.unittesting.models.Note;
import com.practice.unittesting.persistence.NoteDao;
import com.practice.unittesting.ui.Resource;
import com.practice.unittesting.util.TestUtil;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;

import static com.practice.unittesting.repository.NoteRepository.INSERT_FAILURE;
import static com.practice.unittesting.repository.NoteRepository.INSERT_SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NoteRepositoryTest {

    private static final Note NOTE1 = new Note(TestUtil.TEST_NOTE_1);

    @Mock
    NoteDao noteDao;

    @InjectMocks
    NoteRepository noteRepository;


    @Test
    public void dummytest() throws Exception {
        assertNotNull(noteDao);
        assertNotNull(noteRepository);
    }

    /*Tests to be written for*/

    /* insert the note
     * verify the correct method has been called
     * confirm that the observer is triggered
     * confirm that the new row has been inserted
     * Confirming the returned result of the NoteDao class after we insert
     * */

    @Test
    public void insertNote_returnRow() throws Exception
    {
      // Arrange
       final Long insertedRow = 1L;
       final Single<Long> insertResult  = Single.just(insertedRow);
       when(noteDao.insertNote(any(Note.class))).thenReturn(insertResult);
      // Act
       // calling blockingFirst since its a flowable and we don't want to return the actual datatype
       final Resource<Integer> returnedValue = noteRepository.insertNote(NOTE1).blockingFirst();

      // Assert
        // verify that the insertNote method is called
        verify(noteDao).insertNote(any(Note.class));
        // verify that there are no more interactions once the note is inserted
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.success(1,INSERT_SUCCESS),returnedValue);

        System.out.println("Returned value: " + returnedValue.data);
        // testing using RxJava
        noteRepository.insertNote(NOTE1)
                .test()
                .await()
                .assertValue(Resource.success(1,INSERT_SUCCESS));
    }

    /*
    * insert the note
    * Failure ( return -1) */
    @Test
    public void insertNote_returnFailure() throws Exception
    {
      //Arrange
      final Long failedInsert = -1L;
      final Single<Long> insertFailure = Single.just(failedInsert);
      when(noteDao.insertNote(any(Note.class))).thenReturn(insertFailure);

      // Act
      final Resource<Integer> returnedResult = noteRepository.insertNote(NOTE1).blockingFirst();

      assertEquals(Resource.error(null,INSERT_FAILURE),returnedResult);

      // Assert
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        /*noteRepository.insertNote(NOTE1)
                .test()
                .await()
                .onError(null);*/
    }


    /*
    * insert a note with a null title
    * confirm that the exception is thrown*/

    @Test
    public void insertNote_nullTitle_throwException() throws Exception
    {
           assertThrows(Exception.class, new Executable() {
               @Override
               public void execute() throws Throwable {
                final Note note = new Note(TestUtil.TEST_NOTE_1);
                note.setTitle(null);
                noteRepository.insertNote(note);
               }
           });
    }
}