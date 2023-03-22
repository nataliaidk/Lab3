package dsaa.lab03;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            object = e;
        }

        E object;
        Element next=null;
        Element prev=null;
    }

    Element head;
    Element tail;
    // can be realization with the field size or without
    int size;

    private class InnerIterator implements Iterator<E>{
        Element pos;


        public InnerIterator() {
            pos = head;
        }
        @Override
        public boolean hasNext() {
            //TODO
            return pos != null;

        }

        @Override
        public E next() {
            if (pos == null) throw new NoSuchElementException();
            E e = pos.object;
            pos = pos.next;
            return e;
        }
    }

    private class InnerListIterator implements ListIterator<E>{
        Element nextElement;
        Element previousElement;
        int index;

        public InnerListIterator()
        {
            nextElement = head;
            previousElement = null;
            index = 0;
        }
        @Override
        public void add(E e) {


        }

        @Override
        public boolean hasNext() {
            return nextElement != null;
        }

        @Override
        public boolean hasPrevious() {

            return previousElement !=null;
        }

        @Override
        public E next() {
            if (nextElement == null)
            {
                throw new NoSuchElementException();

            }
            E actElem = nextElement.object;
            previousElement = nextElement;
            nextElement = nextElement.next;

            index++;
            return actElem;
        }

        @Override
        public int nextIndex() {
            return index+1;
            //maybe index idk
        }

        @Override
        public E previous() {
            if (previousElement == null)
                throw new NoSuchElementException();

            E actElem= previousElement.object;
            nextElement = previousElement;
            previousElement = previousElement.prev;

            index--;
            return actElem;
        }

        @Override
        public int previousIndex() {
            return index-1;
        }

        @Override
        public void remove() {


        }

        @Override
        public void set(E e) {


        }
    }

    public TwoWayUnorderedListWithHeadAndTail() {
        // make a head and a tail
        head=null;
        tail=null;
        size = 0;
    }

    @Override
    public boolean add(E e) {
        Element element = new Element(e);
        if (head == null) {
            head = tail =element;


        }
        else {
            tail.next = element;
            element.prev = tail;
            tail = element;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size)
            throw new NoSuchElementException();

        Element newElement = new Element(element);

        if (index == 0) {
            if (head == null) {
                head = newElement;
                tail = newElement;
            } else {
                newElement.next = head;
                head.prev = newElement;
                head = newElement;
            }
        } else if (index == size) {
            tail.next = newElement;
            newElement.prev = tail;
            tail = newElement;
        } else {
            Element pos = head;
            for (int i = 0; i < index; i++) {
                pos = pos.next;
            }
            newElement.next = pos;
            newElement.prev = pos.prev;
            pos.prev.next = newElement;
            pos.prev = newElement;
        }

        size++;
    }


    @Override
    public void clear() {
        tail = null;
        head = null;
        size = 0;

    }

    @Override
    public boolean contains(E element) {
//        Element current = head;
//        while (current != null) {
//            if (current.object.equals(element)) {
//                return true;
//            }
//            current = current.next;
//        }
//        return false;
//        // to fix maybe
        for (E e : this) {
            if (e.equals(element))
                return true;
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Element current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.object;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Element current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E set = current.object;
        current.object = element;
        return set;

    }

    @Override
    public int indexOf(E element) {
        int index = 0;

        for (E e : this)
        {
            if (e.equals(element)) return index;
            index++;
        }

        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index ==0)
        {
            E ret = head.object;
            head = head.next;
            head.prev = null;
            return  ret;
        }

        Element actElem = head;
        for (int i =0; i < index -1; i++)
        {
            actElem = actElem.next;
        }

        E ret1 = actElem.next.object;
        actElem.next = actElem.next.next;

        if (actElem.next != null)
        {
            actElem.next.prev = actElem;

        }
        size--;
        return ret1;
    }

    @Override
    public boolean remove(E e) {
       if (head == null)
       {
           return false;
       }
       Element actElem = head;
       while (actElem != null){
           if (actElem.object.equals(e))
           {
               if (actElem.prev != null) actElem.prev.next = actElem.next;
               if (actElem.next != null ) actElem.next.prev = actElem.prev;
               if (actElem == head) head = actElem.next;
               if (actElem == tail) tail = actElem.prev;
               size--;
               return true;

           }
           actElem = actElem.next;
       }
        return false;

    }

    @Override
    public int size() {
        //TODO
        return size;
    }

    //odwracanie strignow
    public String toStringReverse() {
        ListIterator<E> iter = new InnerListIterator();
        String retStr = "";

        while (iter.hasNext()) {
            iter.next();
        }

        while (iter.hasPrevious()) {
            retStr += "\n" + iter.previous();
        }

        return retStr;
    }
//dodawanie list do siebie
    public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
            if (other == this) return;

            if (other.head != null)
            {
                if (head == null)
                {
                    head = other.head;
                    tail = other.tail;
                }
                else
                {
                    tail.next = other.head;
                    other.head.prev = tail;
                    tail = other.tail;
                }
                size += other.size;
            }
            other.clear();

    }
}

