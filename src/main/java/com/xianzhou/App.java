package com.xianzhou;

import com.xianzhou.monitoring.SystemMonitor;

public class App 
{
    public static void main( String[] args )
    {
        SystemMonitor monitor = new SystemMonitor();
        // Run the CPU load calculation multiple times
        
        System.out.println("Calculating CPU load...");
    
        System.out.println("CPU Load: " + monitor.getCPULoad() + "%");
        // try {
        //     // Sleep for 1 second between each CPU load calculation
        //     Thread.sleep(1000);
        // } catch (InterruptedException e) {
        //     System.err.println("Thread was interrupted, failed to complete sleep.");
        //     e.printStackTrace();
        // }
        System.out.println("Total Memory: " + monitor.getTotalMemory() + "GB");
        System.out.println("*************************************************");
        System.out.println("Used Memory: " + monitor.getUsedMemory() + "GB");
        System.out.println("*************************************************");;
        System.out.println("Battery usage: ");
        System.out.println(monitor.getBatteryUsage());


    }
}