package chap02;

public class MyArray {

    private int[] array;
    private int elemN;

    MyArray(int length) {
        this.array = new int[length];
        this.elemN = 0;
    }

    boolean find(int v) {
        for (int j = 0; j <= elemN; j++) {
            if (array[j] == v) {
                return true;
            }
        }
        return false;
    }

    private void insert(int v) {
        this.array[elemN] = v;
        elemN++;
    }

    private boolean delete(int v) {
        for (int i = 0; i <= elemN; i++) {
            if (array[i] == v) {
                for (int j = i; j <= elemN; j++) {
                    array[j] = array[j + 1];
                }
                return true;
            }
        }
        return false;
    }

    private String display() {
        StringBuilder ar = new StringBuilder();
        for (int i = 0; i < elemN; i++) {
            ar.append(array[i]).append(", ");
        }
        return ar.toString();
    }

    public static void main(String[] args) {
        MyArray myArray = new MyArray(6);
        System.out.println("Intial: " + myArray.display());
        myArray.insert(3);
        myArray.insert(5);
        System.out.println("After insert: " + myArray.display());
        myArray.find(3);
        System.out.println(myArray.find(3));
        System.out.println(myArray.delete(4));
        System.out.println(myArray.delete(3));
        myArray.display();
    }
}
