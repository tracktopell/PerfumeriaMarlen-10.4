/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmarlen.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alfredo
 */
public class JarpeReportsInfoDTO {
	HashMap<String, Object> parameters;
	//List<HashMap<String, String>> records;
	Collection<Map<String,?>> records = new ArrayList<Map<String,?>>();

	public JarpeReportsInfoDTO(HashMap<String, Object> parameters, Collection<Map<String,?>> records) {
		this.parameters = parameters;
		this.records = records;
	}

	public HashMap<String, Object> getParameters() {
		return parameters;
	}

	public Collection<Map<String,?>> getRecords() {
		return records;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("JarpeReportsInfoDTO {");
		
		sb.append("parameters=").
				append(parameters.toString().replace(",", "\n")).
				append(", records=").
				append(records.toString().replace(",", "\n")).append("}");		
		return sb.toString();
	}
	
}
