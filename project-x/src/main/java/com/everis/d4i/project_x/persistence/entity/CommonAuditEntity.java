package com.everis.d4i.project_x.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Getter
@Setter
public class CommonAuditEntity implements Serializable {

	private static final long serialVersionUID = 8766059991048007097L;

	@CreationTimestamp
	@Column(name = "create_date", nullable = false, length = 9, updatable = false)
	private Timestamp createDate;

	@UpdateTimestamp
	@Column(name = "update_date", length = 9)
	private Timestamp updateDate;

}
