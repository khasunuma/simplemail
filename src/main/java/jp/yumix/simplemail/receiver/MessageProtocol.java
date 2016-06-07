package jp.yumix.simplemail.receiver;

/**
 * @author Yumi Hiraoka - yumix at outlook.com
 *
 */
public enum MessageProtocol {
	
	/**
	 * Protocol 'POP3'.
	 */
	POP3("pop3"), 
	
	/**
	 * Protocol 'POP3 over SSL'.
	 */
	POP3_OVER_SSL("pop3s"),
	
	/**
	 * Protocol 'IMAP4'.
	 */
	IMAP("imap"),
	
	/**
	 * Protocol 'IMAP4 over SSL'.
	 */
	IMAP_OVER_SSL("imaps");
	
	/**
	 * Protocol name for JavaMail API.
	 */
	private final String name;
	
	/**
	 * @param name
	 */
	MessageProtocol(String name) {
		this.name = name;
	}
	
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}
}
