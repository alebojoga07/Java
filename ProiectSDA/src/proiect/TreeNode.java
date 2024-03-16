package proiect;

/**
 * Clasa TreeNode reprezinta nodul unui arbore rosu-negru, care contine un element de tipul  E
 * si referinte catre nodurile din stanga si dreapta  ale acestui nod. De asemenea, prezinta si 
 * informatii despre culoarea nodului intr-un arbore rosu-negru (red-black).
 *
 * @param <E> Tipul elementelor stocate în nodul arborelui, care trebuie să fie Comparable.
 */
public class TreeNode<E extends Comparable<E>> {
    E data;
    TreeNode<E> parent, left, right;
    int color;

    public TreeNode(E data) {
        this.data = data;
        this.color = 1;
    }

    public E getData() {
        return data;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public int getColor() {
        return color;
    }
}
