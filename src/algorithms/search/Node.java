package algorithms.search;

import java.util.*;

public class Node {
    private String[][] grid;
    private int iEmpty, jEmpty;
    private String roadToParent;
    private Node parent;
    static String[][] goal = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", " "}};
    static Node GOAL = new Node(goal);
    public static Stack<Node> AnswerNodes = new Stack<>();
    public static Stack<String> AnswerDirection = new Stack<>();
    public static boolean solved = false;

    public Node(String[][] grid) {
        this.grid = grid;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.grid[i][j].compareToIgnoreCase(" ") == 0) {
                    iEmpty = i;
                    jEmpty = j;
                }
            }
        }
    }

    public String[][] getGrid() {
        return grid;
    }

    public int getiEmpty() {
        return iEmpty;
    }

    public int getjEmpty() {
        return jEmpty;
    }

    public String getRoadToParent() {
        return roadToParent;
    }

    public void setRoadToParent(String roadToParent) {
        this.roadToParent = roadToParent;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object object){
        if (object instanceof Node) {
            String[][] otherGrid = ((Node) object).getGrid();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (this.grid[i][j].compareToIgnoreCase(otherGrid[i][j]) != 0) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    @Override
    public int hashCode(){
        return Arrays.deepHashCode(grid);
    }

    private String[][] makeNewGrid(String[][] oldGrid) {
        String[][] newGrid = new String[oldGrid.length][];
        for (int i = 0; i < oldGrid.length; i++) {
            newGrid[i] = oldGrid[i].clone();
        }
        return newGrid;
    }

    Node buildRightNode() {
        String[][] newGrid = makeNewGrid(this.grid);
        newGrid[this.iEmpty][this.jEmpty] = newGrid[this.iEmpty][this.jEmpty + 1];
        newGrid[this.iEmpty][this.jEmpty + 1] = " ";
        Node rightNode = new Node(newGrid);
//        node.setRight(rightNode);
        rightNode.setParent(this);
        rightNode.setRoadToParent("Right");
//        rightNode.setLeft(node);
        return rightNode;
    }

    Node buildLeftNode() {
        String[][] newGrid = makeNewGrid(this.grid);
        newGrid[this.iEmpty][this.jEmpty] = newGrid[this.iEmpty][this.jEmpty - 1];
        newGrid[this.iEmpty][this.jEmpty - 1] = " ";
        Node leftNode = new Node(newGrid);
//        node.setLeft(leftNode);
        leftNode.setParent(this);
        leftNode.setRoadToParent("Left");
//        leftNode.setRight(node);
        return leftNode;
    }

    Node buildUpNode() {
        String[][] newGrid = makeNewGrid(this.grid);
        newGrid[this.iEmpty][this.jEmpty] = newGrid[this.iEmpty - 1][this.jEmpty];
        newGrid[this.iEmpty - 1][this.jEmpty] = " ";
        Node upNode = new Node(newGrid);
//        node.setUp(upNode);
        upNode.setParent(this);
        upNode.setRoadToParent("Up");
//        upNode.setDown(node);
        return upNode;
    }

    Node buildDownNode() {
        String[][] newGrid = makeNewGrid(this.grid);
        newGrid[this.iEmpty][this.jEmpty] = newGrid[this.iEmpty + 1][this.jEmpty];
        newGrid[this.iEmpty + 1][this.jEmpty] = " ";
        Node downNode = new Node(newGrid);
//        node.setDown(downNode);
        downNode.setParent(this);
        downNode.setRoadToParent("Down");
//        downNode.setUp(node);
        return downNode;
    }

    void printGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(this.grid[i][j] + "\t");
            }
            System.out.println();
        }
    }

    static boolean isGoal(Node newNode) {
        if (newNode.equals(GOAL)) {
            while (newNode.getRoadToParent() != null) {
                AnswerNodes.push(newNode);
                AnswerDirection.push(newNode.roadToParent);
                newNode = newNode.getParent();
            }
            solved = true;
            return true;
        }
        return false;
    }


}