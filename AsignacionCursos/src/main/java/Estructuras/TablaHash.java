/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Estudiante;
import Objetos.Usuario;
import java.awt.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;

/**
 *
 * @author meza4
 */
public class TablaHash<T> {

    private int CAPACITY;
    private float LF;
    private int MAX_SIZE;
    private List<T>[] arr;
    private int size = 0;

    public TablaHash() {
        CAPACITY = 37;
        LF = 0.55f;
        MAX_SIZE = (int) (LF * CAPACITY);
        arr = new List[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            arr[i] = new List<>();
        }
    }


    public TablaHash(int initialCapacity) {

        assert (initialCapacity > 0);

        CAPACITY = getNearestPowerTwo(initialCapacity);
        MAX_SIZE = (int) (LF * CAPACITY);

        arr = new List[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            arr[i] = new List<>();
        }
    }


    public TablaHash(int initialCapacity, int loadFactor) {
        assert (initialCapacity > 0);
        assert (loadFactor > 0 && loadFactor <= 1);

        CAPACITY = getNearestPowerTwo(initialCapacity);
        LF = loadFactor;
        MAX_SIZE = (int) (LF * CAPACITY);

        arr = new List[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            arr[i] = new List<>();
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Estudiante get(int carnet) {

        int index = hashing(carnet);
        List<T> list = arr[index];
        if (((Estudiante) list.getHead().val).getCarnet() == carnet) {
            return ((Estudiante) list.getHead().val);
        }
        return null;
    }

    public void modificarDato( Estudiante estu){
        Estudiante estudiante= get(estu.getCarnet());
        estudiante.setNombre(estu.getNombre());
        estudiante.setDireccion(estu.getDireccion());
    }
    
    public boolean add(T item) {
        if (item instanceof Estudiante) {
            try {
                if (get(((Estudiante) item).getCarnet()) != null) {
                    
                    return false;
                }
            } catch (Exception e) {
                

            }
        }
       
        if (contains(item)) {
            System.out.println("Contains");
            int cont = 0;
            while (containsColission(item, cont)) {
                System.out.println("ContainsColission");
                cont++;
            }
            addItemColission(item, cont);
            ++size;
            if (size == MAX_SIZE) {
                resize(); // resize
                return true;
            }

        } else {
            addItem(item); // add item into array
            ++size; // increase size
             System.out.println();
            if (size == MAX_SIZE) {
                resize(); // resize
                return true;
            }

        }

        return true;
    }

    private void addItem(T item) {
        if (item instanceof Estudiante) {
            Estudiante estu = (Estudiante) item;
            int index = hashing(estu.getCarnet()); // rehash
            arr[index].add(item);
        }

    }

    private void addItemColission(T item, int i) {
        if (item instanceof Estudiante) {
            Estudiante estu = (Estudiante) item;
            int index = hashingCollision(estu.getCarnet(), i); // rehash
            arr[index].add(item);
        }

    }

    public boolean contains(T item) {
        if (item instanceof Estudiante) {
            Estudiante estudiante = (Estudiante) item;
            int index = hashing(estudiante.getCarnet());
            List<T> list = arr[index];
            try {
                if (((Estudiante) list.getHead().val) != null) {
                    return true;
                }
            } catch (Exception e) {

            }
        }
        return false;
    }

    public boolean containsColission(T item, int i) {
        if (item instanceof Estudiante) {
            Estudiante estudiante = (Estudiante) item;
            int index = hashingCollision(estudiante.getCarnet(), i);
            List<T> list = arr[index];
            try {
                if (((Estudiante) list.getHead().val) != null) {
                    return true;
                }
            } catch (Exception e) {

            }
        }
        return false;
    }

    public boolean remove(int carnet) {

        int index = hashing(carnet);
        List<T> list = arr[index];

        if (list.getHead() != null) {
            if (((Estudiante) list.getHead().val).getCarnet() == carnet) {
                list.removeHead();
                size--;
                return true;
            }
        } else {
            return false;
        }

        return false;
    }

    private int getNearestPowerTwo(int capacity) {
        int shifts = 0;
        while (capacity > 0) {
            capacity = capacity >> 1;
            ++shifts;
        }
        return 1 << ++shifts;
    }

    @SuppressWarnings("unchecked")
    private boolean resize() {
        int temp = CAPACITY;
        CAPACITY = CAPACITY << 1;
        MAX_SIZE = (int) (LF * CAPACITY);
        List<T>[] arrCopy = arr;
        arr = new List[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            arr[i] = new List<>();
        }
        for (int i = 0; i < temp; i++) {
            Iterator<T> it = arrCopy[i].iterator();
            while (it.hasNext()) {
                addItem(it.next());
            }
        }
        return true;
    }

    // following hash() method in HashMap.class (Java JDK 1.8)
    public int hashing(int item) {
        int h = item % CAPACITY;
        return h;
    }

    public int hashingCollision(int item, int i) {

        int hash = ((item % 7) + 1) * i;
        return hash;

    }

    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < CAPACITY; i++) {
            sb.append(arr[i].toString() + (i == CAPACITY - 1 ? "" : ", "));
        }
        return sb.toString();
    }

    private class List<T> {

        public Node<T> getHead() {
            return head;
        }

        public void removeHead() {
            head = tail = null;
        }

        public void setHead(Node<T> head) {
            this.head = head;
        }

        public Node<T> head, tail;
        public int size;

        public List() {
            head = tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void add(T val) {
            if (head == null) {
                head = tail = new Node<>(val);
            } else {
                tail.next = new Node<>(val);
                tail = tail.next;
            }
            ++size;
        }

        public void remove(T val) {
            Node<T> current = head, prev = new Node<>(val);
            prev.next = current;
            while (current != null) {
                if (val.equals(current.val)) {
                    prev.next = current.next;
                    --size;
                    return;
                }
            }
            throw new NoSuchElementException();
        }

        public String toString() {
            Node<T> current = head;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            while (current != null) {
                sb.append(((Estudiante) current.val).getCarnet() + ",");
                current = current.next;
            }
            if (sb.length() > 1) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]");
            return sb.toString();
        }

        public Iterator<T> iterator() {
            return new ListIterator<>();
        }

        private class ListIterator<T> implements Iterator<T> {

            Node<T> next;

            public ListIterator() {
                next = (Node<T>) head;
            }

            public boolean hasNext() {
                return next != null;
            }

            public T next() {
                if (next == null) {
                    throw new NoSuchElementException();
                }
                T val = next.val;
                next = next.next;
                return val;
            }
            // incorrect and needs to be corrected!

            public void remove() {
                if (next == null) {
                    throw new NoSuchElementException();
                }
                T val = next.val;
                next = next.next;
            }

        }

        private class Node<T> {

            public T val;
            public Node<T> next;

            public Node(T val) {
                this.val = val;
                this.next = null;
            }
        }
    }
}
