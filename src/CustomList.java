import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

// Definicja klasy generycznej CustomList, gdzie T to typ przechowywanych danych
public class CustomList<T> extends AbstractList<T>
{
    // Wewnętrzna klasa Node reprezentująca pojedynczy element listy (Node-węzeł/można interpretować jako pojedynczy element)
    private class Node<T>
    {
      T value;    // Przechowuje wartość danego elementu
      Node<T> next;  // Wskaźnik na następny element w liście

        // Konstruktor przyjmujący wartość elementu
        public Node(T value)
        {
            this.value=value;
            this.next=null; // Domyślnie brak następnego elementu
        }
    }

    private Node<T> head;  // Referencja do pierwszego elementu listy (początek)
    private Node<T> tail;  // Referencja do ostatniego elementu listy (koniec)
    private int size=0;   // Licznik elementów w liście(rozmiar)

    //Konstruktor
    public CustomList()
    {
        this.head=null;
        this.tail=null;
        this.size=0;
    }

    // Metoda dodająca element na koniec listy
    public void addLast(T value)
    {
        // Tworzymy nowy węzeł z podaną wartością
        Node<T> newNode=new Node<>(value);

        if(tail==null)
        {
            // Ustawiamy zarówno head, jak i tail na nowy węzeł
            head=tail=newNode;
        }
        else
        {
            tail.next = newNode;  // Dotychczasowy ostatni element wskazuje na nowy
            tail = newNode;       // Aktualizujemy tail, żeby wskazywał na nowy koniec listy
        }

        size++;  // Zwiększamy licznik elementów

    }

    // Metoda zwracająca wartość z końca listy
    public T getLast()
    {
        if(tail==null)
        {
            throw new IllegalStateException("Lista jest pusta");
        }

        // Zwracamy wartość z ostatniego elementu
        return tail.value;
    }

    // Metoda dodająca element na początek listy
    public void addFirst(T value)
    {
        Node<T> newNode=new Node<>(value);

        if(head==null)
        {
            // Lista jest pusta — nowy węzeł staje się zarówno head, jak i tail
            head=tail=newNode;
        }
        else
        {

            // Podpinamy stary head jako następny element nowego
            newNode.next = head;
            // Ustawiamy newNode jako nowy początek listy
            head = newNode;
        }
        size++;  // Zwiększamy licznik elementów


    }
    // Metoda zwracająca wartość z początku listy
    public T getFirst()
    {
        if(head==null)
        {
            throw new IllegalStateException("Lista jest pusta");
        }

        // Zwracamy wartość z pierwszego elementu
        return head.value;
    }

    // Metoda usuwająca pierwszy element z listy i zwracająca jego wartość
    public T removeFirst()
    {
        // Sprawdzamy, czy lista jest pusta
        if (head == null) {

            // Jeśli lista jest pusta, rzucamy wyjątek
            throw new IllegalStateException("Lista jest pusta");
        }


        T resultValue = head.value; // Zapisujemy wartość pierwszego elementu do zmiennej value

        // Sprawdzamy, czy lista zawiera tylko jeden element
        if (head == tail)
        {
            // Jeśli lista ma tylko jeden element, ustawiamy head i tail na null
            head = tail = null;
        }
        else
        {
            // Jeśli lista ma więcej niż jeden element, przestawiamy head na kolejny element
            head = head.next;
        }

        size--;  // Zmniejszamy rozmiar listy

        // Zwracamy wartość usuniętego elementu
        return resultValue;
    }

