package edu.aua.talents.utils;

import edu.aua.common.model.EmailDTO;
import edu.aua.talents.persistance.Talent;

public class EmailGenerator {
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
