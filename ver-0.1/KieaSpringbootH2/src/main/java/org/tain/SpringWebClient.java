package org.tain;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

public class SpringWebClient {

	private static boolean flag = true;
	
	public static void main(String[] args) throws Exception {
		if (flag) test01();
	}
	
	private static void test01() throws Exception {
		WebClient webClient = null;
		webClient = WebClient.create();
		webClient = WebClient.create("https://api.github.com");
		webClient = WebClient.builder()
				.baseUrl("https://api.github.com")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.github.v3+json")
				.defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
				.build();
		
		String username = "";
		String token = "";
		
		// https://github.com/grtlinux/spring-webclient-webtestclient-demo
		/*
		Flux<GithubRepo> result = webClient.get()
				.uri("/user/repos")
				.header("Authorization",  "Basic " + Base64Utils.encodeToString((username + ":" + token).getBytes(UTF_8))
				.retrieve()
				.bodyToFlux(GithubRepo.Class);
		*/
	}
}
