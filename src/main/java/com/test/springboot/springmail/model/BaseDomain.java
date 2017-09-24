package com.test.springboot.springmail.model;

import java.io.Serializable;
import java.util.Date;

public class BaseDomain implements Serializable {

	private static final long serialVersionUID = -8166920467266837496L;
	
	protected Long id;
	
	protected Date createdDate;
	
	protected String createdBy;
	
	protected Date updateDate;
	
	protected String updatedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public void setBaseDomain(String loggedInUser, String registeredUser) {
		
		if(loggedInUser != null) {
			if(this.getId() == null)
				this.setCreatedBy(loggedInUser);
			else
				this.setUpdatedBy(loggedInUser);
		}
		else {
			if(this.getId() == null)
				this.setCreatedBy(registeredUser);
			else
				this.setUpdatedBy(registeredUser);
		}
		
		if(this.getId() == null)
			this.setCreatedDate(new Date());
		else
			this.setUpdateDate(new Date());
	}
}
