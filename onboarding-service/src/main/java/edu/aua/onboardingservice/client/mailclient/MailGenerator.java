package edu.aua.onboardingservice.client.mailclient;

import edu.aua.onboardingservice.persistance.entity.Mentee;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class MailGenerator {
    public static String sprintCheckupSubject = "Talent Journey: Sprint checkup time";
    public static String firstMeetingSubject = "Talent Journey: Meeting with the CEO";

    private final SpringTemplateEngine thymeleafTemplateEngine;

    public MailGenerator(SpringTemplateEngine thymeleafTemplateEngine) {
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }

//    public MailDTO firstMeetingMailGenerator(Mentee mentee) {
//        Context thymeleafContext = new Context();
//        thymeleafContext.setVariable("name", mentee.getFirstName());
//        return MailDTO.builder()
//                .emailTo(mentee.getEmail())
//                .subject(firstMeetingSubject)
//                .text(thymeleafTemplateEngine.process("mail_to_talent.html", thymeleafContext))
//                .build();
//    }
//
//    public MailDTO sprintCheckupMailGenerator(Mentee mentee) {
//        Context thymeleafContext = new Context();
//        return MailDTO.builder()
//                .emailTo(mentee.getEmail())
//                .subject(sprintCheckupSubject)
//                .text(thymeleafTemplateEngine.process("mail_to_talent.html", thymeleafContext))
//                .build();
//    }

    //TODO change the texts
}
