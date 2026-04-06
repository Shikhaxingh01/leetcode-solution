import java.util.*;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        
        Set<String> set = new HashSet<>();
        for (int[] o : obstacles) {
            set.add(o[0] + "," + o[1]);
        }

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        int dir = 0; // North
        
        int x = 0, y = 0;
        int maxDist = 0;

        for (int cmd : commands) {
            
            if (cmd == -1) { // right
                dir = (dir + 1) % 4;
            } 
            else if (cmd == -2) { // left
                dir = (dir + 3) % 4;
            } 
            else {
                for (int i = 0; i < cmd; i++) {
                    
                    int nx = x + dirs[dir][0];
                    int ny = y + dirs[dir][1];
                    
                    if (set.contains(nx + "," + ny)) {
                        break; // obstacle
                    }
                    
                    x = nx;
                    y = ny;
                    
                    maxDist = Math.max(maxDist, x*x + y*y);
                }
            }
        }
        
        return maxDist;
    }
}