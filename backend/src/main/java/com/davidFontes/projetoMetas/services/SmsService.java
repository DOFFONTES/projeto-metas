package com.davidFontes.projetoMetas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.davidFontes.projetoMetas.entities.Venda;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;
	
	@Autowired
	private VendaService vendaService;

	public void enviaSms(Long id) {
		
		Venda obj = vendaService.buscaPorId(id);
		
		String data = obj.getData().getMonth() + "/" + obj.getData().getYear();
		
		String msg = "O vendedor " + obj.getVendedor() + " foi destaque em " + data + ", com um total de R$ " + String.format("%.2f", obj.getTotal()) + ".";

		Twilio.init(twilioSid, twilioKey);

		PhoneNumber destinatario = new PhoneNumber(twilioPhoneTo);
		PhoneNumber remetente = new PhoneNumber(twilioPhoneFrom);

		Message messagem = Message.creator(destinatario, remetente, msg).create();

		System.out.println(messagem.getSid());
	}
}
