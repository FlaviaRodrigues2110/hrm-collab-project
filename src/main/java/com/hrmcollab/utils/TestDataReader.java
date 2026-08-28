package com.hrmcollab.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class TestDataReader {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Reads a JSON array file from src/test/resources (classpath) into an array of the
     * given POJO type, and wraps each element for TestNG's Object[][] DataProvider format.
     */
    public static <T> Object[][] readAsDataProvider(String classPathFile,Class<T[]> arrayType )
    {
        try(InputStream input = TestDataReader.class.getClassLoader()
                .getResourceAsStream(classPathFile))
        {
            if(input==null)
            {
                throw new RuntimeException("Test Data file not found at location " + classPathFile);
            }

            T[] jsonRecords = mapper.readValue(input,arrayType);
            Object[][] testNgData  = new Object[jsonRecords.length][1];
            for(int i=0;i<jsonRecords.length;i++)
            {
                testNgData[i][0]=jsonRecords [i];
            }
            return testNgData ;

        } catch (Exception e) {
            throw new RuntimeException("Failed to read/parse test data: "+ classPathFile,e);
        }
    }
}
