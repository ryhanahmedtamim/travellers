package com.ryhanahmedtamim.travellersservice.repository;

import com.ryhanahmedtamim.travellersservice.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {
    List<StatusEntity> findAllByPrivacy(Integer privacy);
    List<StatusEntity> findAllByCreatedUserIdOrderByUpdatedDate(Integer id);
}
