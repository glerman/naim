import logic.AppLogic;
import org.junit.Test;
import util.InputReaderHelper;

public class AppTest {

  @Test
  public void testSystemWeak() throws Exception {
    String sendMails = "false";
    String sendFromNaim = "false";
    String[] args = {
            InputReaderHelper.salariesFilePath,
            InputReaderHelper.teachersFilePath,
            "UTF-8",
            sendMails,
            sendFromNaim,
            AppLogic.TeachersToIterate.ONE.toString(),
            "הגברת שושנה",
            InputReaderHelper.teacherMessagesFilePath
    };
    App.main(args);
  }
}
