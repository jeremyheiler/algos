package algos;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

    private class Node {
        private final int value;
        private Node left;
        private Node right;

        private Node(int value) {
            this(value, null, null);
        }

        private Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        private void add(int value) {
            if (value < this.value ) {
                if (left == null) {
                    left = new Node(value);
                } else {
                    left.add(value);
                }
            } else if (value > this.value) {
                if (right == null) {
                    right = new Node(value);
                } else {
                    right.add(value);
                }
            }
        }

        private void depthPreOrder(Visitor visitor) {
            visitor.visit(this);
            if (left != null) {
                left.depthPreOrder(visitor);
            }
            if (right != null) {
                right.depthPreOrder(visitor);
            }
        }

        private void depthInOrder(Visitor visitor) {
            if (left != null) {
                left.depthInOrder(visitor);
            }
            visitor.visit(this);
            if (right != null) {
                right.depthInOrder(visitor);
            }
        }

        private void depthPostOrder(Visitor visitor) {
            if (left != null) {
                left.depthPostOrder(visitor);
            }
            if (right != null) {
                right.depthPostOrder(visitor);
            }
            visitor.visit(this);
        }
    }

    private Node root;

    private void add(int value) {
        if (root == null) {
            root = new Node(value);
        } else {
            root.add(value);
        }
    }

    private void depthPreOrder(Visitor visitor) {
        if (root != null) {
            root.depthPreOrder(visitor);
        }
    }

    private void depthInOrder(Visitor visitor) {
        if (root != null) {
            root.depthInOrder(visitor);
        }
    }

    private void depthPostOrder(Visitor visitor) {
        if (root != null) {
            root.depthPostOrder(visitor);
        }
    }

    public void breadth(Visitor visitor) {
        if (root == null) {
            return;
        }
        List<Node> queue = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove(0);
            visitor.visit(node);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    private interface Visitor {

        void visit(Node node);
    }

    private static class PrintVisitor implements Visitor {

        @Override
        public void visit(Node node) {
            System.out.println(node.value);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.add(5);
        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.add(7);
        System.out.println("Pre-order: ");
        tree.depthPreOrder(new PrintVisitor());
        System.out.println("In-order: ");
        tree.depthInOrder(new PrintVisitor());
        System.out.println("Post-order: ");
        tree.depthPostOrder(new PrintVisitor());
        System.out.println("Breadth: ");
        tree.breadth(new PrintVisitor());
    }
}
