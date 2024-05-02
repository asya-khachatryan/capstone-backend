package edu.aua.onboardingservice.facade.impl;

import edu.aua.onboardingservice.annotation.Facade;
import edu.aua.onboardingservice.client.jiraclient.JiraIntegrationClientFacade;
import edu.aua.onboardingservice.client.jiraclient.user.dto.JiraUserDto;
import edu.aua.onboardingservice.client.mailclient.MailGenerator;
import edu.aua.onboardingservice.converter.MenteeConverter;
import edu.aua.onboardingservice.converter.MentorConverter;
import edu.aua.onboardingservice.facade.UserFacade;
import edu.aua.onboardingservice.persistance.entity.Mentee;
import edu.aua.onboardingservice.persistance.entity.Mentor;
import edu.aua.onboardingservice.service.MenteeService;
import edu.aua.onboardingservice.service.MentorService;
import edu.aua.onboardingservice.service.dto.MenteeDto;
import edu.aua.onboardingservice.service.dto.MentorDto;
import edu.aua.onboardingservice.thymeleafTemplate.ITemplateResolverConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Facade
public class UserFacadeImpl implements UserFacade {

    private final static Logger log = LoggerFactory.getLogger(UserFacadeImpl.class);

    private final JiraIntegrationClientFacade jiraClientFacade;
    private final MenteeService menteeService;
    private final MentorService mentorService;
    private final MenteeConverter menteeConverter;
    private final MentorConverter mentorConverter;
//    private final MailSenderClient mailSenderClient;
//    private final MailGenerator mailGenerator = new MailGenerator(new ITemplateResolverConfig().thymeleafTemplateEngine());


    public UserFacadeImpl(final JiraIntegrationClientFacade jiraClientFacade,
                          final MenteeService menteeService,
                          final MentorService mentorService,
                          final MenteeConverter menteeConverter,
                          final MentorConverter mentorConverter
//            ,
//                          final MailSenderClient mailSenderClient
    ) {
        this.jiraClientFacade = jiraClientFacade;
        this.menteeService = menteeService;
        this.mentorService = mentorService;
        this.menteeConverter = menteeConverter;
        this.mentorConverter = mentorConverter;
//        this.mailSenderClient = mailSenderClient;
    }

    @Override
    public MenteeDto createMentee(final MenteeDto menteeDto) {
        log.info("Start mentee creating");
        final JiraUserDto jiraUser = this.jiraClientFacade.createUser(
                new JiraUserDto(menteeDto.getEmail(), menteeDto.getDisplayName()));
        final Mentee mentee = this.menteeService.create(menteeDto, jiraUser);
//        mailSenderClient.sendEmail(mailGenerator.firstMeetingMailGenerator(mentee));
        log.info("Finished mentee creating");
        return this.menteeConverter.convertToDto(mentee);
    }

    @Override
    public MenteeDto findMenteeById(final Long id) {
        final Mentee mentee = this.menteeService.findById(id);
        return this.menteeConverter.convertToDto(mentee);
    }

    @Override
    public MentorDto findMentorById(final Long id) {
        final Mentor mentor = this.mentorService.findById(id);
        return this.mentorConverter.convertToDTO(mentor);
    }

    @Override
    public List<MenteeDto> getAllMentees() {
        return this.menteeConverter.bulkConvertToDto(this.menteeService.findALl());
    }

    @Override
    public List<MentorDto> getAllMentors() {
        return this.mentorConverter.bulkConvertToDTO(this.mentorService.findALl());
    }

    @Override
    public boolean deleteMentee(final Long id) {
        return this.menteeService.deleteById(id);
    }

    @Override
    public boolean deleteMentor(final Long id) {
        return this.mentorService.deleteById(id);
    }
}
