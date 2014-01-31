package it.unical.mat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PERSONAL_PREFERENCE")
public class PersonalPreference extends DomainObject {

//	@Column(name="CHILDRENS_ON_BOARD")
//	private Boolean childrensOnBoard;
	@Column(name="PETS_ON_BOARD")
	private Boolean petsOnBoard;
	@Column(name="SMOKING")
	private Boolean smoking;
	@Column(name="MUSIC")
	private Boolean music;
	@Column(name="CHATNESS_LEVEL")
	private Integer chatnessLevel;
	
	public PersonalPreference(){}
	
//	public PersonalPreference(){
//		this.petsOnBoard = false;
//		this.chatnessLevel = 1;
//		this.music = false;
//		this.smoking = false;
//	}
	
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PERSONAL_PREFERENCE_ID")
	public long getId() {
		return super.getId();
	}

	@Override
	public void copy(DomainObject object2) {
		PersonalPreference pp=(PersonalPreference)object2;
//		if(pp.childrensOnBoard!=null)
//			this.childrensOnBoard=pp.childrensOnBoard;
		if(pp.music!=null)
			this.music=pp.music;
		if(pp.music!=null)
			this.music=pp.music;
		if(pp.petsOnBoard!=null)
			this.petsOnBoard=pp.petsOnBoard;
		if(pp.smoking!=null)
			this.smoking=pp.smoking;
		if(pp.chatnessLevel!=null)
			this.chatnessLevel=pp.chatnessLevel;
		

	}


//	public Boolean getChildrensOnBoard() {
//		return childrensOnBoard;
//	}
//
//
//	public void setChildrensOnBoard(Boolean childrensOnBoard) {
//		this.childrensOnBoard = childrensOnBoard;
//	}


	public Boolean getPetsOnBoard() {
		return petsOnBoard;
	}


	public void setPetsOnBoard(Boolean petsOnBoard) {
		this.petsOnBoard = petsOnBoard;
	}


	public Boolean getSmoking() {
		return smoking;
	}


	public void setSmoking(Boolean smoking) {
		this.smoking = smoking;
	}


	public Boolean getMusic() {
		return music;
	}


	public Integer getChatnessLevel() {
		return chatnessLevel;
	}

	public void setMusic(Boolean music) {
		this.music = music;
	}


	public void setChatnessLevel(Integer chatnessLevel) {
		this.chatnessLevel = chatnessLevel;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chatnessLevel == null) ? 0 : chatnessLevel.hashCode());
//		result = prime
//				* result
//				+ ((childrensOnBoard == null) ? 0 : childrensOnBoard.hashCode());
		result = prime * result + ((music == null) ? 0 : music.hashCode());
		result = prime * result
				+ ((petsOnBoard == null) ? 0 : petsOnBoard.hashCode());
		result = prime * result + ((smoking == null) ? 0 : smoking.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonalPreference other = (PersonalPreference) obj;
		if (chatnessLevel == null) {
			if (other.chatnessLevel != null)
				return false;
		} else if (!chatnessLevel.equals(other.chatnessLevel))
			return false;
//		if (childrensOnBoard == null) {
//			if (other.childrensOnBoard != null)
//				return false;
//		} else if (!childrensOnBoard.equals(other.childrensOnBoard))
//			return false;
		if (music == null) {
			if (other.music != null)
				return false;
		} else if (!music.equals(other.music))
			return false;
		if (petsOnBoard == null) {
			if (other.petsOnBoard != null)
				return false;
		} else if (!petsOnBoard.equals(other.petsOnBoard))
			return false;
		if (smoking == null) {
			if (other.smoking != null)
				return false;
		} else if (!smoking.equals(other.smoking))
			return false;
		return true;
	}

	
	
}
