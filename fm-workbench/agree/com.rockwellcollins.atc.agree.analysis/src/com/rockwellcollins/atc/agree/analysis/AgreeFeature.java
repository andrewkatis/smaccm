package com.rockwellcollins.atc.agree.analysis;

import org.osate.aadl2.Feature;

public class AgreeFeature {
	
	public enum ConnType {QUEUE,IMMEDIATE,DELAYED}
	public enum Direction {IN, OUT};
	
	public Feature feature;
	public String lustreString;
	public String varType;
	public ConnType connType;
	public Direction direction;
	public long queueSize;
	
	@Override
	public String toString(){
		return lustreString;
	}

}
