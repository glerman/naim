package parse;

import com.google.common.collect.Lists;
import dnl.utils.text.table.TextTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.InputReaderHelper;

import java.util.List;

//todo: add content test
public class CsvParserTest {

  private static final List<Integer> ALLOWED_EMPTY_COLUMNS = Lists.newArrayList(2);

  private CsvParser csvParser;

  @Before
  public void setUp() throws Exception {
    csvParser = new CsvParser();
  }

  @Test
  public void testSalaries() throws Exception {

    List<String> salaryLines = InputReaderHelper.readLines(InputReaderHelper.salariesFilePath);
    CsvResult result = csvParser.parse(salaryLines);

    Assert.assertEquals(12, result.header.length);
    Assert.assertEquals(120, result.data.length);

    doTest(result);
  }

  @Test
  public void testTeachers() throws Exception {

    List<String> teacherLines = InputReaderHelper.readLines(InputReaderHelper.teachersFilePath);
    CsvResult result = csvParser.parse(teacherLines);

    Assert.assertEquals(2, result.header.length);
    Assert.assertEquals(24, result.data.length);

    doTest(result);
  }


  @Test
  public void testMessages() throws Exception {

    List<String> teacherLines = InputReaderHelper.readLines(InputReaderHelper.teacherMessagesFilePath);
    CsvResult result = csvParser.parse(teacherLines);

    Assert.assertEquals(2, result.header.length);
    Assert.assertEquals(8, result.data.length);

    doTest(result);
  }

  private void doTest(CsvResult result) {
    for (int i = 0; i < result.data.length; i++) {
      Assert.assertNotNull("Data row is null at row #" + i, result.data[i]);
      Assert.assertEquals("Data row length is unexpected at row #" + i + "\n" +result.data[i], result.header.length, result.data[i].length);
      for (int j = 0 ; j < result.header.length; j++ ) {
        Assert.assertNotNull("Data element (" + i + "," + j + ") is null", result.data[i][j]);
        if (!ALLOWED_EMPTY_COLUMNS.contains(j)) {
          Assert.assertFalse("Data element (" + i + "," + j + ") is empty", ((String)result.data[i][j]).isEmpty());
        }
      }
    }
    new TextTable(result.header, result.data).printTable();
  }
}
