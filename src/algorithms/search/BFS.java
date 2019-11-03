package algorithms.search;

import algorithms.Algorithm;
import algorithms.AlgorithmClass;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends AlgorithmClass {

    static Queue<Node> openedStates = new LinkedList<>();
    static HashSet<Node> visited = new HashSet<>();

    @Override
    public String makeMove(String[][] grid) {
        if (!Node.solved){
            long startTime = System.currentTimeMillis();
            solve(grid);
            System.out.println("Time Elapsed: " + (System.currentTimeMillis() - startTime) + " milliseconds");
        }
        return Node.AnswerDirection.pop();
    }

    @Override
    public void solve(String[][] initialGrid) {
        Node initialNode = new Node(initialGrid);
        openingManagement(initialNode);
        if (Node.isGoal(initialNode)) {
            return;
        }
        while (!Node.solved){
            expandNode(openedStates.poll());
        }
    }

    void expandNode(Node node){
        if (node.getiEmpty() < 2){
            Node newNode = node.buildDownNode();
            newNode.printGrid();
            if (Node.isGoal(newNode)){
                return;
            }
            openingManagement(newNode);
        }
        if (node.getiEmpty() > 0){
            Node newNode = node.buildUpNode();
            newNode.printGrid();
            if (Node.isGoal(newNode)){
                return;
            }
            openingManagement(newNode);
        }
        if (node.getjEmpty() < 2){
            Node newNode = node.buildRightNode();
            newNode.printGrid();
            if (Node.isGoal(newNode)){
                return;
            }
            openingManagement(newNode);
        }
        if (node.getjEmpty() > 0){
            Node newNode = node.buildLeftNode();
            newNode.printGrid();
            if (Node.isGoal(newNode)){
                return;
            }
            openingManagement(newNode);
        }

    }

    void openingManagement(Node node) {
        if (visited.contains(node)){
            return;
        }
        openedStates.add(node);
        visited.add(node);
        System.out.println("Opens" + openedStates.size());
    }

}
