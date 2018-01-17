package com.zoxal.queuebrain.security.api;

import com.zoxal.queuebrain.exceptions.ExceptionFactory;
import com.zoxal.queuebrain.model.User;
import com.zoxal.queuebrain.repository.UserRepository;
import com.zoxal.queuebrain.security.dto.LoginPassLoginRequest;
import com.zoxal.queuebrain.security.utils.RegistrationLinkGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Insert description vere
 *
 * @author Mike
 * @version 01/08/2018
 */
@RestController
@CrossOrigin
@RequestMapping("/registration")
//@Transactional
public class RegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    @Autowired
    public RegistrationController(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    @PostMapping
    @RequestMapping(params = {"type=loginpass"})
    public void register(@RequestBody LoginPassLoginRequest request) {
        try {
            logger.debug("Registration request");
            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setActive(false);
            user.setRegistrationLink(RegistrationLinkGenerator.generate());
            userRepository.save(user);

            MimeMessage message = templateMail(mailSender);
            try {
                message.setContent(getRegistrationMailBody(user.getName(), user.getRegistrationLink()), "text/html; charest=utf-8");
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            } catch (MessagingException e) {
                throw ExceptionFactory.internal("Can not set user registration email parameters");
            }
            mailSender.send(message);
            logger.debug("Registration request completed");
        } catch (Exception e) {
            logger.warn("Wowo..", e);
        }
    }

    private static String getRegistrationMailBody(String userName, String reglink) {
        return "Hello, " + userName + "!<br><br>"
                + "You are about to register in QueueBrain app. To complete registration process, please, navigate "
                + "<a href=https://qbrain.of.by/regcomplete?reglink=" + reglink + ">here</a>.<br><br>"
                + "If you didn't ask for registration, just ignore this message.<br><br><hr>"
                + "With love,<br>your QueueBrain team.";
    }

    public MimeMessage templateMail(JavaMailSender sender) {
        MimeMessage templateMail = sender.createMimeMessage();
        try {
            templateMail.setFrom("qbrain.team@gmail.com");
            templateMail.setSubject("Welcome to QueueBrain!");
        } catch (MessagingException e) {
            logger.error("Can not set template mail parameters", e);
            throw new RuntimeException(e);
        }
        return templateMail;
    }
}
