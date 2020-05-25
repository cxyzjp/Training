package com.ms.base.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerator.Feature;

public class WeJSONObjectMapper extends ObjectMapper{

	private static final long serialVersionUID = -4748908158149174034L;

	public WeJSONObjectMapper(){
		super();
		this.configure(Feature.WRITE_NUMBERS_AS_STRINGS, true);
		this.configure(Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
	}
}
