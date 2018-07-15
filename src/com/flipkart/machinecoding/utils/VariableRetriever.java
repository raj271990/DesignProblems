package com.flipkart.machinecoding.utils;

import com.flipkart.machinecoding.entities.Variable;

import java.util.List;

public class VariableRetriever {

    public static Variable retrievebyVariableName(String variableName, List<Variable> variables) {

        for(Variable variable : variables) {
            if(variableName.equals(variable.getName())) {
                return variable;
            }
        }

        return null;
    }
}
