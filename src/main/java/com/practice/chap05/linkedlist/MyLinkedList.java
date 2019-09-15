package com.practice.chap05.linkedlist;

////////////////////////////////////////////////////////////////
class MyLinkedList {
    private Link first; // Ссылка на первый элемент
    private Link last; // Ссылка на последний элемент

    // -------------------------------------------------------------
    public MyLinkedList() {// Конструктор

        first = null; // Список пока не содержит элементов
        last = null;
    }

    // -------------------------------------------------------------
    public boolean isEmpty() {// true, если список пуст

        return first == null;
    }

    // -------------------------------------------------------------
    public void insertFirst(long dd) {// Вставка элемента в начало списка

        Link newLink = new Link(dd); // Создание нового элемента
        if (isEmpty()) // Если список пуст,
            last = newLink; // newLink <-- last
        newLink.next = first; // newLink --> старое значение first
        first = newLink; // first --> newLink
    }

    // -------------------------------------------------------------
    public void insertLast(long dd) {// Вставка элемента в конец списка

        Link newLink = new Link(dd); // Создание нового элемента
        if (isEmpty()) // Если список пуст,
            first = newLink; // first --> newLink
        else
            last.next = newLink; // Старое значение last --> newLink
        last = newLink; // newLink <-- last
    }

    // -------------------------------------------------------------
    public long deleteFirst() {// Удаление первого элемента списка
        // (предполагается, что список не пуст)
        long temp = first.dData;
        if (first.next == null) // Если только один элемент
            last = null; // null <-- last
        first = first.next; // first --> старое значение next
        return temp;
    }

    // -------------------------------------------------------------
    public void displayList() {
        System.out.print("List (first-->last): ");
        Link current = first; // От начала списка
        while (current != null) // Перемещение до конца списка
        {
            current.displayLink(); // Вывод данных
            current = current.next; // Переход к следующему элементу
        }
        System.out.println("");
    }

    // можно использовать для сортировки массива
    // trade-off - занимает в два раза больше памяти (надо держать и массив и лист одновременно)
    public void insert(long key) {// Вставка в порядке сортировки
        Link newLink = new Link(key); // Создание нового элемента
        Link previous = null; // От начала списка
        Link current = first;
// До конца списка
        while (current != null && key > current.dData) { // или если key > current,
            previous = current;
            current = current.next; // Перейти к следующему элементу
        }
        if (previous == null) // В начале списка
            first = newLink; // first --> newLink
        else // Не в начале
            previous.next = newLink; // старое значение prev --> newLink
        newLink.next = current; // newLink --> старое значение current
    }

    public Link find(int key) // Поиск элемента с заданным ключом
    { // (предполагается, что список не пуст)
        Link current = first; // Начиная с 'first'
        while(current.dData != key) // Пока совпадение не найдено
        {
            if(current.next == null) // Если достигнут конец списка
                return null; // и совпадение не найдено
            else // Если еще остались элементы
                current = current.next; // Перейти к следующему элементу
        }
        return current; // Совпадение обнаружено
    }
    // -------------------------------------------------------------

    // -------------------------------------------------------------
    class Link {
        public long dData; // Данные
        public Link next; // Следующий элемент в списке

        // -------------------------------------------------------------
        public Link(long d) // Конструктор
        {
            dData = d;
        }

        // -------------------------------------------------------------
        public void displayLink() // Вывод содержимого элемента
        {
            System.out.print(dData + " ");
        }
// -------------------------------------------------------------
    } // Конец класса Link
} // Конец класса FirstLastList
////////////////////////////////////////////////////////////////