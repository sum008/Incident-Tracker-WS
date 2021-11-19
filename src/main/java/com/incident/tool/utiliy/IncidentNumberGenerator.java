package com.incident.tool.utiliy;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import com.incident.tool.constants.IncidentConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidentNumberGenerator {

	@Autowired
	DateUtility dateUtility;

	public String generateTicketNumber() throws NoSuchAlgorithmException {

		Random random = SecureRandom.getInstanceStrong();
		String baseDate = dateUtility.getCurrentDate(IncidentConstants.BASE_INCIDENT_NO_FORMAT);
		int seedMilliSeconds = Integer.parseInt(dateUtility.getCurrentDate(IncidentConstants.BASE_SEED_MILLISECONDS));
		int seedSeconds = Integer.parseInt(dateUtility.getCurrentDate(IncidentConstants.BASE_SEED_SECONDS));
		int randomSeedValue = random.nextInt(100);
		int newSeed = seedMilliSeconds + randomSeedValue + seedSeconds;
		return new StringBuilder().append("INC").append(baseDate).append(String.valueOf(newSeed)).toString();
	}
}
