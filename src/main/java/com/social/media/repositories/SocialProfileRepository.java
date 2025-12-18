package com.social.media.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.media.models.SocialProfile;

public interface SocialProfileRepository extends JpaRepository<SocialProfile, Long> {

}
