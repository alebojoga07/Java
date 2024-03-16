package proiect;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Clasa RedBlackTree implementeaza o structura de date arbore rosu-negru, care este un arbore binar de cautare.
 * Sunt implementate metode pentru inserare, stergere, parcurgere in ordine, preordine si postordine, precum si
 * parcurgerea pe niveluri a arborelui.
 *
 * @param <E> Tipul de elemente stocate în arbore
 */

public class RedBlackTree<E extends Comparable<E>> {
    private TreeNode<E> root;

    /**
     * Constructorul clasei RedBlackTree. Initializeaza radacina arborelui cu nil (un nod fictiv negru).
     */

  
    public TreeNode<E> getRoot() {
        return root;
    }

    /**
     * Metoda insert inserează un nou element in arbore.
     *
     * @param element Elementul de inserat în arbore.
     */
    public void insert(E data) {
        TreeNode<E> newNode = new TreeNode<>(data);
        insertNode(newNode);
    }

    private void insertNode(TreeNode<E> newNode) {
        if (root == null) {
            root = newNode;
            root.color = 0;
        } else {
            insertNodeRecursive(root, newNode);
            insertFixup(newNode);
        }
    }

    private void insertNodeRecursive(TreeNode<E> currentNode, TreeNode<E> newNode) {
        int compareResult = newNode.data.compareTo(currentNode.data);

        if (compareResult < 0) {
            if (currentNode.left == null) {
                currentNode.left = newNode;
                newNode.parent = currentNode;
            } else {
                insertNodeRecursive(currentNode.left, newNode);
            }
        } else if (compareResult > 0) {
            if (currentNode.right == null) {
                currentNode.right = newNode;
                newNode.parent = currentNode;
            } else {
                insertNodeRecursive(currentNode.right, newNode);
            }
        } else {
            System.out.println("Nodul exista deja in arbore");
        }
    }

    private void insertFixup(TreeNode<E> node) {
        while (node.parent != null && node.parent.color == 1) {
            if (node.parent == node.parent.parent.left) {
                TreeNode<E> y = node.parent.parent.right;
                if (y != null && y.color == 1) {
                    node.parent.color = 0;
                    y.color = 0;
                    node.parent.parent.color = 1;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.color = 0;
                    node.parent.parent.color = 1;
                    rightRotate(node.parent.parent);
                }
            } else {
                TreeNode<E> y = node.parent.parent.left;
                if (y != null && y.color == 1) {
                    node.parent.color = 0;
                    y.color = 0;
                    node.parent.parent.color = 1;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.color = 0;
                    node.parent.parent.color = 1;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.color = 0;
    }


    /**
     * Metoda delete sterge un element din arbore.
     *
     * @param element Elementul de sters din arbore.
     */
    public void delete(E data) {
        TreeNode<E> nodeToDelete = searchNode(root, data);
        if (nodeToDelete != null) {
            deleteNode(nodeToDelete);
        }
    }

    private TreeNode<E> searchNode(TreeNode<E> node, E data) {
        if (node == null || data.equals(node.data)) {
            return node;
        }

        int compareResult = data.compareTo(node.data);
        if (compareResult < 0) {
            return searchNode(node.left, data);
        } else {
            return searchNode(node.right, data);
        }
    }

    private void deleteNode(TreeNode<E> nodeToDelete) {
        TreeNode<E> x, y;
        if (nodeToDelete.left == null || nodeToDelete.right == null) {
            y = nodeToDelete;
        } else {
            y = findSuccessor(nodeToDelete.right);
        }

        if (y.left != null) {
            x = y.left;
        } else {
            x = y.right;
        }

        if (x != null) {
            x.parent = y.parent;
        }

        if (y.parent == null) {
            root = x;
        } else {
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }

        if (y != nodeToDelete) {
            nodeToDelete.data = y.data;
        }

        if (y.color == 0) {
            deleteFixup(x);
        }
    }

    private void deleteFixup(TreeNode<E> x) {
        while (x != null && x != root && x.color == 0) {
            if (x == x.parent.left) {
                TreeNode<E> w = x.parent.right;
                if (w.color == 1) {
                    w.color = 0;
                    x.parent.color = 1;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == 0 && w.right.color == 0) {
                    w.color = 1;
                    x = x.parent;
                } else {
                    if (w.right.color == 0) {
                        w.left.color = 0;
                        w.color = 1;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = 0;
                    w.right.color = 0;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                TreeNode<E> w = x.parent.left;
                if (w.color == 1) {
                    w.color = 0;
                    x.parent.color = 1;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.color == 0 && w.left.color == 0) {
                    w.color = 1;
                    x = x.parent;
                } else {
                    if (w.left.color == 0) {
                        w.right.color = 0;
                        w.color = 1;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = 0;
                    w.left.color = 0;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        if (x != null) {
            x.color = 0;
        }
    }

    private TreeNode<E> findSuccessor(TreeNode<E> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Metoda inorder() realizeaza parcurgerea in ordine a arborelui si afiseaza elementele in ordine crescatoare.
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(TreeNode<E> node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }
    /**
     * Metoda preOrderTraversal() realizeaza parcurgerea in pre-ordine a arborelui si afiseaza elementele
     */
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(TreeNode<E> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }



    private void leftRotate(TreeNode<E> x) {
        TreeNode<E> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }


    private void rightRotate(TreeNode<E> y) {
        TreeNode<E> x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }
        x.right = y;
        y.parent = x;
    }


    /**
     * Metoda preOrderTraversal() realizeaza parcurgerea in preordine a arborelui si
     * afiseaza elementele in ordinea aceasta.
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(TreeNode<E> node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }
}
