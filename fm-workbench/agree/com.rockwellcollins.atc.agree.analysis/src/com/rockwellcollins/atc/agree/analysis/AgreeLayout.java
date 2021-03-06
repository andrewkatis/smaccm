package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jkind.results.layout.Layout;

public class AgreeLayout implements Layout {
	private List<String> categories = new ArrayList<>();
	private Map<String, String> signalCategories = new HashMap<>();
	private Map<String, List<String>> compInputSignals = new HashMap<>();
	private Map<String, List<String>> compOutputSignals = new HashMap<>();
	public enum SigType{INPUT, OUTPUT}
	
	public void addCategory(String category) {
		categories.add(category);
	}

	public void addElement(String category, String signal, SigType type) {
		signalCategories.put(signal, category);
		Map<String, List<String>> compSignals;
		if(type == SigType.INPUT){
		    compSignals = compInputSignals;
		}else{
		    assert(type == SigType.OUTPUT);
		   compSignals = compOutputSignals;
		}
		
		List<String> sigList = compSignals.get(category);
		if(sigList == null){
		    sigList = new ArrayList<>();
		    compSignals.put(category, sigList);
		}
		
		sigList.add(signal);
	}
	
	@Override
	public List<String> getCategories() {
		return categories;
	}

	@Override
	public String getCategory(String signal) {
		String retCat = signalCategories.get(signal);
		if(retCat == null){
			while(retCat == null && signal.contains(".")){
				String[] tokens = signal.split("\\.");
				String delim = "";
				signal = "";
				for(int i = 0; i < tokens.length-1; i++){
					signal += delim + tokens[i];
					delim = ".";
				}
				retCat = signalCategories.get(signal);
			}
		}
		return retCat;
	}
	
	public List<String> getAllInputsFromCategory(String category){
	    return compInputSignals.get(category);
	}
	
	public List<String> getAllOutputsFromCategory(String category){
        return compOutputSignals.get(category);
    }
	
}
