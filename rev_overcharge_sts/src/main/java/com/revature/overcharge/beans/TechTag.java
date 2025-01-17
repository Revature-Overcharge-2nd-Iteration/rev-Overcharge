package com.revature.overcharge.beans;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tags")
@JsonIgnoreProperties(value = { "decks" })
public class TechTag {
	
	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private int id;
	
	@Column (name = "tag")
	private String tag;
	
	@ManyToMany(mappedBy = "tags")
    private Set<Deck> decks = new HashSet<>();
	

	public TechTag() {
		super();
	}
	
	
	public TechTag(String tag) {
		this.tag = tag;
	}
	

	public TechTag(int id, String tag) {
		this.id = id;
		this.tag = tag;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Set<Deck> getDecks() {
		return decks;
	}

	public void setDecks(Set<Deck> decks) {
		this.decks = decks;
	}
	
	public void addDeck(Deck deck) {
		this.decks.add(deck);
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id, tag);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TechTag other = (TechTag) obj;
		return id == other.id && Objects.equals(tag, other.tag);
	}


	@Override
	public String toString() {
		return "TechTag [id=" + id + ", tag=" + tag + "]";
	}	

}
