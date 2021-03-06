package org.tain;

import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;
import org.tain.domain.Stmt;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SpringWebClient {

	private static boolean flag = true;
	
	public static void main(String[] args) throws Exception {
		if (!flag) test01();
		if (flag) test02();
	}
	
	//////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////
	private static void test01() throws Exception {
		/*
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
		*/
		
		// https://github.com/grtlinux/spring-webclient-webtestclient-demo
		/*
		Flux<GithubRepo> result = webClient.get()
				.uri("/user/repos")
				.header("Authorization",  "Basic " + Base64Utils.encodeToString((username + ":" + token).getBytes(UTF_8))
				.retrieve()
				.bodyToFlux(GithubRepo.Class);
		*/
	}
	
	//////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////
	private WebClient webClient;
	
	private SpringWebClient() {
		//this.webClient = WebClient.create();
		this.webClient = WebClient.create("http://localhost:8080/api");
		//this.webClient = WebClient.builder()
		//		.baseUrl("http://localhost:8080/api")
		//		.defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.github.v3+json")
		//		.defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
		//		.build();
	}
	
	private static void test02() {
		SpringWebClient springWebClient = new SpringWebClient();
		
		if (flag) springWebClient.findAll();
		if (flag) springWebClient.findById(4);
		if (!flag) springWebClient.create(Stmt.join(1, 1101, "", ""));
		if (!flag) springWebClient.update(Stmt.join(1, 1101, "", ""));
		if (!flag) springWebClient.delete(4);
	}

	private void findAll() {
		Flux<Stmt> fluxStmt = this.webClient.get()
				.uri("/stmts")
				.retrieve()
				.bodyToFlux(Stmt.class);
		List<Stmt> lstStmt = fluxStmt.collectList().block();
		//lstStmt.forEach(System.out::println);
		for (Stmt stmt : lstStmt) {
			System.out.println(">>>>> " + stmt);
		}
	}

	private void findById(Integer id) {
		Mono<Stmt> monoStmt = this.webClient.get()
				.uri("/stmts/" + id)
				.retrieve()
				.bodyToMono(Stmt.class);
		System.out.println(">>>>> " + monoStmt);
	}

	private void create(Stmt stmt) {
		Mono<Stmt> monoStmt = this.webClient.post()
				.uri("/stmts")
				.body(Mono.just(stmt), Stmt.class)
				.retrieve()
				.bodyToMono(Stmt.class);
		System.out.println(">>>>> " + monoStmt);
	}

	private void update(Stmt stmt) {
		Mono<Stmt> monoStmt = this.webClient.put()
				.uri("/stmts/" + stmt.getId())
				.body(Mono.just(stmt), Stmt.class)
				.retrieve()
				.bodyToMono(Stmt.class);
		System.out.println(">>>>> " + monoStmt);
	}

	private void delete(Integer id) {
		Mono<Void> monoStmt = this.webClient.delete()
				.uri("/stmts/" + id)
				.retrieve()
				.bodyToMono(Void.class);
		System.out.println(">>>>> " + monoStmt);
	}
}
