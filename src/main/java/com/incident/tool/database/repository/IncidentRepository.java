package com.incident.tool.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends CrudRepository<IncidentEntity, String> {

	@Query(value = "SELECT * FROM incident_db WHERE empid = :empId", nativeQuery = true)
	public List<IncidentEntity> getAllIncidents(@Param("empId") String empId);

}
