package edu.aua.onboardingservice.util;

public class EmailMessages {
    private EmailMessages() {

    }

    public static String ONBOARDING_DOCUMENT_EMAIL_SUBJECT = "Onboarding Setup Information: Welcome to the team!";

    private static String ONBOARDING_DOCUMENT_EMAIL_TEXT = "Dear %s,\n" +
            "I hope this email finds you well. \n" +
            "\n" +
            "On behalf of the entire team, I want to extend a warm welcome to you! We are thrilled to have you join our team and are looking forward to the valuable contributions you will bring to our organization.\n" +
            "\n" +
            "As you begin your journey with us, it's essential to ensure that you have all the necessary information and resources to hit the ground running. We’ve compiled a list of resources as a part of our onboarding checklist that we believe will help you better understand our company, its culture, and your role. Please check out the url below where you will find detailed information about the next steps you should take and all the setups you should have for your onboarding process. " +
            "%s" +
            "\n\nPlease review these materials at your convenience and feel free to reach out if you have any questions or if there’s anything you’d like to discuss further.\n\nBest,\n%s\nHR Specialist, Talent Journey";

    public static String generateOnboardingEmailText(String employeeName, String documentUrl, String hrManagerName) {
        return String.format(ONBOARDING_DOCUMENT_EMAIL_TEXT, employeeName, documentUrl, hrManagerName);
    }

}
