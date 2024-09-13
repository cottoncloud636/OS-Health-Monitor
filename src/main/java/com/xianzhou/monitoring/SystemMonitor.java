package com.xianzhou.monitoring;

import java.util.List;

/* hardware */

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.PowerSource; 

public class SystemMonitor {
    // | System information. This is the main entry point to OSHI.
    // This obj provides getters which instantiate the platform-specific implementations of OperatingSystem (software) and HardwareAbstractionLayer (hardware).
    private final SystemInfo systemInfo; 

    // | constructor
    public SystemMonitor(){
        this.systemInfo = new SystemInfo();
    }

    /* -- getters */

    // | get CPU usage
    public double getCPULoad (){
        System.out.println("Fetching CPU processor...");
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        // | add an interval because CPU usage fluctuate constantly, I need to measure CPU load
        //   at a given time.
        System.out.println("Got processor, calculating load...");
        long interval = 5000L;  // 5000 nanosecond = 0.000005 second. The interval can't be too large, as it takes a long time to calculate

        double cpuLoad = processor.getSystemCpuLoad(interval) * 100;

        System.out.println("CPU load calculated.");

        if (cpuLoad == -1.0) {
            System.out.println("CPU load could not be determined.");
        }

        return cpuLoad;
    
    }

    // | get total memory 
    public long getTotalMemory(){
        GlobalMemory memory = systemInfo.getHardware().getMemory();//returns "bytes" by default
        long totalMemoryGB = memory.getTotal() / (1024 * 1024 * 1024);
        return totalMemoryGB; 
    }

    // | get used memory 
    public long getUsedMemory(){
        GlobalMemory memory = systemInfo.getHardware().getMemory();//returns "bytes" by default
        long totalMemoryGB = memory.getTotal() / (1024 * 1024 * 1024);
        long memoryAvailable = memory.getAvailable() / (1024 * 1024 * 1024);
        long memoryUsed = totalMemoryGB - memoryAvailable;
        return memoryUsed;
    }

    // | get battery usage report
    public String getBatteryUsage(){
        // | PowerSource[] is an array of objs, representing batteries
        List<PowerSource> powerSources = systemInfo.getHardware().getPowerSources();

        if (powerSources.isEmpty()){
            System.out.println("No battery information found.");
            return "Battery not available or no battery information found.";
        }
        StringBuilder batteryReport = new StringBuilder();
        for (PowerSource powerSource : powerSources){
           batteryReport.append("Battery Name: ").append(powerSource.getName()).append("\n")
                        .append("Remaining battery capaticy: ").append(powerSource.getRemainingCapacityPercent()*100).append("%\n");
                        

            double powerUsageRate = powerSource.getPowerUsageRate();
            if (powerUsageRate < 0) {
                batteryReport.append("Power Usage Rate: Discharging at: ").append(Math.abs(powerUsageRate)).append(" milliwatts\n");
            } else if (powerUsageRate > 0) {
                batteryReport.append("Power Usage Rate: Charging at ").append(powerUsageRate).append(" milliwatts\n");
            } else {
                batteryReport.append("Power Usage Rate: Battery fully charged or your battery is plugged in\n");
            }

        
            batteryReport.append("Time remaining: ").append(powerSource.getTimeRemainingInstant()/60).append(" minutes\n");//getTimeRemainingInstant(): based on the current instantaneous discharge rate
        }

        return batteryReport.toString();
    }
}


