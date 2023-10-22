package capstone;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.lang.Math;

class Node implements Comparable<Node> {
    Long[] axes;
    Node left, right; // left, right, parent
    Long uId, d;
    int idx, state; // root-axes distance , 방문한 분기점 idx 0 = left, 1 = right, 2 = leaf 노드
    ArrayList<uNode> group = new ArrayList<uNode>();

    protected Node(Long[] arr) {
        this.axes = arr;
        this.left = this.right = null;
        this.d = 999999999999l;
        this.state = 0; // 2-> match / others wait
    }

    public Long getDistance() {
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

class uNode {
    Long[] axes;
    Long uId;

    protected uNode(Long uId, Long[] arr) {
        this.uId = uId;
        this.axes = arr;
    }
}

public final class kd_tree {
    static final int k = 2; // 2 dimensional
    // depth = 0

    private Node newNode(Long[] arr) {
        return new Node(arr);
    }

    private Node insert(Node root, Long[] axes) {
        return insertTr(root, axes, 0);
    }

    private Node insertTr(Node root, Long[] axes, int depth) {
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

    private boolean areaxesSame(Long[] axes1, Long[] axes2) {

        for (int i = 0; i < k; i++) {
            if (axes1[i] != axes2[i]) {
                return false;
            }
        }
        return true;
    }

    private Node findMin(Node root, int d) {
        return findMinRec(root, d, 0);
        // d means dimension
    }

    private Node findMinRec(Node root, int d, int depth) {
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

    private Node minNode(Node a, Node b, Node c, int d) {
        Node min = a;

        if (b != null && (b.axes[d] < min.axes[d])) {
            min = b;
        }
        if (c != null && (c.axes[d] < min.axes[d])) {
            min = c;
        }

        return min;
    }

    private Node deleteNode(Node root, Long[] axes) {
        return deleteNodeRec(root, axes, 0);
    }

    private Node deleteNodeRec(Node root, Long[] axes, int depth) {
        if (root == null) {
            return null;
        }

        int cd = depth % k;

        if (areaxesSame(root.axes, axes)) {
            if (root.right != null) { // find min in right sub tree
                Node min = findMin(root.right, cd);
                root = min;
                root.right = deleteNodeRec(root.right, min.axes, depth + 1);
            } else if (root.left != null) { // find min in left sub tree
                Node min = findMin(root.left, cd);
                root = min;
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

    private Node searchNode(Node root, Node best, Long[] axes, int depth, PriorityQueue<Node> q) {
        root.idx = 2; // it means leaf
        root.d = (long) Math.pow((double) root.axes[0] - (double) axes[0], 2)
                + (long) Math.pow((double) root.axes[1] - (double) axes[1], 2);
        q.add(root);

        System.out.println("x : " + root.axes[0] + " y : " + root.axes[1]);

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

    private Node nearest(Node root, Long[] axes) {
        Node best = newNode(new Long[] { 0l, 0l });
        PriorityQueue<Node> q = new PriorityQueue<>();

        // reset
        return nearestNeighbor(root, best, axes, 0, q);
    }

    private Node nearestNeighbor(Node root, Node best, Long[] axes, int depth, PriorityQueue<Node> q) {
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

    private ArrayList<uNode> findGroup(Node root, Node best, uNode s) {
        if ((best != null) && (best.state <= 2)) {
            best.state++;
            best.group.add(s);
            if (best.state == 2) {
                deleteNode(root, best.axes);
                return best.group;
            }

        }
        return null;
    }

    public static void main(String[] args) {
        kd_tree kdTree = new kd_tree();

        Node root = null;
        Long[][] axes = { { 3l, 6l }, { 17l, 15l }, { 13l, 15l }, { 6l, 12l },
                { 9l, 1l }, { 2l, 7l }, { 10l, 19l } };

        int n = axes.length;

        for (int i = 0; i < n; i++) {
            root = kdTree.insert(root, axes[i]);
        }

        Long a = 13l;
        Long[] axes1 = { 12l, 15l };
        Long b = 2l;
        Long[] axes2 = { 11l, 15l };
        uNode test = new uNode(a, axes1);
        uNode test1 = new uNode(b, axes2);

        Node best = kdTree.nearest(root, test.axes);
        Node best1 = kdTree.nearest(root, test1.axes);

        ArrayList<uNode> result = kdTree.findGroup(root, best, test);
        System.out.println(
                "nearest node : (" + best.axes[0] + "," + best.axes[1] + ")" + best.d + "state = " + best.state);

        ArrayList<uNode> result1 = kdTree.findGroup(root, best1, test1);
        System.out.println(
                "nearest node : (" + best1.axes[0] + "," + best1.axes[1] + ")" + best1.d + "state = " + best1.state);

        System.out.println(result1.get(0).axes[0]);
        System.out.println(result1.get(1).uId);

        kdTree.nearest(root, test.axes);
    }
}
