package proiect;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Objects;

/**
 * Clasa RBPane reprezinta afisarea arborelui rosu-negru.
 *
 * Atribute:
 * - tree: Referinta catre arborele rosu-negru care va fi afisat.
 * - radius: Raza cercului reprezentand un nod al arborelui.
 * - vGap: Distanta intre noduri consecutive in arbore.
 */
public class RBPane extends JPanel {
    private RedBlackTree<Integer> tree;
    private double radius = 15;
    private double vGap = 50;

    /**
     * Constructorul clasei RBPane.
     *
     * @param tree Arborele rosu-negru care va fi afisat.
     */
    public RBPane(RedBlackTree<Integer> tree) {
        this.tree = tree;
    }

    public void setTree(RedBlackTree<Integer> tree) {
        this.tree = tree;
    }

    public void displayTree() {
        repaint();
    }

    /**
     * Metoda displayTree(TreeNode<Integer> root, int x, int y, int hGap, Graphics g) afiseaza recursiv arborele rosu-negru.
     *
     * @param root Radacina arborelui sau subarborelui.
     * @param x    Coordonata x a nodului.
     * @param y    Coordonata y a nodului.
     * @param hGap Distanța orizontală intre nodurile consecutive.
     * @param g    Contextul grafic pentru desenare.
     */
    private void displayTree(TreeNode<Integer> root, int x, int y, int hGap, Graphics g) {
        if (root != null) {
            if (root.getLeft() != null) {
                int leftX = x - hGap;
                int leftY = y + (int) vGap;
                drawLine(x, y, leftX, leftY, g);
                displayTree(root.getLeft(), leftX, leftY, hGap / 2, g);
            } else {
                drawNullLeaf(x - hGap, y + (int) vGap, g);
            }

            if (root.getRight() != null) {
                int rightX = x + hGap;
                int rightY = y + (int) vGap;
                drawLine(x, y, rightX, rightY, g);
                displayTree(root.getRight(), rightX, rightY, hGap / 2, g);
            } else {
                drawNullLeaf(x + hGap, y + (int) vGap, g);
            }

            drawCircle(x, y, radius, isRed(root), g);
            drawText(x - 4, y + 4, Objects.toString(root.getData()), g);
        }
    }
    /**
     * Metoda drawNullLeaf(int x, int y, Graphics g) deseneaza un cerc reprezentand un nod nul in arbore.
     *
     * @param x Coordonata x a nodului nul.
     * @param y Coordonata y a nodului nul.
     * @param g Contextul grafic pentru desenare.
     */
    private void drawNullLeaf(int x, int y, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);

        g2d.setColor(Color.GRAY);
        g2d.fill(circle);
        g2d.setColor(Color.BLACK);
        g2d.draw(circle);

        drawText(x - 10, y + 5, "null", g);
    }

    /**
     * Metoda drawLine(int x1, int y1, int x2, int y2, Graphics g) deseneaza o linie intre doua noduri in arbore.
     *
     * @param x1 Coordonata x a primului nod.
     * @param y1 Coordonata y a primului nod.
     * @param x2 Coordonata x a celui de-al doilea nod.
     * @param y2 Coordonata y a celui de-al doilea nod.
     * @param g  Contextul grafic pentru desenare.
     */
    private void drawLine(int x1, int y1, int x2, int y2, Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
    }

    /**
     * Metoda drawCircle(int x, int y, double radius, boolean isRed, Graphics g) desenează un cerc reprezentand un nod în arbore.
     *
     * @param x      Coordonata x a nodului.
     * @param y      Coordonata y a nodului.
     * @param radius Raza cercului.
     * @param isRed  Indicator pentru culoarea nodului (true pentru roșu, false pentru negru).
     * @param g      Contextul grafic pentru desenare.
     */
    private void drawCircle(int x, int y, double radius, boolean isRed, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
        g2d.setColor(isRed ? Color.RED : Color.BLACK);  // Am modificat Color.GRAY la negru pentru noduri negre
        g2d.fill(circle);
        g2d.setColor(Color.BLACK);
        g2d.draw(circle);
    }

    /**
     * Metoda drawText(int x, int y, String text, Graphics g) deseneaza text la coordonatele specificate în plan.
     *
     * @param x    Coordonata x a textului.
     * @param y    Coordonata y a textului.
     * @param text Textul de afisat.
     * @param g    Contextul grafic pentru desenare.
     */
    private void drawText(int x, int y, String text, Graphics g) {
        g.drawString(text, x, y);
    }

    private boolean isRed(TreeNode<Integer> node) {
        return node != null && node.getColor() == 0; // 0 pentru rosu, 1 pentru negru
    }

    /**
     * Suprascrie metoda paintComponent(Graphics g) pentru a desena arborele rosu-negru.
     *
     * @param g Contextul grafic pentru desenare.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        TreeNode<Integer> root = tree.getRoot();
        if (root != null) {
            displayTree(root, getWidth() / 2, 30, getWidth() / 4, g);
        }
    }
}
