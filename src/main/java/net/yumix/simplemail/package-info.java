/**
 * <h2>Simple Mail Library</h2>
 * <p>This is a wrapper for Jakarta Mail (ex. JavaMail) API.</p>
 * 
 * <h3>Getting started</h3>
 * 
 * <p>To send a mail on the code, write such as:</p>
 * <pre>
 * var config = new SMTPConfig();
 * config.setHost("hostname");
 * config.setFrom("yumix@example.com");
 * config.setAuth(true);
 * config.setAuthenticate("username", "password");
 * 
 * Mail.create(config)
 *     .from("yumix@example.com")
 *     .to("your@example.org")
 *     .subject("Test")
 *     .text(() -> "Hello, world")
 *     .send();
 * </pre>
 * 
 * <p>To receive mails on the code, write such as:</p>
 * <pre>
 * var config = new IMAPConfig();
 * config.setHost("hostname");
 * config.setAuthenticate("username", "password");
 * 
 * try (var mailbox = MailBox.open(config)) {
 *     try (var inbox = mailbox.getFolder("INBOX")) {
 *         for (var mail : inbox.listMessages()) {
 *             System.out.println("From: " + mail.getFrom());
 *             System.out.println("Subject: " + mail.getSubject());
 *             System.out.println(mail.getText().getData());
 *             System.out.println("[Attachment: " + mail.getAttachments().size() + " file(s)]");
 *             System.out.println();
 *         }
 *     }
 * }
 * </pre>
 * 
 * @author Yumi Hiraoka
 */
package net.yumix.simplemail;