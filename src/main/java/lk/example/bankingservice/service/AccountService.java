/*
 *     Copyright (c) 1995-2022,  The Data Management Group Ltd   All Rights Reserved.
 *     *  PROPRIETARY AND COPYRIGHT NOTICE.
 *
 *        This software product contains information which is proprietary to
 *        and considered a trade secret The Data management Group Ltd .
 *        It is expressly agreed that it shall not be reproduced in whole or part,
 *        disclosed, divulged or otherwise made available to any third party directly
 *        or indirectly.  Reproduction of this product for any purpose is prohibited
 *        without written authorisation from the The Data Management Group Ltd
 *        All Rights Reserved.
 *
 *        E-Mail andyj@datam.co.uk
 *        URL : www.datam.co.uk
 *        Created By :Jasintha Peiris
 */
package lk.example.bankingservice.service;

import lk.dmg.rmsstandard.apidoc.ComponentResponse;
import lk.dmg.rmsstandard.apidoc.ObjectiveResponse;
import lk.dmg.rmsstandard.apidoc.PrincipleResponse;
import lk.dmg.rmsstandard.apidoc.StandardListApiDoc;
import lk.dmg.rmsstandard.apidoc.EntityDataResponse;
import lk.dmg.rmsstandard.apidoc.StandardRequest;
import lk.dmg.rmsstandard.apidoc.StandardResponse;
import lk.dmg.rmsstandard.model.Objective;
import lk.dmg.rmsstandard.model.Principle;
import lk.dmg.rmsstandard.model.Account;
import lk.dmg.rmsstandard.model.Component;
import lk.dmg.rmsstandard.model.EntityData;
import lk.dmg.rmsstandard.repository.StandardRepository;
import lk.dmg.rmsstandard.util.Constant;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Date :2022-05-12. This class process the crud operation Service class
 *
 * @author Suresh Rnaweera
 */
@Slf4j
@org.springframework.stereotype.Component
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public class AccountService {
	private final AccountRepository standardRepository;
	
	/**
	 * findAllUnits method is get all units Data
	 * 
	 * @return allUnits
	 */
	public List<Unit> findAllUnits(Unit unit) {
		if (log.isDebugEnabled()) {
			log.debug("UnitService findAllUnits method calling.");
		}
		List<Unit> allUnits = new ArrayList<Unit>();
		if (unit.getCentreId() != null && unit.getCentreId() != "") {
			allUnits = (List<Unit>) unitRepository.findAllByCentreId(unit.getCentreId());
		} else {
			allUnits = (List<Unit>) unitRepository.findAll();
		}
		return allUnits;
	}

	/**
	 * findUnit method is get single unit data
	 * 
	 * @param id
	 * @return getUnit
	 */
	public Unit findUnit(int id) {
		if (log.isDebugEnabled()) {
			log.debug("UnitService findUnit method calling.");
		}
		Unit getUnit = unitRepository.findByUnitId(id);
		return getUnit;
	}

	/**
	 * saveUnit method is save unit data
	 * 
	 * @param unit
	 * @return savedUnit
	 */
	public Unit saveUnit(Unit unit) {
		if (log.isDebugEnabled()) {
			log.debug("UnitService saveUnit method calling.");
		}
		Unit savedUnit = new Unit();
		savedUnit = unitRepository.save(unit);
		return savedUnit;
	}

	/**
	 * updateUnit method is update unit data
	 * 
	 * @param unit
	 * @return updatedUnit
	 */
	public Unit updateUnit(Unit unit) {
		if (log.isDebugEnabled()) {
			log.debug("UnitService updateUnit method calling.");
		}
		Unit updatedUnit = new Unit();
		Unit existUnit = unitRepository.findByUnitId(unit.getUnitId());

		if (unit.getUnitName() == null || unit.getUnitName() == "") {
			unit.setUnitName(existUnit.getUnitName());
		}
		if (unit.getCentreId() == null || unit.getCentreId() == "") {
			unit.setCentreId(existUnit.getCentreId());
		}
		if (unit.getIsActive() == null) {
			unit.setIsActive(existUnit.getIsActive());
		}
		updatedUnit = unitRepository.save(unit);
		return updatedUnit;
	}

	/**
	 * findAllActiveUnits method is get all active Units
	 * 
	 * @param centreId
	 * @return allActiveUnits
	 */
	public List<Unit> findAllActiveUnits(Unit unit) {
		if (log.isDebugEnabled()) {
			log.debug("UnitService findAllActiveUnits method calling.");
		}
		boolean active = true;
		List<Unit> allActiveUnits = new ArrayList<Unit>();
		if (unit.getCentreId() != null && unit.getCentreId() != "") {
			allActiveUnits = (List<Unit>) unitRepository.findByIsActiveAndCentreId(active,unit.getCentreId());
		} else {
			allActiveUnits = (List<Unit>) unitRepository.findByIsActive(active);
		}
		return allActiveUnits;

	}

	/**
	 * deleteUnit method is delete single unit
	 * 
	 * @param id
	 * @return getUnit
	 */
	public Unit deleteUnit(int id) {
		if (log.isDebugEnabled()) {
			log.debug("UnitService deleteUnit method calling.");
		}
		Unit getUnit = unitRepository.findByUnitId(id);
		unitRepository.delete(getUnit);
		return getUnit;
	}
}