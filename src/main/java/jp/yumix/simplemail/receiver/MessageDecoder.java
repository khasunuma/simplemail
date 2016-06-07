package jp.yumix.simplemail.receiver;

import javax.mail.Message;

/**
 * @author Yumi Hiraoka - yumix at outlook.com
 *
 */
public interface MessageDecoder<E> {

    /**
     * @param message
     * @return
     * @throws MessageDecodeException
     */
    E decode(Message message) throws MessageDecodeException;
}
