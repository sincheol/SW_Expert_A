package capstone;

import java.util.PriorityQueue;
import java.util.Random;
import java.lang.Math;

class Node implements Comparable<Node> {
    long[] axes;
    Node left, right; // left, right, parent
    long d;
    int idx, state; // root-axes distance , 방문한 분기점 idx 0 = left, 1 = right, 2 = leaf 노드

    public Node(long[] arr) {
        this.axes = arr;
        this.left = this.right = null;
        this.d = 999999999;
        this.state = 0; // 2-> match / others wait
    }

    public long getDistance() {
        return this.d;
    }

    @Override
    public int compareTo(Node node) {
        if (this.d > node.getDistance()) {
            return 1;
        } else if (this.d < node.getDistance()) {
            return -1;
        }
        return 0;
    }
}

public class kd_tree {
    int k = 2; // 2 dimensional
    // depth = 0

    public Node newNode(long[] arr) {
        return new Node(arr);
    }

    public Node insert(Node root, long[] axes) {
        return insertTr(root, axes, 0);
    }

    public Node insertTr(Node root, long[] axes, int depth) {
        if (root == null) { // if tree is empty
            return newNode(axes);
        } else {
            int cd = depth % k; // 0 or 1 -> x or y

            if (axes[cd] < root.axes[cd]) { // insert node go right when larger than root node
                root.left = insertTr(root.left, axes, depth + 1);
            } else {
                root.right = insertTr(root.right, axes, depth + 1);
            }

            return root;
        }
    }

    public boolean areaxesSame(long[] axes1, long[] axes2) {

        for (int i = 0; i < k; i++) {
            if (axes1[i] != axes2[i]) {
                return false;
            }
        }
        return true;
    }

    public Node findMin(Node root, int d) {
        return findMinRec(root, d, 0);
        // d means dimension
    }

    public Node findMinRec(Node root, int d, int depth) {
        if (root == null) {
            return null;
        }

        int cd = depth % k;

        if (cd == d) { // then min rec may be in left sub tree
            if (root.left == null) {
                return root;
            }

            return findMinRec(root.left, d, depth + 1);
        }

        // else have to compare all
        return minNode(root, findMinRec(root.left, d, depth + 1), findMinRec(root.right, d, depth + 1), d);
    }

    public Node minNode(Node a, Node b, Node c, int d) {
        Node min = a;

        if (b != null && b.axes[d] < min.axes[d]) {
            min = b;
        }
        if (c != null && c.axes[d] < min.axes[d]) {
            min = c;
        }

        return min;
    }

    public void copyNode(long[] n1, long[] n2) {
        for (int i = 0; i <= k; i++) {
            n1[i] = n2[i];
        }
    }

    public Node deleteNode(Node root, long[] axes) {
        return deleteNodeRec(root, axes, 0);
    }

    public Node deleteNodeRec(Node root, long[] axes, int depth) {
        if (root == null) {
            return null;
        }

        int cd = depth % k;

        if (areaxesSame(root.axes, axes)) {
            if (root.right != null) { // find min in right sub tree
                Node min = findMin(root.right, cd);
                copyNode(root.axes, min.axes);
                root.right = deleteNodeRec(root.right, min.axes, depth + 1);
            } else if (root.left != null) { // find min in left sub tree
                Node min = findMin(root.left, cd);
                copyNode(root.axes, min.axes);
                root.right = deleteNodeRec(root.left, min.axes, depth + 1);
            } else { // just delete it
                root = null;
            }
            return root;
        }

        if (axes[cd] < root.axes[cd]) {
            root.left = deleteNodeRec(root.left, axes, depth + 1);
        } else {
            root.right = deleteNodeRec(root.right, axes, depth + 1);
        }

        return root;

    }

    public Node searchNode(Node root, Node best, long[] axes, int depth, PriorityQueue<Node> q) {
        root.idx = 2; // it means leaf
        root.d = (long) Math.pow((double) root.axes[0] - (double) axes[0], 2)
                + (long) Math.pow((double) root.axes[1] - (double) axes[1], 2);
        q.add(root);

        if (best.d > root.d) { // root -> best
            best = root;
        }

        if (root.d == 0) {
            return best;
        }

        int cd = depth % k;

        // move to the next node
        if (axes[cd] < root.axes[cd]) { // left
            if (root.left == null) { // current node != leaf
                return best;
            }
            root.idx = 0;
            return searchNode(root.left, best, axes, depth + 1, q);

        } else { // right
            if (root.right == null) {
                return best;
            }
            root.idx = 1;
            return searchNode(root.right, best, axes, depth + 1, q);
        }

    }

    public Node nearest(Node root, long[] axes) {
        Node best = newNode(new long[] { 0, 0 });
        PriorityQueue<Node> q = new PriorityQueue<>();

        // reset
        return nearestNeighbor(root, best, axes, 0, q);
    }

    public Node nearestNeighbor(Node root, Node best, long[] axes, int depth, PriorityQueue<Node> q) {
        int cd = depth % k;

        best = searchNode(root, best, axes, depth, q); // search로 들어가면서 axes와의 distance를 계산해 저장해 그것을 기반으로 queue에 저장

        while (!q.isEmpty()) { // 역으로 올라가기
            Node node = q.poll();

            if (node.d <= 2) { // stop
                best = node;
                break;
            }

            // non visited branch
            if (node.idx == 0) {
                if (Math.pow((double) axes[cd] - (double) node.axes[cd], 2) <= best.d) {
                    if (node.right == null) {
                        continue; // there's nothing to visit,, next poll
                    }
                    best = nearestNeighbor(node.right, best, axes, depth, q);
                }
            } else if (node.idx == 1) {
                if (Math.pow((double) axes[cd] - (double) node.axes[cd], 2) <= best.d) {
                    if (node.left == null) {
                        continue;
                    }
                    best = nearestNeighbor(node.left, best, axes, depth, q);
                }
            }
        }
        return best;
    }

    public boolean p(Node root, Node best) {
        if (best != null) {
            best.state++;
            if (best.state == 2) {
                deleteNode(root, best.axes);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        kd_tree kdTree = new kd_tree();

        Node root = null;
        Random random = new Random();
        long[][] axes = new long[600000][2];
        for (int i = 0; i < 600000; i++) {
            axes[i][0] = random.nextInt(600000); // x 좌표 범위 (0부터 99)
            axes[i][1] = random.nextInt(600000); // y 좌표 범위 (0부터 99)
        }

        int n = axes.length;

        for (int i = 0; i < n; i++) {
            root = kdTree.insert(root, axes[i]);
        }

        long[] axes1 = { 1200, 1500 };
        long beforeTime = System.currentTimeMillis();
        Node test = kdTree.nearest(root, axes1);
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime);
        System.out.println("nearest node : (" + test.axes[0] + "," + test.axes[1] + ")" + test.d + "state = " + test.state);
        System.out.println("time : " + secDiffTime);
    }
}