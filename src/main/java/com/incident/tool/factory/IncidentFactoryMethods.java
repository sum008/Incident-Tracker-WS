package com.incident.tool.factory;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.incident.tool.constants.IncidentConstants;
import com.incident.tool.database.repository.IncidentEntity;
import com.incident.tool.database.repository.IncidentRepositoryServiceImpl;
import com.incident.tool.exception.DataValidationFailedException;
import com.incident.tool.exception.InvalidJsonDataException;
import com.incident.tool.exception.JSONParsingException;
import com.incident.tool.model.IncidentModel;
import com.incident.tool.security.utility.EncDec;
import com.incident.tool.utiliy.JsonStringToObjectConverter;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Lazy
@Service
public class IncidentFactoryMethods implements IncidentFactory {

	private JsonStringToObjectConverter jsonStringToObjectConverter;
	private EncDec decrypt;
	private IncidentRepositoryServiceImpl incidentRepoService;

	// @Autowired(required=false)
	public IncidentFactoryMethods(JsonStringToObjectConverter jsonStringToObjectConverter, EncDec decrypt,
			IncidentRepositoryServiceImpl incidentRepoService) {
		super();
		this.jsonStringToObjectConverter = jsonStringToObjectConverter;
		this.decrypt = decrypt;
		this.incidentRepoService = incidentRepoService;
	}

	public IncidentModel createIncident(String encryptedJson)
			throws InvalidJsonDataException, DataValidationFailedException, JSONParsingException {
		IncidentModel incidentModel = null;
		try {
			incidentModel = jsonStringToObjectConverter.convertToObject(decrypt.decrypt(encryptedJson));
			incidentRepoService.saveIncident(incidentModel);
			this.buildResponse(incidentModel);
		} catch (NoSuchAlgorithmException e) {
			throw new JSONParsingException("Invalid encrypted json");
		}
		return incidentModel;
	}

	public IncidentModel viewSingleIncident(String incidentNumber) {
		IncidentModel incidentModel = null;
		try {
			IncidentEntity incidentEntity = incidentRepoService.getIncident(incidentNumber);
			incidentModel = this.buildResponse(incidentEntity);
		} catch (NoSuchElementException e) {
			incidentModel = this.buildNoDataFoundResponse(incidentNumber);
		}
		return incidentModel;
	}

	public List<IncidentModel> viewIncidentList(String empId) {
		List<IncidentEntity> incidentList = null;
		List<IncidentModel> incidentModelList = new ArrayList<>();
		try {
			incidentList = incidentRepoService.getIncidentList(empId);
			if (incidentList.size() == 0) {
				throw new IllegalAccessException();
			}
			incidentList.stream().forEach(entity -> incidentModelList.add(this.buildResponse(entity)));
		} catch (NoSuchElementException | IllegalAccessException e) {
			incidentModelList.add(this.buildNoDataFoundResponse(empId));
		}
		return incidentModelList;
	}

	private IncidentModel buildResponse(IncidentEntity incidentEntity) {
		IncidentModel incidentModel = IncidentModel.builder().incidentNumber(incidentEntity.getIncidentNumber())
				.dateCreated(incidentEntity.getDateCreated()).empName(incidentEntity.getEmpName())
				.empId(incidentEntity.getEmpId()).title(incidentEntity.getTitle())
				.description(incidentEntity.getDescription()).severity(incidentEntity.getSeverity()).build();
		incidentModel.setStatusMessage("Incident retrieved successfully");
		incidentModel.setResponseCode(IncidentConstants.SUCCESS);
		incidentModel.setHttpStatusCode(HttpStatus.ACCEPTED.value());

		return incidentModel;
	}

	private void buildResponse(IncidentModel incidentModel) {
		incidentModel.setStatusMessage("Incident created successfully");
		incidentModel.setResponseCode(IncidentConstants.SUCCESS);
		incidentModel.setHttpStatusCode(HttpStatus.ACCEPTED.value());
	}

	private IncidentModel buildNoDataFoundResponse(String queryKey) {
		IncidentModel model = new IncidentModel();
		model.setExceptionMessage("No Data Found");
		model.setStatusMessage(String.format("Invalid query key : %s", queryKey));
		model.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
		model.setResponseCode(IncidentConstants.FAILED);
		return model;
	}
}
