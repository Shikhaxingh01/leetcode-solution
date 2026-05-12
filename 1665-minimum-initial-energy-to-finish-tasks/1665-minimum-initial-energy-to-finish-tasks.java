class Solution {
    public int minimumEffort(int[][] tasks) {
        int energy = 0;
        int extraEnergyAdded = 0;
        int[][] taskNRestEnergy = new int[tasks.length][3];
        for (int i = 0; i < tasks.length; i++) {
            taskNRestEnergy[i][0] = tasks[i][0];
            taskNRestEnergy[i][1] = tasks[i][1];
            taskNRestEnergy[i][2] = tasks[i][1] - tasks[i][0];
        }
        // Sort tasks by the rest energy (required energy - actual energy) in descending order, and if the rest energy is the same, sort by required energy in ascending order
        Collections.sort(java.util.Arrays.asList(taskNRestEnergy), (a, b) -> a[2] == b[2] ? a[1] - b[1] : b[2] - a[2]);
        for (int i = 0; i < taskNRestEnergy.length; i++) {
            if (energy < taskNRestEnergy[i][1]) {
                extraEnergyAdded += taskNRestEnergy[i][1] - energy;
                energy = taskNRestEnergy[i][1];
            }
            energy -= taskNRestEnergy[i][0];
        }
        return extraEnergyAdded;
    }
}