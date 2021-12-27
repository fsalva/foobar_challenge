package foobar.prepare_the_bunnies_escape;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;



public class Solution {

    public static class Node {
        public int val;
        public int x;
        public int y;
        public int dist;

        public Node(int x, int y, int val, int dist){
            this.x = x;
            this.y = y;
            this.val = val;
            this.dist = dist;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "["+ val+"] - x: " + x + " y: " + y;
        }

        public void moveRight(){
            this.x++;
        }
        public void moveLeft(){
            this.x--;
        }
        public void moveUp(){
            this.y++;
        }
        public void moveDown(){
            this.y--;
        }

    }

    public static int solution(int[][] map) {
        // Your code here
        boolean brokeWall = true;
        
        Node startingPoint = new Node(0, 0, 2, 1);
        map[0][0] = 2;

        Queue<Node> visited_queue = new ArrayDeque<>();
        visited_queue.add(startingPoint);
        
        Node node;
        boolean found = false;

        while(!found && visited_queue.size() > 0) {

            printBoard(map);

            node = visited_queue.poll();
            node.val = 3;
            
            Queue<Node> node_queue = discoverNeighbours(map, node, brokeWall);
            for (Node neighbor : node_queue) {
            
                if(neighbor.x == map[0].length -1 && neighbor.y == map.length -1){
                    found = true;
                    System.out.println("Arrivato: Distanza --> " + neighbor.dist + "m.");
                    break;
                }

                System.out.println(neighbor.toString());

                if(neighbor.val != 3)
                    visited_queue.add(neighbor);

            }
        }

        printBoard(map);


        return 0;
    }


    private static boolean checkValueOrVisited(int[][] map, int x, int y, boolean flag){
        
        //2 = già visitato
        //1 = mattone
        //flag = ho già rotto il muro
        
        if(map[x][y] == 2) return false;
        else if(map[x][y] == 1 && flag) return false;
        else return true;

    } 

    private static Queue<Node> discoverNeighbours(int[][] map, Node point, boolean brokeWall) {
        
        Queue<Node> res = new ArrayDeque<Node>();

        int height_limit = map.length;
        int width_limit = map[0].length;

        if(point.x < width_limit - 1)
            if(checkValueOrVisited(map, point.x + 1, point.y, brokeWall))
                //inserisco vicino dx
                {
                    res.add(new Node(point.x + 1, point.y, 2, point.dist+1));
                    map[point.x+1][point.y] = 2;
                }
            
        if(point.y < height_limit - 1)
            if(checkValueOrVisited(map, point.x, point.y + 1, brokeWall))
                //inserisco vicino sotto
                {
                    res.add(new Node(point.x, point.y +1, 2, point.dist+1));
                    map[point.x][point.y+1] = 2;
                }

        if(point.x > 0)
            if(checkValueOrVisited(map, point.x -1, point.y, brokeWall))
                //inserisco vicino a sx
                {
                    res.add(new Node(point.x - 1, point.y, 2, point.dist+1));
                    
                    // e aggiorno il valore, per specificare che l'ho già visitato.
                
                    map[point.x-1][point.y] = 2;
                }

        
        if(point.y > 0)
            if(checkValueOrVisited(map, point.x, point.y -1, brokeWall))
                //inserisco vicino sopra
                {
                    res.add(new Node(point.x, point.y -1, 2, point.dist+1));
                    map[point.x][point.y-1] = 2;
                }



        return res;

    }


    public static int bfs(int[][] map){

        

        return 0;
    }

    public static void printBoard(int[][] array){
        
        System.out.print("+");
        for (int i = 0; i < 2 * array.length - 1; i++) {
            System.out.print("-");
        }
        System.out.print("+");

        for (int i = 0; i < array.length; i++) {
            System.out.print("\n|");
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + "|");
            }
            if(i != array.length - 1){
             
                System.out.print("\n+");
                    for (int k = 0; k < 2 * array.length - 1; k++) {
                        if(k%2 == 0)
                            System.out.print("-");
                        else
                            System.out.print("+");
                    }
                System.out.print("+");   
            }
        }
        
        System.out.print("\n+");
        for (int i = 0; i < 2 * array.length - 1; i++) {
            System.out.print("-");
        }
        System.out.print("+");
    
        System.out.println();
    }

    

    public static void main(String[] args) {
        int[][] arr = { 
                        {0, 0, 0 , 0, 0, 0},
                        {1, 1, 1 , 1, 1, 0},
                        {0, 0, 0 , 0, 0, 0},
                        {0, 1, 1 , 1, 1, 1},
                        {0, 1, 1 , 1, 1, 1},
                        {0, 0, 0 , 0, 0, 0}, 
                        
                    };

        solution(arr);

    }
}
