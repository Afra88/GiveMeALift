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

	@Column(name="CHILDRENS_ON_BOARD")
	private Boolean childrensOnBoard;
	@Column(name="PETS_ON_BOARD")
	private Boolean petsOnBoard;
	@Column(name="smocking")
	private Boolean smoking;
	@Column(name="MUSIC")
	private Boolean music;
	@Column(name="CHATNESS_LEVEL")
	private Integer chatnessLevel;
	
	public PersonalPreference(){
		childrensOnBoard=false;
		petsOnBoard=false;
		smoking=false;
		music=false;
		chatnessLevel=1;
	}
	
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PERSONAL_PREFERENCE_ID")
	public long getId() {
		return super.getId();
	}
	
	public boolean isChildrensOnBoard() {
		return childrensOnBoard;
	}





	public void setChildrensOnBoard(boolean childrensOnBoard) {
		this.childrensOnBoard = childrensOnBoard;
	}





	public boolean isPetsOnBoard() {
		return petsOnBoard;
	}





	public void setPetsOnBoard(boolean petsOnBoard) {
		this.petsOnBoard = petsOnBoard;
	}





	public boolean isSmoking() {
		return smoking;
	}





	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}





	public boolean isMusic() {
		return music;
	}





	public void setMusic(boolean music) {
		this.music = music;
	}





	public int getChatnessLevel() {
		return chatnessLevel;
	}





	public void setChatnessLevel(int chatnessLevel) {
		this.chatnessLevel = chatnessLevel;
	}





	@Override
	public void copy(DomainObject object2) {
		PersonalPreference pp=(PersonalPreference)object2;
		if(pp.childrensOnBoard!=null)
			this.childrensOnBoard=pp.childrensOnBoard;
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

}
