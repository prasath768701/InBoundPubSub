package com.pubsub.demo.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;

@Controller
public class PubsubDemoController {

	String message;
	
	@GetMapping("getMessage")
	public String getMessage(Model m) {
		
		m.addAttribute("res", message);
		
		return "result";
		
	}
	
	// Step 1
	// Create an inbound channel adapter to listen to the subscription `sub-one` and send
	// messages to the input message channel.
	@Bean
	public PubSubInboundChannelAdapter messageAdapter (
			@Qualifier("inputChannel") MessageChannel inputChannel,
			PubSubTemplate template
			) {
		PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(template, "topic-workout-sub");
		adapter.setOutputChannel(inputChannel);
		return adapter;
	}
	// after pulling the data from subscription it sends to inputchannel 
	// Step 2
	@Bean
	MessageChannel inputChannel() {
		return new DirectChannel();
	}
	// Step 3
	@ServiceActivator(inputChannel = "inputChannel")
	public void receiveMessage(String payload)
	{
		this.message=payload;
	}
	
	
}
