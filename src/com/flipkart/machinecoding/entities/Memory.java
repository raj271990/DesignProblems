package com.flipkart.machinecoding.entities;

import java.util.List;

public class Memory {

    private static Memory instance = null;

    private int allocatedBlocks;


    private int totalBlocks;

    private Memory(int totalBlocks) {

        this.totalBlocks = totalBlocks;

        this.allocatedBlocks = 0;

    }

    public static Memory getInstance(int totalBlocks) {

        if(instance == null) {
            instance = new Memory(totalBlocks);
        }

        return instance;

    }

    public int getAllocatedBlocks() {
        return allocatedBlocks;
    }


    public int getFreeBlocks() {

        return totalBlocks - allocatedBlocks;
    }

    public int getSize() {
        return this.totalBlocks;
    }

    public void updateAllocatedBlocks(List<MemoryChunk> allocatedChunks, boolean allocation) {

        for(MemoryChunk chunk : allocatedChunks) {
            if(allocation) allocatedBlocks += chunk.getSize();
            else allocatedBlocks -= chunk.getSize();
        }
    }

}
