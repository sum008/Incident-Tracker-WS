package com.incident.tool.database.repository;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.incident.tool.constants.IncidentConstants;
import com.incident.tool.model.IncidentModel;
import com.incident.tool.utiliy.DateUtility;
import com.incident.tool.utiliy.IncidentNumberGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidentRepositoryServiceImpl implements IncidentRepositoryService {

	@Autowired
	IncidentNumberGenerator incidentGenerator;

	@Autowired
	public DateUtility date;

	@Autowired
	IncidentRepository repository;

	public void saveIncident(IncidentModel incidentModel) throws NoSuchAlgorithmException {
		IncidentEntity incidentEntity = this.buildRequest(incidentModel);
		repository.save(incidentEntity);
	}

	public IncidentEntity getIncident(String incidentNumber) {
		return repository.findById(incidentNumber).orElseThrow();
	}

	public List<IncidentEntity> getIncidentList(String empId) {
		return repository.getAllIncidents(empId);
	}

	private IncidentEntity buildRequest(IncidentModel incidentModel) throws NoSuchAlgorithmException {
		return IncidentEntity.builder().incidentNumber(incidentGenerator.generateTicketNumber())
				.dateCreated(date.getCurrentDate(IncidentConstants.DATE_FORMAT)).empName(incidentModel.getEmpName())
				.empId(incidentModel.getEmpId()).title(incidentModel.getTitle())
				.description(incidentModel.getDescription()).severity(incidentModel.getSeverity()).build();
	}
}