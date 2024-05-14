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

    public static final String INTERVIEW_INVITATION_SUBJECT = "Congratulations! You're Invited to Interview";

    private static final String INTERVIEW_INVITATION_TEXT = "Dear %s,\n" +
            "\n" +
            "I hope this email finds you well. I am writing to extend an invitation for an interview for the %s role at Talent Journey. We were impressed by your resume and believe that you could be a valuable addition to our team.\n" +
            "\n" +
            "The interview will provide an opportunity for us to discuss your qualifications further, as well as for you to learn more about our company culture, values, and the role itself.\n" +
            "\n" +
            "To schedule a convenient time for the interview, please use the link below to access our Calendly scheduling tool:\n" +
            "\n" +
            "%s\n" +
            "\n" +
            "Calendly will display my availability, allowing you to choose a time slot that works best for you. If you encounter any difficulties or require further assistance, please do not hesitate to reach out to me directly.\n" +
            "\n" +
            "We look forward to meeting with you and discussing how your skills and experience align with the needs of our team. If you have any questions prior to the interview, feel free to contact me at %s.\n" +
            "\n" +
            "Thank you for considering this opportunity, and we anticipate a fruitful discussion ahead.\n" +
            "\n" +
            "Warm regards,\n" +
            "\n" +
            "%s\n" +
            "HR Manager\n" +
            "Talent Journey\n" +
            "%s";

    public static String generateInterviewInvitationEmailText(
            String candidateFullName,
            String specializationName,
            String calendlyURL,
            String hrManagerEmail,
            String hrManagerFullName) {
        return String.format(candidateFullName, specializationName, calendlyURL, hrManagerEmail, hrManagerFullName, hrManagerEmail);
    }

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
