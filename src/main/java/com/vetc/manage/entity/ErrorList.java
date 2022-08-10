package com.vetc.manage.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "error_list")
public class ErrorList {
	@Id
	private Long autoid;
	private String errorcode;
	private String description;
	private String viMessage;
	private String enMessage;
}
