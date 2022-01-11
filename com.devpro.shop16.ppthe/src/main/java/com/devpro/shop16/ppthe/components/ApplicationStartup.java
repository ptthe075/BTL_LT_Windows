package com.devpro.shop16.ppthe.components;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component // Bean
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	/**
	 * Khi ứng dụng chạy xong sẽ chạy vào hàm này
	 */
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("--------- BEGIN ---------");
		System.out.println("--------- END ---------");
	}

}
