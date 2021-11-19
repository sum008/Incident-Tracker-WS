package com.incident.tool.utiliy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.incident.tool.exception.DataValidationFailedException;
import com.incident.tool.exception.InvalidJsonDataException;
import com.incident.tool.model.IncidentModel;

import org.springframework.stereotype.Service;

@Service
public class JsonStringToObjectConverter {

	public IncidentModel convertToObject(String json) throws InvalidJsonDataException, DataValidationFailedException {
		IncidentModel incidentModel;
		try {
			incidentModel = new ObjectMapper().readValue(json, IncidentModel.class);
		} catch (JsonProcessingException e) {
			throw new InvalidJsonDataException("Error occured while parsing json data");
		}
		this.checkJsonDataValidity(Validation.buildDefaultValidatorFactory().getValidator(), incidentModel);
		return incidentModel;
	}

	private void checkJsonDataValidity(Validator validator, IncidentModel incidentModel)
			throws DataValidationFailedException {
		Set<ConstraintViolation<IncidentModel>> errors = validator.validate(incidentModel);
		if (errors.size() > 0) {
			List<String> errorList = errors.stream().map(val -> new StringBuilder()
					.append(val.getPropertyPath().toString()).append(" : ").append(val.getMessage()).toString())
					.collect(Collectors.toList());
			throw new DataValidationFailedException("Data Validation Exception", errorList);
		}
	}

}
