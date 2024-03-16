package proiect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa TreeVisualizer reprezinta  interfata grafica pentru vizualizarea arborelui rosu-negru.
 * Permite adaugarea si stergerea de noduri in arbore, precum si afisarea traversarilor in preordine si postordine
 * ale arborelui.
 */

public class TreeVisualizer extends JFrame {
    private RedBlackTree<Integer> redBlackTree;
    private JPanel treePanel;
    private JLabel preOrderLabel;

    /**
     * Construiește un Visualizator pentru Arborele Roșu-Negru și inițializează interfața utilizator.
     */
    public TreeVisualizer() {
        redBlackTree = new RedBlackTree<>();
        initializeUI();
    }

    /**
     * Inițializează interfața grafică pentru Visualizatorul Arborelui Roșu-Negru.
     * Creează butoane pentru inserarea, ștergerea nodurilor și efectuarea traversărilor în arbore.
     */
    private void initializeUI() {
        setTitle("Red-Black Tree Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        treePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTree(g, getWidth() / 2, 30, redBlackTree.getRoot(), getWidth() / 4);
            }
        };
        treePanel.setPreferredSize(new Dimension(800, 500));

        JButton insertButton = new JButton("Insert Node");
        insertButton.addActionListener(e -> {
            int value = Integer.parseInt(JOptionPane.showInputDialog("Insert value:"));
            redBlackTree.insert(value);
            treePanel.repaint();
        });

        JButton deleteButton = new JButton("Delete Node");
        deleteButton.addActionListener(e -> {
            int value = Integer.parseInt(JOptionPane.showInputDialog("Delete value:"));
            redBlackTree.delete(value);
            treePanel.repaint();

        });

        JButton traversalButton = new JButton("Parcurgere In-Ordine");
        traversalButton.addActionListener(e -> {
            System.out.print("Parcurgere In-Ordine: ");
            redBlackTree.inOrderTraversal();
            System.out.println();
        });

        preOrderLabel = new JLabel("Parcurgere Pre-Ordine: ");
        JButton preOrderButton = new JButton("Parcurgere Pre-Ordine");
        preOrderButton.addActionListener(e -> {
            System.out.print("Parcurgere Pre-Ordine: ");
            redBlackTree.preOrderTraversal();
            System.out.println();
        });

        JButton postOrderButton = new JButton("Parcurgere Post-Ordine");
        postOrderButton.addActionListener(e -> {
            System.out.print("Parcurgere Post-Ordine: ");
            redBlackTree.postOrderTraversal();
            System.out.println();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(insertButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(traversalButton);
        buttonPanel.add(preOrderButton);
        buttonPanel.add(postOrderButton);

        JPanel labelPanel = new JPanel();
        labelPanel.add(preOrderLabel);

        add(treePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(labelPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    
    /**
     * Desenează arborele în componenta grafică specificată.
     * @param g Grafica pentru desenare.
     * @param x Coordonata x a centrului arborelui.
     * @param y Coordonata y a centrului arborelui.
     * @param node Nodul curent în desenat.
     * @param xOffset Offset pentru desenarea copiilor nodului curent.
     */
    private void drawTree(Graphics g, int x, int y, TreeNode<Integer> node, int xOffset) {
        if (node != null) {
            g.setColor(node.color == 0 ? Color.BLACK : Color.RED);
            g.fillOval(x - 15, y - 15, 30, 30);
            g.setColor(Color.WHITE);
            g.drawString(node.data.toString(), x - 5, y + 5);
            if (node.left != null) {
                g.setColor(Color.BLACK);
                g.drawLine(x, y, x - xOffset, y + 50);
                drawTree(g, x - xOffset, y + 50, node.left, xOffset / 2);
            }
            if (node.right != null) {
                g.setColor(Color.BLACK);
                g.drawLine(x, y, x + xOffset, y + 50);
                drawTree(g, x + xOffset, y + 50, node.right, xOffset / 2);
            }
        }
    }

    /**
     * Punctul de intrare în aplicație.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TreeVisualizer::new);
    }
}
