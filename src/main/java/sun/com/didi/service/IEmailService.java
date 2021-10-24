package sun.com.didi.service;

public interface IEmailService {
	/**
	 * 
	* @Description 发送简单纯文字邮件
	* @param to 收件人地址
	* @param content 邮件内容<br>
	* @return boolean 成功返回true，失败返回false
	 */
	 boolean sendAttachmentMail(String to, String content);

}
