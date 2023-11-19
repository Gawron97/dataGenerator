**1. Funkcja EXPLAIN. Na przygotowanych zapytaniach omówić zawartość wyniku działania funkcji EXPLAIN
i jej specyfiki wynikającej z wybranego systemu zarządzania bazą danych. Jakie informacje są tam
przedstawione? Jak je interpretować? Na czym polega optymalizacja zapytań?**

Przydatny link: https://thoughtbot.com/blog/reading-an-explain-analyze-query-plan

W MySQL, polecenie EXPLAIN dostarcza informacje o planie wykonania zapytania, natomiast w PostgreSQL, polecenie EXPLAIN ANALYZE 
dostarcza informacje o planie wykonania zapytania oraz dodatkowo informacje o czasie wykonania zapytania.

```postgresql
EXPLAIN ANALYZE
SELECT _user.first_name, _user.last_name, EXTRACT(YEAR FROM payment.creation_date) as payment_year,
       SUM(payment.price) as total_price
FROM payment
         JOIN student ON payment.id_student = student.id
         JOIN _user ON student.id_user = _user.id
WHERE EXTRACT(YEAR FROM payment.creation_date) = 2023 AND payment.id_payment_status = '15' AND
    NOT EXISTS (
        SELECT 1
        FROM contract_payments
        WHERE contract_payments.id_payment = payment.id
    )
GROUP BY _user.first_name, _user.last_name, EXTRACT(YEAR FROM payment.creation_date)
```

![Zrzut ekranu 2023-11-19 o 18.28.02.png](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fdatagenerator%2Fphotos%2FZrzut%20ekranu%202023-11-19%20o%2018.28.02.png)

Jakie informacje są tam przedstawione? Jak je interpretować? Na czym polega optymalizacja zapytań? Wyjaśnienie na przykładzie:

```HashAggregate:```

* cost=131.83..133.41 rows=105 width=77:
    * cost to szacowany koszt wykonania operacji grupowania.
    * rows to szacowana liczba grup.
    * width to szacowana szerokość wyniku wiersza.
* actual time=0.977..1.036 rows=163 loops=1:
    * actual time to faktyczny czas wykonania operacji.
    * rows to rzeczywista liczba zwróconych wierszy.
    * loops to liczba iteracji w danym etapie.

```Group Key:```

* Grupowanie wyników według kluczy: _user.first_name, _user.last_name, EXTRACT(year FROM payment.creation_date).

```Batches:```

* Liczba partii (batchy) przetwarzanych w danym etapie.

```Memory Usage:```

* Zużycie pamięci w danym etapie.

```Nested Loop:```

* Pętla zagnieżdżona, oznacza wykonanie operacji dla każdego wiersza z zewnętrznego etapu.

```Hash Join:```

* Łączenie dwóch zbiorów danych za pomocą hashowania.

```Seq Scan:```

* Pełne przeszukiwanie sekwencyjne tabeli.

```Index Scan:```

* Przeszukiwanie indeksu w poszukiwaniu pasujących rekordów.

```Index Only Scan:```

* Przeszukiwanie indeksu, zwracając tylko informacje z indeksu, bez konieczności odwoływania się do oryginalnej tabeli.

```Planning Time:```

* Czas potrzebny na analizę i planowanie wykonania zapytania.

```Execution Time:```

* Całkowity czas wykonania zapytania.


Optymalizacja zapytań przy użyciu EXPLAIN/EXPLAIN ANALYZE polega na analizie planu wykonania zapytania SQL w bazie danych. 
Dzięki EXPLAIN ANALYZE można identyfikować potencjalne bottlenecks, nieefektywne operacje czy braki w indeksach, co umożliwia 
dostosowanie zapytań lub struktury bazy danych w celu poprawy wydajności, na podstawie rzeczywistych statystyk wykonania.

Kilka porad do optymalizacji:
* Koszty i Szacunki:

Analizuj koszty i szacunki, aby zidentyfikować najbardziej kosztowne operacje. Możesz to wykorzystać do optymalizacji, np. przez dodanie indeksów, zmianę kolejności łączeń itp.
* Buckets i Memory Usage:

Sprawdzanie liczby kubełków (buckets) i zużycia pamięci może pomóc w dostosowaniu konfiguracji bazy danych, zwłaszcza gdy pracujesz z operacjami haszowania.
* Nested Loop vs. Hash Join:

Rozważ, czy zmiana rodzaju łączenia (Nested Loop, Hash Join) może poprawić wydajność.
* Seq Scan vs. Index Scan:

W przypadku pełnego przeszukiwania sekwencyjnego (Seq Scan) zastanów się nad dodaniem indeksów, aby przyspieszyć przeszukiwanie.
* Sort:

Zidentyfikuj operacje sortowania. Sortowanie może być kosztowne, dlatego warto sprawdzić, czy można zoptymalizować zapytanie tak, aby unikać sortowania.
* Optymalizacja Grupowania:

Grupowanie może być kosztowne, zwłaszcza jeśli operuje na dużej ilości danych. Upewnij się, że kolumny grupujące mają indeksy.


Przydatny link: https://thoughtbot.com/blog/advanced-postgres-performance-tips

**2. Wybór i wprowadzanie indeksów. Na podstawie dokumentacji wybranego systemu zarządzania bazą
danych do przygotowanych i wypełnionych tabel wprowadzić indeksy. Jakie są wady i zalety
poszczególnych typów indeksów i w jakich sytuacjach najlepiej ich używać?**



**3. Weryfikacja działania zapytań z poprzednich zajęć w świetle wyników funkcji EXPLAIN przed i po
wprowadzeniu indeksów. Czy można inaczej zdefiniować zapytania?**

