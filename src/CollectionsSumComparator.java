import java.util.Collection;
import java.util.Comparator;

// Tworzymy klasę, która implementuje Comparator dla kolekcji liczb (Number lub jego podklas)
public class CollectionSumComparator implements Comparator<Collection<? extends Number>> {

    // Nadpisujemy metodę compare, która porównuje dwie kolekcje typu Collection<? extends Number>
    @Override
    public int compare(Collection<? extends Number> c1, Collection<? extends Number> c2)
    {

        // Inicjalizujemy zmienną sum1, która będzie przechowywać sumę elementów w pierwszej kolekcji
        double sum1 = 0;

        // Iterujemy przez wszystkie elementy w kolekcji c1
        for (Number n : c1)
        {
            // Dodajemy wartość każdego elementu do sumy (używamy doubleValue(), aby upewnić się, że traktujemy liczby jako liczby zmiennoprzecinkowe)
            sum1 += n.doubleValue();
        }

        // Inicjalizujemy zmienną sum2, która będzie przechowywać sumę elementów w drugiej kolekcji
        double sum2 = 0;

        // Iterujemy przez wszystkie elementy w kolekcji c2
        for (Number n : c2)
        {
            // Dodajemy wartość każdego elementu do sumy (tak samo jak dla c1)
            sum2 += n.doubleValue();
        }

        // Porównujemy sumy dwóch kolekcji. Zwracamy:
        // - wartość ujemną, jeśli sum1 < sum2
        // - wartość dodatnią, jeśli sum1 > sum2
        // - 0, jeśli sum1 == sum2
        return Double.compare(sum1, sum2);
    }
}