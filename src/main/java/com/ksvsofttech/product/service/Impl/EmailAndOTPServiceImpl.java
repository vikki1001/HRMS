package com.ksvsofttech.product.service.Impl;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.service.EmailAndOTPService;

@Service
public class EmailAndOTPServiceImpl implements EmailAndOTPService {
	private static final Logger LOGGER = LogManager.getLogger(EmailAndOTPServiceImpl.class);

	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private int port;
	@Value("${spring.mail.username}")
	private String username;
	@Value("${spring.mail.password}")
	private String password;
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String smtpAuth;
	@Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
	private String smtpConnectiontimeout;
	@Value("${spring.mail.properties.mail.smtp.timeout}")
	private String smtpTimeout;
	@Value("${spring.mail.properties.mail.smtp.writetimeout}")
	private String smtpWritetimeout;
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String smtpStatttlsEnable;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(host);
		javaMailSenderImpl.setPort(port);
		javaMailSenderImpl.setUsername(username);
		javaMailSenderImpl.setPassword(password);

		Properties props = javaMailSenderImpl.getJavaMailProperties();
		props.put("mail.smtp.auth", smtpAuth);
		props.put("mail.smtp.connectiontimeout", smtpConnectiontimeout);
		props.put("mail.smtp.timeout", smtpTimeout);
		props.put("mail.smtp.writetimeout", smtpWritetimeout);
		props.put("mail.smtp.starttls.enable", smtpStatttlsEnable);

		return javaMailSenderImpl;
	}

	@Override
	public void emailSend(String from, String to, String subject, String body, String cc) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setCc(cc);

			javaMailSender.send(message);
		} catch (Exception e) {
			LOGGER.error("Error occur while send mail ..." + ExceptionUtils.getStackTrace(e));
		}
	}

	@Override
	public void emailsend(String from, String to, String subject, String body) throws Exception {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setFrom(from);
			helper.setTo(to);
			

			javaMailSender.send(message);
		} catch (Exception e) {
			LOGGER.error("Error occur while send mail ..." + ExceptionUtils.getStackTrace(e));
		}
	}

}
