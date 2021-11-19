package com.incident.tool.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidentModel extends IncidentInfo {
	@NotEmpty
	private String empId;

	@NotEmpty
	@Size(min = 2, max = 30)
	private String empName;

	@NotEmpty
	private String title;
	private String description;
	private String assignedTo;
	private String severity;
	private String incidentNumber;
	private String dateCreated;
	private String dateClosed;
	private String closingNotes;
}
