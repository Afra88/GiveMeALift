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
	private boolean childrensOnBoard;
	@Column(name="PETS_ON_BOARD")
	private boolean petsOnBoard;
	@Column(name="smocking")
	private boolean smoking;
	@Column(name="MUSIC")
	private boolean music;
	@Column(name="CHATNESS_LEVEL")
	private int chatnessLevel;
	
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
		// TODO Auto-generated method stub

	}

}
