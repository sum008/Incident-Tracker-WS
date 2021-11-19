package com.incident.tool.database.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "incident_db")
public class IncidentEntity {

	@Column(name = "empid", nullable = false)
	private String empId;
	@Column(name = "empname", nullable = false)
	private String empName;
	@Column(name = "title", nullable = false)
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "assignedTo")
	private String assignedTo;
	@Column(name = "severity", nullable = false)
	private String severity;

	@Id
	@Column(name = "incidentNumber", nullable = false)
	private String incidentNumber;
	@Column(name = "dateCreated", nullable = false)
	private String dateCreated;
	@Column(name = "dateClosed")
	private String dateClosed;
	@Column(name = "closingNotes")
	private String closingNotes;
}