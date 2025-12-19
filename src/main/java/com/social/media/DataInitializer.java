package com.social.media;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.social.media.models.Post;
import com.social.media.models.SocialGroup;
import com.social.media.models.SocialProfile;
import com.social.media.models.SocialUser;
import com.social.media.repositories.PostRepository;
import com.social.media.repositories.SocialGroupRepository;
import com.social.media.repositories.SocialProfileRepository;
import com.social.media.repositories.SocialUserRepository;

@Configuration
public class DataInitializer {
    private final SocialUserRepository socialUserRepository;
    private final SocialGroupRepository socialGroupRepository;
    private final SocialProfileRepository socialProfileRepository;
    private final PostRepository postRepository;
    
    public DataInitializer(SocialUserRepository socialUserRepository, SocialGroupRepository socialGroupRepository,
            SocialProfileRepository socialProfileRepository, PostRepository postRepository) {
        this.socialUserRepository = socialUserRepository;
        this.socialGroupRepository = socialGroupRepository;
        this.socialProfileRepository = socialProfileRepository;
        this.postRepository = postRepository;
    }
    
    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            // Create some users
            SocialUser socialUser1 = new SocialUser();
            SocialUser socialUser2 = new SocialUser();
            SocialUser socialUser3 = new SocialUser();

            // Save users
            socialUserRepository.save(socialUser1);
            socialUserRepository.save(socialUser2);
            socialUserRepository.save(socialUser3);

            // Create some groups
            SocialGroup socialGroup1 = new SocialGroup();
            SocialGroup socialGroup2 = new SocialGroup();

            socialGroup1.getSocialUsers().add(socialUser1);
            socialGroup1.getSocialUsers().add(socialUser2);

            socialGroup2.getSocialUsers().add(socialUser2);
            socialGroup2.getSocialUsers().add(socialUser3);

            // Save groups to database
            socialGroupRepository.save(socialGroup1);
            socialGroupRepository.save(socialGroup2);

            // Associate users with groups
            socialUser1.getSocialGroups().add(socialGroup1);
            socialUser2.getSocialGroups().add(socialGroup1);
            socialUser2.getSocialGroups().add(socialGroup2);
            socialUser3.getSocialGroups().add(socialGroup2);

            // Save users back to database to update repository
            socialUserRepository.save(socialUser1);
            socialUserRepository.save(socialUser2);
            socialUserRepository.save(socialUser3);

            // Create some posts
            Post post1 = new Post();
            Post post2 = new Post();
            Post post3 = new Post();

            // Associate posts with users
            post1.setSocialUser(socialUser1);
            post2.setSocialUser(socialUser2);
            post3.setSocialUser(socialUser3);

            // Save post to database
            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);

            // Create some social profiles
            SocialProfile socialProfile1 = new SocialProfile();
            SocialProfile socialProfile2 = new SocialProfile();
            SocialProfile socialProfile3 = new SocialProfile();

            // Associate profiles with users
            socialProfile1.setSocialUser(socialUser1);
            socialProfile2.setSocialUser(socialUser2);
            socialProfile3.setSocialUser(socialUser3);

            socialProfileRepository.save(socialProfile1);
            socialProfileRepository.save(socialProfile2);
            socialProfileRepository.save(socialProfile3);

            // Fetch Type
            System.out.println("FETCH TYPE QUERY");
            socialUserRepository.findById(1L);
        };
    }
}
