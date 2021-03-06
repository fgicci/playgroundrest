package com.gicci.playground.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "community")
public class Community {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonIgnore
	@Column(name = "user_id")
	private String userId;
	
	@JsonIgnore
	@Column(name = "insert_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date insertDate;
	
	@JsonIgnore
	@Column(name = "update_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "sport")
	@Enumerated(EnumType.STRING)
	private Sport sport;
	
	@JsonIgnore
	@OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FellowCommunity> partners;
	
	@JsonIgnore
	@OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CommunityAddress> addresses;
	
	public Community() {}
	
	public Community(String name, Sport sport) {
		this.name = name;
		this.sport = sport;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public List<FellowCommunity> getPartners() {
		return partners;
	}

	public void setPartners(List<FellowCommunity> partners) {
		this.partners = partners;
	}

	public FellowCommunity addPartners(FellowCommunity partner) {
		getPartners().add(partner);
		partner.setCommunity(this);
		return partner;
	}
	
	public FellowCommunity removePartners(FellowCommunity partner) {
		getPartners().remove(partner);
		partner.setCommunity(null);
		return partner;
	}
	
	public List<CommunityAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<CommunityAddress> addresses) {
		this.addresses = addresses;
	}

	public CommunityAddress addAddress(CommunityAddress address) {
		getAddresses().add(address);
		address.setCommunity(this);
		return address;
	}
	
	public CommunityAddress removeAddress(CommunityAddress address) {
		getAddresses().remove(address);
		address.setCommunity(null);
		return address;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Community other = (Community) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Community [id=" + id + ", userId=" + userId + ", insertDate=" + insertDate + ", updateDate="
				+ updateDate + ", name=" + name + ", sport=" + sport + "]";
	}
}
