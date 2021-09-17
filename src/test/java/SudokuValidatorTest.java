import com.kiva.sudoku.util.FileUtil;
import com.kiva.sudoku.validator.SudokuValidator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuValidatorTest {

    @Test
    void givenAnArgumentWithCorrectPatternThenReturnTrue() {

        String arg = "--file=/home/csv.csv";

        boolean fileArgumentPatternValid = FileUtil.isFileArgumentPatternValid(arg);

        assertTrue(fileArgumentPatternValid);
    }

    @Test
    void givenAnArgumentWithWrongPatternThenReturnIllegalArgumentException() {

        String arg = "file=/home/csv.csv";

        assertThrows(IllegalArgumentException.class, () -> FileUtil.isFileArgumentPatternValid(arg));
    }

    @Test
    void givenAnArgumentWithCorrectPathThenReturnOnlyPath() {
        String arg = "--file=/home/csv.csv";

        Path pathFromArgument = FileUtil.getPathFromArgument(arg);

        assertNotNull(pathFromArgument);
    }

    @Test
    void givenAnArgumentWithWrongPathThenReturnIllegalArgumentException() {
        String arg = "--file /home/csv.csv";

        assertThrows(IllegalArgumentException.class, () -> FileUtil.getPathFromArgument(arg));
    }

    @Test
    void givenACSVFileThatExistsThenReturnContent() throws URISyntaxException, IOException {
        URL resource = this.getClass().getResource("example_valid.csv");

        List<String> fileContent = FileUtil.readCSVFileContent(Paths.get(resource.toURI()));

        assertEquals(9, fileContent.size());
    }

    @Test
    void givenACSVFileThatNotExistsThenReturnContent()  {

        assertThrows(IllegalArgumentException.class, () -> FileUtil.readCSVFileContent(Paths.get(URI.create("fake"))));
    }

    @Test
    void givenAValidSudokuCSVThenReturnTrue() throws URISyntaxException, IOException {
        URL resource = this.getClass().getResource("example_valid.csv");

        List<String> fileContent = FileUtil.readCSVFileContent(Paths.get(resource.toURI()));

        var sudokuValidator = new SudokuValidator(fileContent);

        assertTrue(sudokuValidator.isValid());
    }

    @Test
    void givenAInvalidSudokuCSVThenReturnFalse() throws URISyntaxException, IOException {
        URL resource = this.getClass().getResource("example_invalid.csv");

        List<String> fileContent = FileUtil.readCSVFileContent(Paths.get(resource.toURI()));

        var sudokuValidator = new SudokuValidator(fileContent);

        assertFalse(sudokuValidator.isValid());
    }

}
