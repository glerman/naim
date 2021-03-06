package mail;

import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import view.FormattedOutput;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Sender {

  private static final String naimSecret = "client_secret_naim.json";
  private static final String galSecret = "client_secret_gal.json";
  private final static String galEmail = "gal.lerman1@gmail.com";
  private final static String naimEmail = "<info@naim.org.il> סטודיו נעים";

  private final Gmail gmail;
  private final String fromEmail;

  public Sender(final boolean sendFromNaim) throws IOException {
    fromEmail = sendFromNaim ? naimEmail : galEmail;
    String clientSecretFileName = sendFromNaim ? naimSecret : galSecret;
    gmail = GmailServiceProvider.getGmailService(clientSecretFileName);
  }


  public void sendMail(String to,
                       FormattedOutput formattedTeacherOutput) throws MessagingException, IOException {

    MimeMessage email = createEmail(to, fromEmail, formattedTeacherOutput);
    sendMessage(gmail, email);
  }

  /**
   * Create a MimeMessage using the parameters provided.
   *
   * @param to email address of the receiver
   * @param from email address of the sender, the mailbox account
   * @return the MimeMessage to be used to send email
   */
  private static MimeMessage createEmail(String to,
                                         String from,
                                         FormattedOutput formattedTeacherOutput) throws MessagingException {
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    MimeMessage email = new MimeMessage(session);
    email.setFrom(new InternetAddress(from));
    email.addRecipient(javax.mail.Message.RecipientType.TO,
            new InternetAddress(to));
    email.setSubject(formattedTeacherOutput.subject());
    email.setContent(formattedTeacherOutput.entireHtml(), "text/html");
    return email;
  }

  /**
   * Create a message from an email.
   *
   * @param emailContent Email to be set to raw of message
   * @return a message containing a base64url encoded email
   */
  private static Message createMessageWithEmail(MimeMessage emailContent)
          throws MessagingException, IOException {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    emailContent.writeTo(buffer);
    byte[] bytes = buffer.toByteArray();
    String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
    Message message = new Message();
    message.setRaw(encodedEmail);
    return message;
  }

  /**
   * Send an email from the user's mailbox to its recipient.
   *
   * @param service Authorized Gmail API instance.
   * @param emailContent Email to be sent.
   */
  private static void sendMessage(Gmail service,
                                  MimeMessage emailContent)
          throws MessagingException, IOException {
    Message message = createMessageWithEmail(emailContent);
    service.users().messages().send("me", message).execute();
  }
}
