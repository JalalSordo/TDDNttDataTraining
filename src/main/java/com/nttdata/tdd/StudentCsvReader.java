package com.nttdata.tdd;

import com.nttdata.tdd.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * a clas that reads a csv file and returns a list of students
 *
 * @author Jalal Sordo
 * @since 26-Dec-23
 */
@Slf4j
public class StudentCsvReader {

    public List<Student> readCsvFile(File csvFile) throws ApplicationException {

        if (csvFile == null) {
            throw new ApplicationException("Cannot process the student csv file, file is null");
        }

        if (!csvFile.exists()) {
            throw new ApplicationException("Cannot process the student csv file, file doesn't exist");
        }

        List<Student> students = new ArrayList<>();
        try {
            Reader in = new FileReader(csvFile);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
            for (CSVRecord record : records) {
                Student student = new Student();
                String studentId = record.get("StudentID");
                if(studentId == null || studentId.isEmpty()) {
                    student.setStudentId(-1);
                } else {
                    student.setStudentId(Integer.parseInt(studentId));
                }

                String firstName = record.get("FirstName");
                student.setFirstName(firstName);



                students.add(student);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return students;
    }
}
