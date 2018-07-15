package com.flipkart.machinecoding.entities;

import com.flipkart.machinecoding.utils.ProcessRetriever;
import com.flipkart.machinecoding.utils.VariableRetriever;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryManager {

    private List<Process> processes;
    private Memory memory;
    private List<MemoryChunk> allocatedMemoryChunks;

    public MemoryManager(Memory memory) {

        this.memory = memory;

        processes = new ArrayList<>();

        allocatedMemoryChunks = new ArrayList<>();


    }

    public void allocate(String processName, String variableName, int blocksRequested, boolean requireContinuousFlag) {


        // Create a list of memorychunks based on continuous flag, create a variable and add the variable to the process

        if(blocksRequested > memory.getSize()) {
            System.out.println("error " + 0 + " / " + memory.getFreeBlocks());
            return;
        }

        List<MemoryChunk> newChunks = null;

        if(requireContinuousFlag) {

            MemoryChunk continuousChunk = allocateChunksWhenContinuousFlagTrue(blocksRequested);
            if(continuousChunk != null) {
                newChunks = new ArrayList<>();
                newChunks.add(continuousChunk);
            }

        } else {

            newChunks = allocateChunksWhenContinuousFlagFalse(blocksRequested);
        }

        if(newChunks == null) {
            System.out.println("error " + memory.getAllocatedBlocks() + " / " +  + memory.getFreeBlocks());
            return;
        }

        // add newChunks to memory
        allocatedMemoryChunks.addAll(newChunks);


        // Update allocated block count in memory
        memory.updateAllocatedBlocks(newChunks, true);

        // Check if process already exists
        Process process = ProcessRetriever.retrievebyProcessName(processName, processes);

        if(process == null) {
            process = new Process(processName);
            processes.add(process);
        }

        process.addVariable(new Variable(variableName, newChunks));



        System.out.println("SUCCESS " + memory.getAllocatedBlocks() + " / " + memory.getFreeBlocks());

    }

    private MemoryChunk allocateChunksWhenContinuousFlagTrue(int blocksRequested) {

        MemoryChunk continuous_chunk = null;

        if(allocatedMemoryChunks.isEmpty()) {
            continuous_chunk = allocateWhenEmptyMemory(blocksRequested);
            if(continuous_chunk != null) return continuous_chunk;
        }

        int previous_alloc_chunk_end = -1;

        Collections.sort(allocatedMemoryChunks);

        for(MemoryChunk allocatedChunk : allocatedMemoryChunks) {

            int start_index = allocatedChunk.getStartIndex();

            if(blocksRequested > start_index - previous_alloc_chunk_end - 1) {
                previous_alloc_chunk_end = allocatedChunk.getEndIndex();
                continue;
            }

            continuous_chunk = new MemoryChunk(previous_alloc_chunk_end + 1, previous_alloc_chunk_end + blocksRequested);
            return continuous_chunk;

        }

        // if continuous chunk couldn't be found till now, then check if there is free space after last allocated slot

        int last_alloc_block_end = allocatedMemoryChunks.get(allocatedMemoryChunks.size() - 1).getEndIndex();

        if(blocksRequested < memory.getSize() - last_alloc_block_end - 1) {
            continuous_chunk = new MemoryChunk(last_alloc_block_end + 1, last_alloc_block_end + blocksRequested);
            return continuous_chunk;
        }

        return continuous_chunk;
    }

    private List<MemoryChunk> allocateChunksWhenContinuousFlagFalse(int blocksRequested) {

        List<MemoryChunk> newChunks = new ArrayList<>();

        // if memory is empty
        if(allocatedMemoryChunks.isEmpty()) {
            MemoryChunk chunk = allocateWhenEmptyMemory(blocksRequested);
            if(chunk != null) {
                newChunks.add(chunk);
                return newChunks;
            }
        }

        int allocationTobeDone = blocksRequested;
        int end_index_of_previoud_chunk = -1;
        Collections.sort(allocatedMemoryChunks);
        for(MemoryChunk allocatedChunk : allocatedMemoryChunks) {

            if(allocatedChunk.getStartIndex() - Math.abs(end_index_of_previoud_chunk) > allocationTobeDone && allocationTobeDone > 0) {

                MemoryChunk chunk = new MemoryChunk(end_index_of_previoud_chunk + 1, end_index_of_previoud_chunk + allocationTobeDone);
                newChunks.add(chunk);
                allocationTobeDone = 0;

                return newChunks;

            } else if(allocationTobeDone > 0 && allocatedChunk.getStartIndex() - Math.abs(end_index_of_previoud_chunk) > 0){

                MemoryChunk chunk = new MemoryChunk(end_index_of_previoud_chunk + 1, allocatedChunk.startIndex - 1);
                newChunks.add(chunk);

                allocationTobeDone -= chunk.getSize();

            } else {
                end_index_of_previoud_chunk = allocatedChunk.getEndIndex();
            }
        }

        // if allocation is still > 0

        // Get the size of free space after last occupied memory chunk
        int end_index_of_last_alloc_chunk = allocatedMemoryChunks.get(allocatedMemoryChunks.size() - 1).getEndIndex();

        if((memory.getSize() - end_index_of_last_alloc_chunk - 1 < allocationTobeDone)) {
            return null;
        }

        if(allocationTobeDone > 0) {
            MemoryChunk chunk = new MemoryChunk(end_index_of_last_alloc_chunk + 1, end_index_of_last_alloc_chunk + allocationTobeDone);
            newChunks.add(chunk);
            allocationTobeDone = 0;
        }

        return newChunks;
    }

    private MemoryChunk allocateWhenEmptyMemory(int blocksRequested) {

        if (blocksRequested < memory.getSize()) {
            return new MemoryChunk(0, blocksRequested - 1);
        } else
            return null;
        }

    public void free(String processName, String variableName) {

        Process process = ProcessRetriever.retrievebyProcessName(processName, processes);

        Variable var = VariableRetriever.retrievebyVariableName(variableName, process.getVariables());

        memory.updateAllocatedBlocks(var.getOccupiedChunks(), false);

        allocatedMemoryChunks.removeAll(var.getOccupiedChunks());

        process.removeVariable(var);

        var = null;

        System.out.println("SUCCESS " + memory.getAllocatedBlocks() + " / " + memory.getFreeBlocks());
    }

    public void kill(String processName) {

        Process process = ProcessRetriever.retrievebyProcessName(processName, processes);

        List<Variable> processVars = process.getVariables();

        for(Variable var : processVars) {
            allocatedMemoryChunks.removeAll(var.getOccupiedChunks());
            memory.updateAllocatedBlocks(var.getOccupiedChunks(),false);
        }

        processes.remove(process);

        process = null;

        System.out.println("SUCCESS " + memory.getAllocatedBlocks() + " / " + memory.getFreeBlocks());
    }

    public void inspect(String processName) {

        Process process = ProcessRetriever.retrievebyProcessName(processName, processes);

        if(process == null) return;

        for(Variable var : process.getVariables()) {

            System.out.print(var.getName());

            for(MemoryChunk chunk : var.getOccupiedChunks()) {

                System.out.print(" " + chunk.getStartIndex() + "-" + chunk.getEndIndex());
            }

            System.out.print("\n");


        }
    }
}