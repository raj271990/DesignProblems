package com.flipkart.machinecoding.utils;

import com.flipkart.machinecoding.entities.Process;

import java.util.List;

public class ProcessRetriever {

    public static Process retrievebyProcessName(String processName, List<Process> processes) {

        for(Process process : processes) {
            if(processName.equals(process.getName())) {
                return process;
            }
        }

        return null;
    }
}
