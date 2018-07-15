package com.flipkart.machinecoding.entities;

import java.util.ArrayList;
import java.util.List;

public class Process {

    private String name;

    private List<Variable> variables;

    public Process(String name) {

        this.name = name;

        variables = new ArrayList<>();


    }

    public void addVariable(Variable var) {
        variables.add(var);
    }

    public void removeVariable(Variable var) {
        variables.remove(var);
    }

    public boolean isAlive() {
        return !variables.isEmpty();
    }

    public String getName() {
        return name;
    }

    public List<Variable> getVariables() {
        return variables;
    }



}
