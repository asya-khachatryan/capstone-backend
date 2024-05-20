package edu.aua.interviews.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class EmailTextGenerator {
    private final SpringTemplateEngine thymeleafTemplateEngine;

    public static final String INTERVIEW_INVITATION_SUBJECT = "Congratulations! You're Invited to an interview";
    public static final String INTERVIEW_CONFIRMATION_SUBJECT = "Talent Journey: Interview confirmed";
    public static final String INTERVIEW_RESCHEDULING_SUBJECT = "Talent Journey: Interview to be rescheduled";


    private static final String INTERVIEW_INVITATION_TEXT = "Dear %s,\n" +
            "\n" +
            "I hope this email finds you well. I am writing to extend an invitation for an interview for the %s role at Talent Journey. We were impressed by your resume and believe that you could be a valuable addition to our team.\n" +
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

    private static final String INTERVIEW_CONFIRMATION_TEXT = "Dear %s,\n" +
            "\n" +
            "I hope this email finds you well.\n" +
            "\n" +
            "We are pleased to confirm your interview for the %s position at Talent Journey. Please find the details below:\n" +
            "\n" +
            "Date and time: %s\n" +
            "Duration: %s\n" +
            "Location: American University of Armenia, Baghramyan 40\n" +
            "Interviewers: %s\n" +
            "\n" +
            "If you have any questions or need further assistance, please feel free to contact us.\n" +
            "\n" +
            "We look forward to meeting you.\n" +
            "\n" +
            "Best regards,\n" +
            "Talent Journey";

    private static final String INTERVIEW_RESCHEDULING_TEXT = "Dear %s,\n" +
            "\n" +
            "I hope this email finds you well.\n" +
            "\n" +
            "We have been informed that you have requested to reschedule your upcoming interview with Talent Journey for the %s position.\n" +
            "To proceed, please use the following Calendly link to choose a new suitable time for the interview:\n" +
            "\n" +
            "%s\n" +
            "\n" +
            "We look forward to meeting with you at the rescheduled time.\n" +
            "\n" +
            "Thank you for your cooperation.\n" +
            "\n" +
            "Best regards,\n" +
            "Talent Journey";

    public static String generateInterviewInvitationEmailText(
            String candidateFullName,
            String specializationName,
            String calendlyURL,
            String hrManagerEmail,
            String hrManagerFullName) {
        return String.format(INTERVIEW_INVITATION_TEXT, candidateFullName, specializationName, calendlyURL, hrManagerEmail, hrManagerFullName, hrManagerEmail);
    }

    public static String generateInterviewConfirmationEmailText(
            String candidateFullName,
            String specializationName,
            String dateAndTime,
            String duration,
            String interviewers) {
        return String.format(INTERVIEW_CONFIRMATION_TEXT, candidateFullName, specializationName,
                dateAndTime, duration, interviewers);
    }

    public static String generateInterviewRescheduleEmailText(
            String candidateFullName,
            String specializationName,
            String calendlyURL
    ) {
        return String.format(INTERVIEW_RESCHEDULING_TEXT, candidateFullName, specializationName,
                calendlyURL);
    }
}
