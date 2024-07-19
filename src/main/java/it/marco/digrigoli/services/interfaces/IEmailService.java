package it.marco.digrigoli.services.interfaces;

import org.springframework.beans.factory.annotation.Value;

public interface IEmailService {
	
	public void sendSimpleMessage(String to, String subject, String text);
	
}
