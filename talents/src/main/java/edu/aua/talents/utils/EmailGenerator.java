package edu.aua.talents.utils;

import edu.aua.common.model.EmailDTO;
import edu.aua.talents.persistance.Talent;

public class EmailGenerator {

    public static String REJECTION_SUBJECT_LINE = "Application Update: %s Position at Talent Journey";

    private static String REJECTION_EMAIL_BODY = "Dear %s,\n" +
            "\n" +
            "Thank you for your interest in the %s position at Talent Journey. We appreciate the time and effort you invested in the application process.\n" +
            "\n" +
            "After careful consideration, we regret to inform you that we have decided not to move forward with your application at this time. While we received many qualified applicants, we have selected candidates whose skills and experience more closely align with the needs of the role.\n" +
            "\n" +
            "We want to express our gratitude for your interest in joining our team. We encourage you to keep an eye on our career opportunities for future openings that may align with your background and career aspirations.\n" +
            "\n" +
            "Thank you again for considering Talent Journey as a potential employer. We wish you all the best in your job search and future endeavors.\n" +
            "\n" +
            "Sincerely,\n" +
            "%s\n" +
            "HR Specialist\n" +
            "Talent Journey";


    public static String generateRejectionEmail(String applicantName, String specialization, String hrManagerName) {
        return String.format(REJECTION_EMAIL_BODY, applicantName, specialization, hrManagerName);
    }

    public static EmailDTO generateEmail(Talent talent) {
        return EmailDTO.builder()
                .emailTo(talent.getEmail())
                .subject("Thank you for applying")
                .text(generateEmailText(talent.getName()))
                .build();
    }

    private static String generateEmailText(String name) {
        return "Hello " + name + ",\n" +
                "Thank you for applying.\n" +
                "\n" +
                "I’d like to inform you that we received your application. Our hiring team is currently reviewing all applications and we are planning to schedule interviews. If you are among qualified candidates, you will receive a call from  one of our recruiters to schedule an interview. In any case, we will keep you posted on the status of your application.\n" +
                "\n" +
                "Once again thanks for taking the time to apply.\n" +
                "\n" +
                "Best regards,\n" +
                "Talent Journey";
    }
}
