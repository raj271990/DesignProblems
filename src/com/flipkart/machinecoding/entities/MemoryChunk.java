package com.flipkart.machinecoding.entities;

public class MemoryChunk implements Comparable<MemoryChunk> {

    int startIndex;
    int endIndex;

    public MemoryChunk(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public int getSize() {
        return endIndex - startIndex + 1;
    }

    @Override
    public int compareTo(MemoryChunk that) {
        if (this.startIndex < that.startIndex) {
            return -1;
        } else if (this.startIndex > that.startIndex) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "MemoryChunk{" +
                "startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                '}';
    }
}
