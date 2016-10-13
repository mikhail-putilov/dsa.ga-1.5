package ru.innopolis.mputilov;

import java.util.Objects;

/**
 * Created by mputilov on 13/10/16.
 */
public class BST<K extends Comparable<K>, V> {
    private Node<K, V> root;
    private int height = 0;

    public void put(K key, V value) {
        Objects.requireNonNull(key, "Key cannot be null");
        if (root == null) {
            root = new Node<>(key, value);
            height = 1;
            return;
        }
        Node<K, V> current = root;
        int depth = 1;
        while (true) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                if (current.left != null) {
                    current = current.left;
                    depth++;
                } else {
                    current.left = new Node<>(key, value);
                    height = Math.max(depth+1, height);
                    return;
                }
            } else if (cmp == 0) {
                current.value = value;
                return;
            } else {
                if (current.right != null) {
                    current = current.right;
                    depth++;
                } else {
                    current.right = new Node<>(key, value);
                    height = Math.max(depth+1, height);
                    return;
                }
            }
        }
    }

    public int getHeight() {
        return height;
    }

    private static class Node<K extends Comparable<K>, V> {
        K key;
        V value;
        Node<K, V> left = null;
        Node<K, V> right = null;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
