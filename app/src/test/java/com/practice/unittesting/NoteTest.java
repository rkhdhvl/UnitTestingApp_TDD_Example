package com.practice.unittesting;

import com.practice.unittesting.models.Note;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import io.reactivex.subjects.PublishSubject;

// testing the basics with this local unit test written with JUnit5

public class NoteTest {

    public static final String TIME_STAMP1 = "05-2019";
    public static final String TIME_STAMP2 = "04-2019";


    //  1) Compare 2 equal notes
    @Test
    public void isNotesEqual_identicalProperties() throws Exception
    {
        //Arrange
        Note note1 = new Note("Note #1","This is note #1",TIME_STAMP1);
        note1.setId(1);
        Note note2 = new Note("Note #1","This is note #1",TIME_STAMP1);
        note2.setId(1);
        //Act

        //Assert
        Assertions.assertEquals(note1,note2);
        System.out.println("The notes are equal");
    }

    // 2) Compare 2 notes with different ids
    @Test
    public void notesNotEqual_differentProperties() throws Exception
    {
        //Arrange
        Note note1 = new Note("Note #1","This is note #1",TIME_STAMP1);
        note1.setId(1);
        Note note2 = new Note("Note #1","This is note #1",TIME_STAMP1);
        note2.setId(2);
        //Act

        //Assert
        Assertions.assertNotEquals(note1,note2);
        System.out.println("The notes are not equal");
    }

    // 3) Compare 2 notes with different timestamps , that should be equal , since we've overriden the equals method

    @Test
    public void notesEqual_differentTimeStamps() throws Exception
    {
        //Arrange
        Note note1 = new Note("Note #1","This is note #1",TIME_STAMP1);
        note1.setId(1);
        Note note2 = new Note("Note #1","This is note #1",TIME_STAMP2);
        note2.setId(1);
        //Act

        //Assert
        Assertions.assertEquals(note1,note2);
        System.out.println("The notes are equal");
    }

    //  4) Compare 2 notes with different titles
    @Test
    public void notesNotEqual_differentTitles_shouldReturnFalse() throws Exception
    {
        //Arrange
        Note note1 = new Note("Note #1","This is note #1",TIME_STAMP1);
        note1.setId(1);
        Note note2 = new Note("Note #2","This is note #1",TIME_STAMP1);
        note2.setId(1);
        //Act

        //Assert
        Assertions.assertNotEquals(note1,note2);
        System.out.println("The notes are not equal, they have different titles");
    }

    // 5) Compare 2 notes with different content
    @Test
    public void notesNotEqual_differentContent_shouldReturnFalse() throws Exception {
        //Arrange
        Note note1 = new Note("Note #1", "This is note #1", TIME_STAMP1);
        note1.setId(1);
        Note note2 = new Note("Note #1", "This is note #2", TIME_STAMP1);
        note2.setId(1);
        //Act

        //Assert
        Assertions.assertNotEquals(note1, note2);
        System.out.println("The notes are not equal, they have different content");
    }

}
