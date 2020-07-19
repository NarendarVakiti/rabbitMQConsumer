package com.rabbitmq.consumer.service;

import com.rabbitmq.consumer.bean.ResourceInfo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class RabbitMQConsumer {

	@RabbitListener(queues = "${test.resource.queue}")
	public void consumeResource(ResourceInfo resourceInfo) {
		System.out.println("Recieved Message From RabbitMQ: " + resourceInfo);

		String uri = resourceInfo.getResourceURL();
		String response = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<String> result  = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
			response = result.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Response :: "+response);
	}
}