    // Metoda usuwająca ostatni element z listy i zwracająca jego wartość
    public T removeLast()
    {

        // Jeśli lista jest pusta (nie ma żadnego elementu)
        if (tail == null)
        {
            // Nie można nic usunąć — rzucamy wyjątek
            throw new IllegalStateException("Lista jest pusta");
        }

        // Zapisujemy wartość ostatniego elementu do zmiennej, żeby ją potem zwrócić
        T resultValue = tail.value;

        // Sprawdzamy, czy lista zawiera tylko jeden element
        if (head == tail)
        {
            // Lista ma tylko jeden element — po jego usunięciu lista będzie pusta
            head = tail = null;  // Ustawiamy oba wskaźniki na null
        } else
        {
            // Potrzebujemy znaleźć przedostatni element w liście
            Node<T> current = head; // Zaczynamy od początku listy

            // Szukamy elementu, który wskazuje na tail (czyli przedostatniego)
            while (current.next != tail)
            {
                current = current.next; // Przechodzimy do kolejnego elementu
            }

            // Gdy znaleźliśmy przedostatni element:
            current.next = null; // Odłączamy ostatni element (czyli tail)
            tail = current;      // Aktualizujemy tail — teraz wskazuje na przedostatni
        }


        size--;  // Zmniejszamy rozmiar listy

        // Zwracamy wartość usuniętego elementu
        return resultValue;
    }


    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder("Lista\n[");

        Node<T> current = head;

        while (current != null)
        {
            result.append(current.value);

            if (current.next != null)
            {
                result.append(", ");
            }

            current = current.next;
        }

        result.append("]");

        return result.toString();
    }

    @Override
    public boolean add(T t)
    {
        addLast(t); // używamy już zaimplementowanej metody

        return true;
    }

    @Override

    public int size()
    {
        return size;
    }

    @Override
    public T get(int index)
    {
        // 1. Sprawdzamy, czy index jest w poprawnym zakresie
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Nieprawidłowy index: " + index);
        }

        // 2. Zaczynamy od początku listy
        Node<T> current = head;

        // 3. Przechodzimy index razy do przodu
        for (int i = 0; i < index; i++)
        {
            current = current.next;
        }

        // 4. Zwracamy wartość węzła na podanym indeksie
        return current.value;
    }


    @Override
    public Iterator<T> iterator()
    {
        // Zwracamy nowy anonimowy obiekt klasy Iterator
        return new Iterator<T>()
        {
            // Prywatna zmienna wskazująca na aktualny element listy podczas iteracji
            private Node<T> current=head;

            @Override
            // Sprawdzamy, czy istnieje kolejny element
            public boolean hasNext()
            {
                return current!=null;// Jeśli current nie jest null, to jeszcze coś zostało
            }

            @Override
            // Zwracamy wartość aktualnego elementu i przesuwamy się dalej
            public T next()
            {
                if(current==null)
                {
                    // Jeśli nie ma już elementów, rzucamy wyjątek
                    throw new NoSuchElementException();
                }

                // Przechowujemy wartość bieżącego elementu
                T value= current.value;
                // Przesuwamy się do następnego węzła
                current=current.next;

                // Zwracamy wartość bieżącego elementu
                return value;
            }
        };

    }

    @Override
    public Stream<T> stream()
    {

        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
    }


    // Metoda sprawdza, czy dana wartość znajduje się w otwartym przedziale (min, max)
    public static <T extends Comparable<T>> boolean isInOpenRange(T value, T min, T max)
    {
        // Porównujemy wartość z dolną granicą: czy jest większa niż min
        // oraz z górną granicą: czy jest mniejsza niż max
        return value.compareTo(min) > 0 && value.compareTo(max) < 0;
    }

    // Metoda liczy, ile elementów z listy znajduje się w otwartym przedziale (min, max)
    public static <T extends Comparable<T>> int countInOpenRange(List<T> list, T min, T max)
    {

        // Inicjalizujemy licznik pasujących elementów
        int count = 0;

        // Iterujemy po wszystkich elementach listy
        for (T value : list)
        {
            // Sprawdzamy, czy element znajduje się w przedziale (min, max)
            if (isInOpenRange(value, min, max))
            {
                // Jeśli tak, zwiększamy licznik
                count++;
            }
        }

        // Zwracamy końcową liczbę elementów spełniających warunek
        return count;
    }





}
