package com.betbull.playerdata.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class TeamPlayerId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Team team;
	private Player player;
	
	@ManyToOne
	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	@ManyToOne
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamPlayerId that = (TeamPlayerId) o;

        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        if (team != null ? !team.equals(that.team) : that.team != null) return false;
        
        return true;
    }

    public int hashCode() {
        int result;
        result = (player != null ? player.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        return result;
    }
}
