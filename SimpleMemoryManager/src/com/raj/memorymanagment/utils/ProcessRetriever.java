package com.raj.memorymanagment.utils;

import com.raj.memorymanagment.entities.Process;

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
