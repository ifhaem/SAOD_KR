package com.company;

public class AVLTree {

    public static class Node {
        Node left, right;
        Node parent;
        Driver value;
        int height = 0;

        public Node(Driver data, Node parent) {
            this.value = data;
            this.parent = parent;
        }

        @Override
        public String toString() { // todo
            return value.getInfo();
        }

        void setLeftChild(Node child) {
            if (child != null)
                child.parent = this;

            this.left = child;
        }

        void setRightChild(Node child) {
            if (child != null)
                child.parent = this;

            this.right = child;
        }

        Driver getDriver() { return value; }
    }


    private Node root = null;

    public void insert(Driver data) {
        if (data.checkNumber() && search(data.getNumberValue()) == null)
            insert(root, data);
        else
            System.out.println("Не удаётся добавить водителя в базу (возможно, он уже зарегестрирован).");
    }

    private int height(Node node) { // Возвращает вес
        return node == null ? -1 : node.height;
    }

    private void insert(Node node, Driver value) {
        if (root == null) {
            root = new Node(value, null);
            return;
        }

        if (value.getNumberValue() < node.value.getNumberValue()) {
            if (node.left != null)
                insert(node.left, value);
            else
                node.left = new Node(value, node);

            if (height(node.left) - height(node.right) == 2) { //left heavier
                if (value.getNumberValue() < node.left.value.getNumberValue())
                    rotateRight(node);
                else
                    rotateLeftThenRight(node);
            }
        } else if (value.getNumberValue() > node.value.getNumberValue()) {
            if (node.right != null)
                insert(node.right, value);
            else
                node.right = new Node(value, node);

            if (height(node.right) - height(node.left) == 2) { //right heavier
                if (value.getNumberValue() > node.right.value.getNumberValue())
                    rotateLeft(node);
                else
                    rotateRightThenLeft(node);
            }
        }

        reHeight(node);
    }

    private void rotateRight(Node pivot) {
        Node parent = pivot.parent;
        Node leftChild = pivot.left;
        Node rightChildOfLeftChild = leftChild.right;
        pivot.setLeftChild(rightChildOfLeftChild);
        leftChild.setRightChild(pivot);
        if (parent == null) {
            this.root = leftChild;
            leftChild.parent = null;
            return;
        }

        if (parent.left == pivot)
            parent.setLeftChild(leftChild);
        else
            parent.setRightChild(leftChild);

        reHeight(pivot);
        reHeight(leftChild);
    }

    private void rotateLeft(Node pivot) {
        Node parent = pivot.parent;
        Node rightChild = pivot.right;
        Node leftChildOfRightChild = rightChild.left;
        pivot.setRightChild(leftChildOfRightChild);
        rightChild.setLeftChild(pivot);
        if (parent == null) {
            this.root = rightChild;
            rightChild.parent = null;
            return;
        }

        if (parent.left == pivot)
            parent.setLeftChild(rightChild);
        else
            parent.setRightChild(rightChild);

        reHeight(pivot);
        reHeight(rightChild);
    }

    private void reHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private void rotateLeftThenRight(Node node) {
        rotateLeft(node.left);
        rotateRight(node);
    }

    private void rotateRightThenLeft(Node node) {
        rotateRight(node.right);
        rotateLeft(node);
    }

    public boolean delete(int key) {
        Node target = search(key);

        if (target == root) {
            if (root.left == null && root.right == null) {
                root = null;
                return true;
            } else {
                if (root.left != null) {
                    Node cur = getRightLeafForRoot(root.left);
                    if (isLeftChild(cur))
                        cur.parent.left = null;
                    else
                        cur.parent.right = null;
                    cur.left = root.left;
                    cur.right = root.right;
                    root = cur;
                    balanceTree(root);
                    return true;
                } else {
                    Node cur = getLeftLeafForRoot(root.right);
                    if (isLeftChild(cur))
                        cur.parent.left = null;
                    else
                        cur.parent.right = null;
                    cur.left = root.left;
                    cur.right = root.right;
                    root = cur;
                    balanceTree(root);
                    return true;
                }
            }
        }
        if (target == null) return false;
        target = deleteNode(target);
        balanceTree(target.parent);
        return true;
    }

