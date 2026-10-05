package com.bugtrack.repository;

import com.bugtrack.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<Bug, Long> {
}
