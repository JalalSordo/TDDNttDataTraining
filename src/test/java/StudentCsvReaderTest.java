import com.nttdata.tdd.Student;
import com.nttdata.tdd.StudentCsvReader;
import com.nttdata.tdd.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for StudentCsvReader class.
 *
 * @author Jalal Sordo
 * @since 26-Dec-23
 */
public class StudentCsvReaderTest {


    @Test
    public void test_readCsvFile_null_file() {
        StudentCsvReader studentCsvReader = new StudentCsvReader();
        try {
            studentCsvReader.readCsvFile(null);
            fail("Should not reach here exception");
        } catch (ApplicationException e) {
            assertEquals("Cannot process the student csv file, file is null", e.getMessage());
        }
    }

    @Test
    public void testReadCSV_file_doesnt_exist() {
        StudentCsvReader studentCsvReader = new StudentCsvReader();
        try {
            studentCsvReader.readCsvFile(new File("somefile.csv"));
            fail("Should not reach here exception");
        } catch (ApplicationException e) {
            assertEquals("Cannot process the student csv file, file doesn't exist", e.getMessage());
        }
    }

    @Test
    public void testReadCSV_file_happy_path() {
        StudentCsvReader studentCsvReader = new StudentCsvReader();
        try {

            List<Student> studentsList = studentCsvReader.readCsvFile(new File("src/test/resources/InternationalHighSchoolStudentRecords.csv"));
            assertNotNull(studentsList);
            assertEquals(50, studentsList.size());
            {
                Student student = studentsList.get(0);
                assertEquals(1001, student.getStudentId());
                assertEquals("Alice", student.getFirstName());
            }
            {
                Student student = studentsList.get(1);
                assertEquals(-1, student.getStudentId());
                assertEquals("Eva", student.getFirstName());
            }
        } catch (ApplicationException e) {
            fail("Should not reach here exception");
        }
    }

}
