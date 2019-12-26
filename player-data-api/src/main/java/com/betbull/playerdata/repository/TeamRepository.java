package com.betbull.playerdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betbull.playerdata.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{

}
