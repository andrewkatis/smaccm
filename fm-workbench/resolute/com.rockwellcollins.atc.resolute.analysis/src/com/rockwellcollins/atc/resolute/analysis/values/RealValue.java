package com.rockwellcollins.atc.resolute.analysis.values;

import com.rockwellcollins.atc.resolute.validation.BaseType;
import com.rockwellcollins.atc.resolute.validation.ResoluteType;

public class RealValue extends ResoluteValue {
    final private double value;

    public RealValue(double value) {
        this.value = value;
    }

    @Override
    public boolean isReal() {
        return true;
    }

    @Override
    public double getReal() {
        return value;
    }

    @Override
    public ResoluteType getType() {
        return BaseType.REAL;
    }
    
    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public int hashCode() {
        return new Double(value).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RealValue) {
            RealValue dv = (RealValue) obj;
            return dv.value == value;
        }

        return false;
    }
}
