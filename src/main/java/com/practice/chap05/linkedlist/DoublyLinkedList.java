package com.practice.chap05.linkedlist;

public class DoublyLinkedList {

    public Link first; // Ссылка на первый элемент списка
    public Link last; // Ссылка на последний элемент списка

    // -------------------------------------------------------------
    public DoublyLinkedList() {// Конструктор
        first = null; // Список пока не содержит элементов
        last = null;
    }

    // -------------------------------------------------------------
    public boolean isEmpty() {// true, если список пуст
        return first == null;
    }

    // -------------------------------------------------------------
    public void addFirst(long dd) {// Вставка элемента в начало списка
        Link newLink = new Link(dd); // Создание нового элемента
        if (isEmpty()) // Если список не содержит элементов,
            last = newLink; // newLink <-- last
        else
            first.previous = newLink; // newLink <-- старое значение first
        newLink.next = first; // newLink --> старое значение first
        first = newLink; // first --> newLink
    }

    // -------------------------------------------------------------
    public void addLast(long dd) {// элемент в конец списка
        Link newLink = new Link(dd); // Создание нового элемента
        if (isEmpty()) // Если список не содержит элементов,
            first = newLink; // first --> newLink
        else {
            last.next = newLink; // старое значение last --> newLink
            newLink.previous = last; // старое значение last <-- newLink
        }
        last = newLink; // newLink <-- last
    }

    /**
     * Необходимо учесть особый случай, когда текущий элемент является первым в списке,
     * потому что ссылка на него хранится в поле first объекта LinkList, а не в другом
     * элементе списка. В этом случае удаление элемента осуществляется занесением
     * в first ссылки из first.next
     */
    // -------------------------------------------------------------
    public Link removeFirst() {// Удаление первого элемента
        // (предполагается, что список не пуст)
        Link temp = first;
        if (first.next == null) // Если только один элемент
            last = null; // null <-- last
        else
            first.next.previous = null; // null <-- старое значение next
        first = first.next; // first --> старое значение next
        return temp;
    }

    // -------------------------------------------------------------
    public Link removeLast() {// Удаление последнего элемента
        // (предполагается, что список не пуст)
        Link temp = last;
        if (first.next == null) // Если только один элемент
            first = null; // first --> null
        else
            last.previous.next = null; // старое значение previous --> null
        last = last.previous; // старое значение previous <-- last
        return temp;
    }

    // -------------------------------------------------------------
// Вставка dd в позицию после key
    public boolean insertAfter(long key, long dd) { // (предполагается, что список не пуст)
        Link current = first; // От начала списка
        while (current.dData != key) {// Пока не будет найдено совпадение
            current = current.next; // Переход к следующему элементу
            if (current == null)
                return false; // Ключ не найден
        }

        Link newLink = new Link(dd); // Создание нового элемента
        if (current == last) {// Для последнего элемента списка
            newLink.next = null; // newLink --> null
            last = newLink; // newLink <-- last
        } else {// Не последний элемент
            newLink.next = current.next; // newLink --> старое значение next
// newLink <-- старое значение next
            current.next.previous = newLink;
        }
        newLink.previous = current; // старое значение current <-- newLink
        current.next = newLink; // старое значение current --> newLink
        return true; // Ключ найден, вставка выполнена
    }

    // -------------------------------------------------------------
    public Link deleteKey(long key) {// Удаление элемента с заданным ключом
        // (предполагается, что список не пуст)
        Link current = first; // От начала списка
        while (current.dData != key) {// Пока не будет найдено совпадение
            current = current.next; // Переход к следующему элементу
            if (current == null)
                return null; // Ключ не найден
        }
        if (current == first) // Ключ найден; это первый элемент?
            first = current.next; // first --> старое значение next
        else // Не первый элемент
// старое значение previous --> старое значение next
            current.previous.next = current.next;
        if (current == last) // Последний элемент?
            last = current.previous; // старое значение previous <-- last
        else // Не последний элемент
// Старое значение previous <-- старое значение next
            current.next.previous = current.previous;
        return current; // Возвращение удаленного элемента
    }

    // -------------------------------------------------------------
    public void displayForward() {
        System.out.print("List (first-->last): ");
        Link current = first; // От начала списка
        while (current != null) {// Перемещение до конца списка
            current.displayLink(); // Вывод данных
            current = current.next; // Переход к следующему элементу
        }
        System.out.println("");
    }

    // -------------------------------------------------------------
    public void displayBackward() {
        System.out.print("List (last-->first): ");
        Link current = last; // От начала списка
        while (current != null) {// Перемещение до конца списка
            current.displayLink(); // Вывод данных
            current = current.previous; // Переход к следующему элементу
        }
        System.out.println("");
    }

    public class Link {
        public long dData; // Данные
        public Link next; // Следующий элемент в списке
        public Link previous; // Предыдущий элемент в списке

        // -------------------------------------------------------------
        public Link(long d) {// Конструктор
            dData = d;
        }

        // -------------------------------------------------------------
        public void displayLink() {// Вывод содержимого элемента
            System.out.print(dData + " ");
        }
// -------------------------------------------------------------
    }
// -------------------------------------------------------------
} // Конец класса DoublyLinkedList

////////////////////////////////////////////////////////////////
class DoublyLinkedApp {
    public static void main(String[] args) { // Создание нового списка
        DoublyLinkedList theList = new DoublyLinkedList();
        theList.addFirst(22); // Вставка в начале
        theList.addFirst(44);
        theList.addFirst(66);
        theList.addLast(11); // Вставка в конце
        theList.addLast(33);
        theList.addLast(55);
        theList.displayForward(); // Вывод в прямом направлении
        theList.displayBackward(); // Вывод в обратном направлении
        theList.removeFirst(); // Удаление первого элемента
        theList.removeLast(); // Удаление последнего элемента
        theList.deleteKey(11); // Удаление элемента с ключом 11
        theList.displayForward(); // Вывод в прямом направлении
        theList.insertAfter(22, 77); // Вставка 77 после 22
        theList.insertAfter(33, 88); // Вставка 88 после 33
        theList.displayForward(); // Вывод в прямом направлении
    }
} // Конец класса DoublyLinkedApp
////////////////////////////////////////////////////////////////