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

<h5>Typy indeksów:</h5>

* Indeks B-drzewny (B-Tree):
  * Zalety:
    * Działa dobrze dla różnych typów zapytań, zwłaszcza równościowych i zakresowych.
    * Działa efektywnie dla różnych typów danych, takich jak liczby, ciągi znaków, daty itp.
    * Stosunkowo niskie zużycie pamięci w porównaniu do niektórych innych indeksów.
  * Wady:
    * Mniej efektywny dla operacji złączeń i sortowań.
    * Może występować wyższe zużycie miejsca na dysku niż niektóre specjalizowane indeksy.

* Indeks haszowy:
  * Zalety:
    * Bardzo szybki dla prostych operacji równościowych.
    * Skuteczny w przypadku wyszukiwań na dużych zbiorach danych, gdzie operacje haszowe są wydajne.
  * Wady:
    * Nie obsługuje operacji zakresowych.
    * Zajmuje więcej miejsca na dysku niż indeksy B-drzewne.

* Indeks GiST (Generalized Search Tree):
  * Zalety:
    * Bardzo skuteczny dla danych przestrzennych i innych niestandardowych typów danych.
    * Obsługuje różne operacje przeszukiwania, takie jak długość łańcucha, operatory itp.
  * Wady:
    * Wyższe zużycie miejsca na dysku niż indeksy B-drzewne.
    * Wyższe koszty operacji zapisu i aktualizacji.

* Indeks GIN (Generalized Inverted Index):
  * Zalety:
    * Bardzo skuteczny dla danych złożonych, takich jak tablice, hstore (mapy klucz-wartość) itp.
    * Działa dobrze dla zapytań związanych z pełnotekstowym przeszukiwaniem.
  * Wady:
    * Wyższe koszty aktualizacji w porównaniu do indeksów B-drzewnych.
    * Wymaga więcej miejsca na dysku niż indeksy B-drzewne.


<h5>Sytuacje najlepszego użycia:</h5>
* Indeks B-drzewny (B-Tree):
  * Wyszukiwanie równościowe i zakresowe.
  * Sortowanie i złączenia na niewielkich zbiorach danych.

* Indeks haszowy:
  * Wyszukiwanie równościowe na dużych zbiorach danych, gdzie efektywność operacji haszowej jest kluczowa.

* Indeks GiST (Generalized Search Tree):
  * Dane przestrzenne, tekstowe lub niestandardowe typy danych.

* Indeks GIN (Generalized Inverted Index):
  * Tablice, mapy klucz-wartość, pełnotekstowe przeszukiwanie.


**3. Weryfikacja działania zapytań z poprzednich zajęć w świetle wyników funkcji EXPLAIN przed i po
wprowadzeniu indeksów. Czy można inaczej zdefiniować zapytania?**

* wprowadzony indeks do tabeli payment na creation_date:
EXTRACT(YEAR FROM creation_date) i EXTRACT(MONTH FROM creation_date)
  * dla 14 zapytania spadek kosztu
    * z HashAggregate  (cost=974.51..974.88 rows=30 width=548)
    * do HashAggregate  (cost=448.41..448.79 rows=30 width=548)
  * dla 16 zapytania spadek kosztu
    * z GroupAggregate  (cost=1075.07..1075.82 rows=25 width=53)
    * do GroupAggregate  (cost=548.98..549.73 rows=25 width=53)
  * dla 19 zapytania spadek kosztu
    * z GroupAggregate  (cost=884.88..884.90 rows=1 width=548)
    * do GroupAggregate  (cost=28.20..28.22 rows=1 width=548)

* wprowadzony indeks do tabeli application na submission_date:
EXTRACT(YEAR FROM submission_date)
  * dla 13 zapytania spadek kosztu
    * z Sort  (cost=8972.43..8972.58 rows=60 width=524)
    * do Sort  (cost=3450.38..3450.53 rows=60 width=524)
    
* wprowadzony indeks do tabeli complain na author:
  * dla 17 zapytania
    * z HashAggregate  (cost=11901.28..12273.98 rows=37270 width=33)
    * do GroupAggregate  (cost=1494.43..1497.31 rows=144 width=34)