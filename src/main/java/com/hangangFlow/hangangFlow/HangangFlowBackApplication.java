package com.hangangFlow.hangangFlow;

import com.hangangFlow.hangangFlow.domain.bookmark.Bookmark;
import com.hangangFlow.hangangFlow.domain.bookmark.BookmarkRepository;
import com.hangangFlow.hangangFlow.domain.community.Community;
import com.hangangFlow.hangangFlow.domain.community.CommunityRepository;
import com.hangangFlow.hangangFlow.domain.park.ParkRepository;
import com.hangangFlow.hangangFlow.domain.user.UserRepository;
import com.hangangFlow.hangangFlow.domain.park.Parks;
import com.hangangFlow.hangangFlow.domain.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackageClasses = {Parks.class, User.class, Bookmark.class, Community.class})
@EnableJpaRepositories (basePackageClasses = {UserRepository.class, ParkRepository.class, BookmarkRepository.class, CommunityRepository.class})

public class HangangFlowBackApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(HangangFlowBackApplication.class, args);
		} catch (Exception e) {
			System.out.println("Error" + e);
		}
	}

//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}
