//package edu.aua.talents.service.utils;
//
//import edu.aua.talents.persistance.entity.Talent;
//
//public class MailGenerator {
//    public static MailDTO mailGenerator(Talent talent) {
//        return MailDTO.builder()
//                .emailTo(talent.getEmail())
//                .subject("Thank you for applying")
//                .text(generateMailText(talent.getName()))
//                .build();
//    }
//
//    private static String generateMailText(String name) {
//        return "Hello " + name + ",\n" +
//                "Thank you for applying.\n" +
//                "\n" +
//                "I’d like to inform you that we received your application. Our hiring team is currently reviewing all applications and we are planning to schedule interviews. If you are among qualified candidates, you will receive a call from  one of our recruiters to schedule an interview. In any case, we will keep you posted on the status of your application.\n" +
//                "\n" +
//                "Once again thanks for taking the time to apply.\n" +
//                "\n" +
//                "Best regards,\n" +
//                "Talent Journey";
//    }
//}
