package org.tain;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.domain.Board;
import org.tain.domain.User;
import org.tain.domain.enums.BoardType;
import org.tain.domain.enums.SocialType;
import org.tain.repository.BoardRepository;
import org.tain.repository.UserRepository;
import org.tain.utils.CurrentInfo;

import lombok.extern.java.Log;

@SpringBootApplication
@Log
public class KieaSpringbootOAuth2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KieaSpringbootOAuth2Application.class, args);
	}

	private static boolean flag = true;
	
	@Override
	public void run(String... args) throws Exception {
		if (flag) test01(args);
	}

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	private void test01(String[] args) {
		log.info("KANG-20200531 >>>>> " + CurrentInfo.get());
		User user = this.userRepository.save(User.builder()
				.name("kiea")
				.password("kieapass")
				.email("kiea@gmail.com")
				.principal("ADMIN")
				.socialType(SocialType.GOOGLE)
				.createdDate(LocalDateTime.now())
				.build()
		);
		IntStream.rangeClosed(1, 500).forEach(index ->
				this.boardRepository.save(Board.builder()
						.title("제목-" + index)
						.subTitle("부제목-" + index)
						.content("콘텐츠입니다.\n" + UUID.randomUUID().toString())
						.boardType(BoardType.free)
						.createdDate(LocalDateTime.now())
						.updatedDate(LocalDateTime.now())
						.user(user)
						.build()
				)
		);
	}
}
