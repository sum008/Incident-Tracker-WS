package com.incident.tool.incident.controller;

import java.util.List;

import javax.validation.Valid;

import com.incident.tool.exception.DataValidationFailedException;
import com.incident.tool.exception.InvalidJsonDataException;
import com.incident.tool.exception.JSONParsingException;
import com.incident.tool.factory.IncidentFactoryMethods;
import com.incident.tool.model.IncidentModel;
import com.incident.tool.model.IncidentQueryModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Lazy
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/incident")
public class IncidentController {
	@Autowired
	IncidentFactoryMethods incidentFactory;

	@PostMapping(value = "/create", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IncidentModel> createNewIncident(@RequestBody String encryptedJson)
			throws InvalidJsonDataException, DataValidationFailedException, JSONParsingException {
		IncidentModel incidentModel = incidentFactory.createIncident(encryptedJson);
		return new ResponseEntity<>(incidentModel, HttpStatus.OK);
	}

	@GetMapping(value = "/viewSingle", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IncidentModel> viewSingleIncident(
			@Valid @ModelAttribute("incidentNumber") IncidentQueryModel model) {
		IncidentModel incidentModel = incidentFactory.viewSingleIncident(model.getIncidentNumber());
		return new ResponseEntity<>(incidentModel, HttpStatus.OK);
	}

	@GetMapping(value = "/viewList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<IncidentModel>> viewIncidentList(@ModelAttribute("empId") IncidentQueryModel model) {
		List<IncidentModel> incidentList = null;
		incidentList = incidentFactory.viewIncidentList(model.getEmpId());
		return new ResponseEntity<>(incidentList, HttpStatus.OK);
	}
}