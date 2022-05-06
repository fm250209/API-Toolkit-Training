package com.ncr.tx.apitoolkit.training;
import java.util.SortedMap;

public class MyCustomAction {

	public static final String PARAMETER_INPUT_STRING = "inputString";
	public static final String[] INVOKE_REQUIRED_PARAMETERS = {PARAMETER_INPUT_STRING};

	public Integer invoke(SortedMap<String, Object> fields, SortedMap<String, Object> parameters) {
		String inputString = parameters.get(PARAMETER_INPUT_STRING).toString();
		fields.put("myCustomResult", inputString.toUpperCase());
		return 0;
	}
}
