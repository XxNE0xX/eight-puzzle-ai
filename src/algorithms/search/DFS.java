package algorithms.search;

import algorithms.Algorithm;
import algorithms.AlgorithmClass;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFS extends AlgorithmClass {
    static Stack<Node> openedStates = new Stack<>();
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
        if (initialNode.equals(Node.GOAL)) {
            return;
        }
        while (!Node.solved){
            expandNode(openedStates.pop());
        }
    }

    void expandNode(Node node){
        if (node.getiEmpty() < 2){
            Node newNode = node.buildDownNode();
            openingManagement(newNode);
        }
        if (node.getiEmpty() > 0){
            Node newNode = node.buildUpNode();
            openingManagement(newNode);
        }
        if (node.getjEmpty() < 2){
            Node newNode = node.buildRightNode();
            openingManagement(newNode);
        }
        if (node.getjEmpty() > 0){
            Node newNode = node.buildLeftNode();
            openingManagement(newNode);
        }

    }



    void openingManagement(Node node) {
        if (visited.contains(node)){
            return;
        }
        openedStates.add(node);
        visited.add(node);
//        System.out.println("Opens" + openedStates.size());
    }
}
