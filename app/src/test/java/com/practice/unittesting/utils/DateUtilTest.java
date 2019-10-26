package com.practice.unittesting.utils;

import com.practice.unittesting.models.Note;
import com.practice.unittesting.util.DateUtil;
import com.practice.unittesting.util.TestUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static com.practice.unittesting.util.DateUtil.GET_MONTH_ERROR;
import static com.practice.unittesting.util.DateUtil.getMonthFromNumber;
import static com.practice.unittesting.util.DateUtil.monthNumbers;
import static com.practice.unittesting.util.DateUtil.months;
import static org.junit.jupiter.api.Assertions.*;

public class DateUtilTest {

    private static final String todays_date = "10-2019";

    // Writing a unit test to check that the function for getting the today's date in the mentioned format does
    // not throw an exception
    @Test
    public void testGetCurrentTimeStamp_returnedTimeStamp()
    {
        assertDoesNotThrow(new Executable(){
           @Override
           public void execute() throws Throwable
           {
             assertEquals(todays_date,DateUtil.getCurrentTimeStamp());
             System.out.println("Timestamp is generated correctly");
           }
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6,7,8,9,10,11})
    public void matchMonthFromNumbersPassedAsParameters_returnSuccess(int monthNumber, TestInfo testInfo, TestReporter testReporter)
    {
        // passing each number paramter to the month array and checking if it returns us back the correct month
        // in short words chekcing if the month names match
       assertEquals(months[monthNumber],DateUtil.getMonthFromNumber(monthNumbers[monthNumber]));
       System.out.println(monthNumbers[monthNumber]+" : "+ months[monthNumber]);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11})
    public void returnMonthForNumber_returnError_forInvalidMonthNumberPassed(int monthNumber, TestInfo testInfo, TestReporter testReporter)
    {
      // the least value of this random int will start from 13 and above
      int randomInt = new Random().nextInt(90)+13;
      assertEquals(getMonthFromNumber(String.valueOf(monthNumber*randomInt)),GET_MONTH_ERROR);
      System.out.println(monthNumbers[monthNumber]+" : "+GET_MONTH_ERROR);
    }


}
