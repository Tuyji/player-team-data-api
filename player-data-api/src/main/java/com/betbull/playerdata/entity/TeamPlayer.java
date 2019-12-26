package com.betbull.playerdata.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity(name = "TeamPlayer")
@Table(name = "team_player")
@AllArgsConstructor
@RequiredArgsConstructor
@AssociationOverrides({
	@AssociationOverride(name = "pk.player", 
		joinColumns = @JoinColumn(name = "player_id")),
	@AssociationOverride(name = "pk.team", 
		joinColumns = @JoinColumn(name = "team_id")) })
public class TeamPlayer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private TeamPlayerId pk = new TeamPlayerId();
	private int yearJoin;
	private int yearLeft;
	
	
	@EmbeddedId
	public TeamPlayerId getPk() {
		return pk;
	}

	public void setPk(TeamPlayerId pk) {
		this.pk = pk;
	}
	
	@Transient
    public Team getTeam() {
    	return getPk().getTeam();
    }
    
    public void setTeam(Team team) {
    	getPk().setTeam(team);
    }

    @Transient
    public Player getPlayer() {
    	return getPk().getPlayer();
    }
    
    public void setPlayer(Player player) {
    	getPk().setPlayer(player);
    }
    
    public int getYearJoin() {
    	return yearJoin;
    }
    
    public void setYearJoin(int yearJoin) {
    	this.yearJoin = yearJoin;
    }
    
    public int getYearLeft() {
    	return yearLeft;
    }
    
    public void setYearLeft(int yearLeft) {
    	this.yearLeft = yearLeft;
    }
    
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		TeamPlayer that = (TeamPlayer) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}

}

