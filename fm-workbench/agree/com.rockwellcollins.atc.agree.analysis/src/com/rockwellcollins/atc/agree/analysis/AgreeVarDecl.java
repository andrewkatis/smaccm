package com.rockwellcollins.atc.agree.analysis;

import jkind.lustre.IdExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Type;
import jkind.lustre.VarDecl;

public class AgreeVarDecl extends VarDecl implements Comparable<AgreeVarDecl>{
    
    public AgreeVarDecl(String jKindStr, String type){
    	super(jKindStr, new NamedType(type));
    }
    
    public AgreeVarDecl(String id, Type type) {
		super(id, type);
	}

	@Override
    public String toString(){
    	return id;
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        
        if(o instanceof IdExpr){
            return ((IdExpr)o).id.equals(id);
        }
        
        if (o instanceof AgreeVarDecl) {
            return ((AgreeVarDecl) o).id.equals(id);
        }
        return false;
        
    }

    @Override
    public int compareTo(AgreeVarDecl o) {
        return id.compareTo(o.id);
    }

}