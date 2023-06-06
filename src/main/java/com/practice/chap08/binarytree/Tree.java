package com.practice.chap08.binarytree;

public class Tree {

    private Node root; // Единственное поле данных

    public Node find(int key) {// Поиск узла с заданным ключом
        // (предполагается, что дерево не пустое)
        Node current = root; // Начать с корневого узла
        while (current.iData != key) {// Пока не найдено совпадение
            if (key < current.iData) {// Двигаться налево?
                current = current.leftChild;
            } else {
                current = current.rightChild; // Или направо?
            }
            if (current == null) {// Если потомка нет,
                return null; // поиск завершился неудачей
            }
        }
        return current; // Элемент найден
    }

    public void insert(int id, double dd) {
        Node newNode = new Node(); // Создание нового узла
        newNode.iData = id; // Вставка данных
        newNode.dData = dd;
        if (root == null) {// Корневой узел не существует
            root = newNode;
        } else {// Корневой узел занят
            Node current = root; // Начать с корневого узла
            Node parent;
            while (true) {// (Внутренний выход из цикла)
                parent = current;
                if (id < current.iData) {// Двигаться налево?
                    current = current.leftChild;
                    if (current == null) {// Если достигнут конец цепочки
                        parent.leftChild = newNode; // вставить слева
                        return;
                    }
                } else {// Или направо?
                    current = current.rightChild;
                    if (current == null) {// Если достигнут конец цепочки,
                        parent.rightChild = newNode;// вставить справа
                        return;
                    }
                }
            }
        }
    }

    //-----------------------------------------------------------------------
    // симметричный обход дерева - инфиксная запись (inorder traversal) А*(B+C)
    private void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.iData + " "); // "посещение"
            inOrder(localRoot.rightChild);
        }
    }

    // прямой обход дерева - префиксная запись (preorder traversal) (*А+BC)
    private void preOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.iData + " "); // "посещение"
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    // обратный обход дерева - постфиксная запись (postorder traversal) (АBC+*)
    private void postOrder(Node localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + " "); // "посещение"
        }
    }
    //-----------------------------------------------------------------------

    public Node minimum() {// Возвращает узел с минимальным ключом
        Node current = root; // Обход начинается с корневого узла
        Node last = null;
        while (current != null) {// и продолжается до низа
            last = current; // Сохранение узла
            current = current.leftChild; // Переход к левому потомку
        }
        return last;
    }

    class Node {
        int iData; // Данные, используемые в качестве ключа
        double dData; // Другие данные
        Node leftChild; // Левый потомок узла
        Node rightChild; // Правый потомок узла

        public void displayNode() {
        }
    }

}
