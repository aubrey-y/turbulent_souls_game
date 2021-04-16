package org.example.services;

import com.google.api.client.http.HttpTransport;
import com.google.api.services.gmail.Gmail;

import com.google.api.services.gmail.model.Message;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

public class EmailService {

    private HttpTransport httpTransport;

    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String APPLICATION_NAME = "Turbulent Souls";

    public EmailService(HttpTransport httpTransport) {
        this.httpTransport = httpTransport;
    }

    public boolean sendEmail(String targetEmail, String code)
            throws MessagingException, IOException {
        Gmail gmail = this.getGmail();
        Message message = this.createMessageWithEmail(
                this.createEmail(
                        targetEmail,
                        System.getenv("GOOGLE_EMAIL"),
                        "Your Authentication Code",
                        "Here is your authentication code: " + code
                                + "\n\nDo not share this code with anyone. Turbulent Souls Ltd "
                                + "will never ask you for this code."
                                + "\n\nEnter this code in the same place you entered your email."
                                + "\n\nIf you are not the intended recipient of this email, "
                                + "please ignore this communication or report this incident "
                                + "by replying to this email.")
        );
        return this.sendMessage(gmail, System.getenv("GOOGLE_EMAIL"), message);
    }

    private Gmail getGmail() {
        Credential credential = new GoogleCredential.Builder()
                .setTransport(this.httpTransport)
                .setJsonFactory(JSON_FACTORY)
                .setClientSecrets(
                        System.getenv("GCP_CLIENT_ID"),
                        System.getenv("GCP_CLIENT_SECRET"))
                .build()
                .setAccessToken(System.getenv("GCP_ACCESS_TOKEN"))
                .setRefreshToken(System.getenv("GCP_REFRESH_TOKEN"));

        return new Gmail.Builder(this.httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Create a MimeMessage using the parameters provided.
     *
     * @param to email address of the receiver
     * @param from email address of the sender, the mailbox account
     * @param subject subject of the email
     * @param bodyText body text of the email
     * @return the MimeMessage to be used to send email
     * @throws MessagingException if email contains incorrect message
     */
    public MimeMessage createEmail(String to,
                                          String from,
                                          String subject,
                                          String bodyText)
            throws MessagingException {
        Session session = Session.getDefaultInstance(new Properties(), null);

        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO,
                new InternetAddress(to));
        email.setSubject(subject);
        email.setText(bodyText);
        return email;
    }

    /**
     * Create a message from an email.
     *
     * @param emailContent Email to be set to raw of message
     * @return a message containing a base64url encoded email
     * @throws IOException if creating email encounters error
     * @throws MessagingException if email contains incorrect message
     */
    public Message createMessageWithEmail(MimeMessage emailContent)
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
     * @param userId User's email address. The special value "me"
     * can be used to indicate the authenticated user.
     * @param emailContent Email to be sent.
     * @return whether or not message was sent
     * @throws MessagingException if email contains incorrect message
     * @throws IOException sending email encounters error
     */
    public boolean sendMessage(Gmail service,
                                      String userId,
                                      Message emailContent) throws IOException {
        Message message = service.users().messages().send(userId, emailContent).execute();

        System.out.println("Message id: " + message.getId());
        System.out.println(message.toPrettyString());
        return message.getLabelIds().contains("SENT");
    }
}
