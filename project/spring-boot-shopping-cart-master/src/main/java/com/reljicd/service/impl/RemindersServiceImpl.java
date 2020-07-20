package com.reljicd.service.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reljicd.model.Form;
import com.reljicd.repository.FormRepository;
import com.reljicd.repository.UserRepository;
import com.reljicd.service.*;

@Service
public class RemindersServiceImpl implements RemindersService {

	private final FormRepository formRepository;
	private final EmailService emailService;

    @Autowired
    public RemindersServiceImpl(FormRepository formRepository, EmailService emailService) {
        this.formRepository = formRepository;
        this.emailService = emailService;
    }
	
	@Override
	public void sendAllReminders() throws MessagingException {
		List<Form> allOpenForms = formRepository.findAllByStatus("OPEN");
		for (Form form: allOpenForms) {
			emailService.sendNextMessage(form.getId());
		}
		if (allOpenForms.isEmpty()) System.out.println("no forms retrieved");
		
	}
	
}