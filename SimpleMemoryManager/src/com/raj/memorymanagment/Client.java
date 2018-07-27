package com.raj.memorymanagment;

import com.raj.memorymanagment.entities.Memory;
import com.raj.memorymanagment.entities.MemoryManager;

public class Client {

    public static void main(String[] args) {

        Memory memory = Memory.getInstance(100);

        MemoryManager manager = new MemoryManager(memory);

        manager.allocate("p1", "var_x", 1000, false);
        manager.inspect("p1");

        manager.allocate("p1", "var_x", 10, false);
        manager.inspect("p1");

        manager.allocate("p2", "var_y", 25, true);
        manager.inspect("p2");

        manager.free("p1", "var_x");
        manager.inspect("p1");
        manager.inspect("p2");


        manager.kill("p2");
        manager.inspect("p2");

        manager.allocate("p1", "var_z", 10, true);
        manager.inspect("p1");

        manager.allocate("p4", "var_x", 5, true);
        manager.inspect("p4");

        manager.allocate("p1", "var_w", 5, true);
        manager.inspect("p1");

        manager.free("p4","var_x");
        manager.inspect("p4");

        manager.allocate("p1", "var_y", 6, false);
        manager.inspect("p1");

    }
}
