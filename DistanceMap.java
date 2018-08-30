package Assignment;

import java.util.LinkedList;
import java.util.Queue;

public class DistanceMap {

    //Represent 1 10*10 block
    static class Location{
        int row;
        int col;
        int distance;

        public Location(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

    private static int minDistance(char grid[][],int n,int m){
        Location source=new Location(0,0,0);
        boolean visited[][]=new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++)
            {
                visited[i][j]= grid[i][j]=='0'? true: false;
                if(grid[i][j]=='s'){
                   source.row=i;
                   source.col=j;
                }
            }
        }

        //Doing a traversal
        Queue<Location> locationQueue=new LinkedList<>();
        locationQueue.add(source);
        visited[source.row][source.col]=true;
        while (!locationQueue.isEmpty()){
            Location next=locationQueue.peek();
            locationQueue.remove();
            if(grid[next.row][next.col]=='d'){
                return next.distance;
            }

            //up
            if(next.row-1>=0 && visited[next.row-1][next.col]==false){
                locationQueue.add(new Location(next.row-1,next.col,next.distance+1));
                visited[next.row-1][next.col]=true;
            }
            //down
            if(next.row+1<n && visited[next.row+1][next.col]==false){
                locationQueue.add(new Location(next.row+1,next.col,next.distance+1));
                visited[next.row+1][next.col]=true;
            }
            //left
            if(next.col-1>=0 && visited[next.row][next.col-1]==false){
                locationQueue.add(new Location(next.row,next.col-1,next.distance+1));
                visited[next.row][next.col-1]=true;
            }
            //right
            if(next.col+1<m && visited[next.row][next.col-1]==false){
                locationQueue.add(new Location(next.row,next.col+1,next.distance+1));
                visited[next.row][next.col+1]=true;
            }
        }
        return -1;
    }
    public static void main(String[] args) {

        //Assuming map is given like a grid
        //0 -> No path in 10*10 block
        //1-> path in 10*10 block
        //s-> source
        //d-> destination
        char grid[][]={{'0','1','1','s'},
                       {'1','0','1','1'},
                       {'0','1','1','1'},
                       {'d','1','1','1'}};

        System.out.println(minDistance(grid,grid.length,grid[0].length));
    }
}