    private Node getRightLeafForRoot(Node cur) {
        if (cur.left == null && cur.right == null)
            return cur;
        if (cur.right != null)
            return getRightLeafForRoot(cur.right);
        else
            return getRightLeafForRoot(cur.left);
    }

    private Node getLeftLeafForRoot(Node cur) {
        if (isLeaf(cur))
            return cur;
        if (cur.left != null)
            return getLeftLeafForRoot(cur.left);
        else
            return getLeftLeafForRoot(cur.right);
    }

    private Node deleteNode(Node target) {
        if (isLeaf(target)) { // leaf
            if (isLeftChild(target))
                target.parent.left = null;
            else
                target.parent.right = null;
        } else if (target.left == null ^ target.right == null) { // exact 1 child
            Node nonNullChild = target.left == null ? target.right : target.left;
            if (isLeftChild(target))
                target.parent.setLeftChild(nonNullChild);
            else
                target.parent.setRightChild(nonNullChild);
        } else { // 2 children
            Node immediatePredInOrder = immediatePredInOrder(target);
            target.value = immediatePredInOrder.value;
            target = deleteNode(immediatePredInOrder);
        }

        reHeight(target.parent);
        return target;
    }

    private Node immediatePredInOrder(Node node) {
        Node current = node.left;
        while (current.right != null)
            current = current.right;

        return current;
    }

    private boolean isLeftChild(Node child) {
        return (child.parent.left == child);
    }

    private boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    private int calDifference(Node node) {
        int rightHeight = height(node.right);
        int leftHeight = height(node.left);
        return rightHeight - leftHeight;
    }

    private void balanceTree(Node node) {
        int difference = calDifference(node);
        Node parent = node.parent;
        if (difference == -2) {
            if (height(node.left.left) >= height(node.left.right))
                rotateRight(node);
            else
                rotateLeftThenRight(node);
        } else if (difference == 2) {
            if (height(node.right.right) >= height(node.right.left))
                rotateLeft(node);
            else
                rotateRightThenLeft(node);
        }

        if (parent != null)
            balanceTree(parent);

        reHeight(node);
    }

    public void searchByFragment(String fragment) {
        binarySearchByFragment(root, fragment);
    }

    private void binarySearchByFragment(Node node, String fragment) {
        if (node != null) {
            char[] arr = fragment.toCharArray();
            char[] FIO = node.getDriver().getFIO().toCharArray();
            int k = 0;
            for (char c : FIO) {
                if (c == arr[k]) {
                    k++;
                    if (k == arr.length) {
                        System.out.println(node.toString());
                        break;
                    }
                    continue;
                }
                k = 0;
            }
            binarySearchByFragment(node.left, fragment);
            binarySearchByFragment(node.right, fragment);
        }
    }

    public void searchByFragmentAdres(String fragment) {
        binarySearchByFragmentAdres(root, fragment);
    }
    private void binarySearchByFragmentAdres(Node node, String fragment) {
        if (node != null) {
            char[] arr = fragment.toCharArray();
            char[] arr1 = node.getDriver().getAdress().toCharArray();
            int k = 0;
            for (char c : arr1) {
                if (c == arr[k]) {
                    k++;
                    if (k == arr.length) {
                        System.out.println(node.toString());
                        break;
                    }
                    continue;
                }
                k = 0;
            }
            binarySearchByFragmentAdres(node.left, fragment);
            binarySearchByFragmentAdres(node.right, fragment);
        }
    }

    public Node search(int key) {
        return binarySearch(root, key);
    }

    private Node binarySearch(Node node, int key) {
        if (node == null) return null;
        if (key == node.value.getNumberValue()) {
            System.out.println(node.toString());
            return node;
        }
        if (key < node.value.getNumberValue() && node.left != null) return binarySearch(node.left, key);
        if (key > node.value.getNumberValue() && node.right != null) return binarySearch(node.right, key);
        return null;
    }

    public void clear() {
        root = null;
        System.gc();
    }

    public void traverseInOrder() {
        inorder(root);
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            inorder(node.right);
            System.out.println(node.toString());
        }
    }

}