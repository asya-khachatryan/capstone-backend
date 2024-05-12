package edu.aua.interviews.service;

import edu.aua.interviews.persistance.EmailTextType;
import edu.aua.interviews.persistance.Interviewer;
import edu.aua.talents.persistance.Talent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class EmailTextGenerator {
    private final SpringTemplateEngine thymeleafTemplateEngine;

    private static final String subject = "Interview invitation with Talent_Journey company";

    public static String getSubject() {
        return subject;
    }

    public String getEmailText(Talent talent, Interviewer interviewer, EmailTextType emailTextType, URI uri) {
        Context thymeleafContext = new Context();
        switch (emailTextType) {
            case TO_TALENT_FIRST:
                thymeleafContext.setVariable("link", uri);
                thymeleafContext.setVariable("name", talent.getName());
                return thymeleafTemplateEngine.process("mail_to_talent.html", thymeleafContext);

            case TO_TALENT:
                thymeleafContext.setVariable("link", uri);
                thymeleafContext.setVariable("name", talent.getName());
                return thymeleafTemplateEngine.process("mail_to_talent_again.html", thymeleafContext);

            case TO_USER:
                thymeleafContext.setVariable("name", interviewer.getFirstName());
                return thymeleafTemplateEngine.process("mail_to_user.html", thymeleafContext);

            default:
                return "";

        }
    }


}
