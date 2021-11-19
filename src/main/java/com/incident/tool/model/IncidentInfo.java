package com.incident.tool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncidentInfo {

	private String exceptionMessage;
	private int responseCode;
	private int httpStatusCode;
	private String statusMessage;
}
