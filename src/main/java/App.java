import logic.AppLogic;
import logic.AppLogic.TeachersToIterate;
import report.ReportAggregator;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Created by glerman on 24/9/17.
 */
//todo: add proper logging instead of sout
public class App {

  public static void main(String[] args) throws IOException, MessagingException {

    try {
      runApp(args);
    } catch (Throwable t) {
      ReportAggregator.instance.unexpectedError(t);
    } finally {
      System.out.println();
      System.out.println();
      System.out.println(ReportAggregator.instance.report(true));
    }
  }

  private static void runApp(String[] args) {
    ReportAggregator.instance.appInput(args);
    String salariesFilePath = args[0];
    String teacherFilePath = args[1];
    String charset = args[2];
    boolean sendMails = Boolean.valueOf(args[3]);
    boolean sendFromNaim = Boolean.valueOf(args[4]);
    TeachersToIterate teachersToIterate = TeachersToIterate.valueOf(args[5]);
    String receiptTo = args[6];
    String messagesPath = args[7];
    String messagesCharset = args[8];

    AppLogic appLogic = new AppLogic();
    appLogic.start(salariesFilePath, teacherFilePath, messagesPath, charset,
            sendMails, sendFromNaim, teachersToIterate, receiptTo, messagesCharset);
  }

}
