package com.betbull.playerdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betbull.playerdata.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{

}
