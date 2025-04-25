import java.util.Collection;
import java.util.Comparator;

// Klasa CollectionSizeComparator implementuje interfejs Comparator dla kolekcji typu Collection<?>
public class CollectionSizeComparator implements Comparator<Collection<?>>
{

    // Nadpisujemy metodę compare, która porównuje dwie kolekcje pod względem ich rozmiarów
    @Override
    public int compare(Collection<?> c1, Collection<?> c2)
    {

        // Porównujemy rozmiary obu kolekcji, używając Integer.compare do porównania
        // Metoda Integer.compare zwraca:
        // - wartość ujemną, jeśli rozmiar c1 < rozmiar c2
        // - wartość dodatnią, jeśli rozmiar c1 > rozmiar c2
        // - 0, jeśli rozmiar c1 == rozmiar c2
        return Integer.compare(c1.size(), c2.size());
    }
}

