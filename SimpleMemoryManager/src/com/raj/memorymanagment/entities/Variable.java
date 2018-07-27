package com.raj.memorymanagment.entities;

import java.util.List;

public class Variable {

    private String name;

    List<MemoryChunk> occupiedChunks;

    public Variable(String name, List<MemoryChunk> occupiedChunks) {

        this.name = name;

        this.occupiedChunks = occupiedChunks;

    }

    public String getName() {
        return name;
    }

    public List<MemoryChunk> getOccupiedChunks() {
        return occupiedChunks;
    }

}
