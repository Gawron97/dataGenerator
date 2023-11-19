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

![Zrzut ekranu 2023-11-19 o 18.12.37.png](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fdatagenerator%2Fphotos%2FZrzut%20ekranu%202023-11-19%20o%2018.12.37.png)

Jakie informacje są tam przedstawione? Jak je interpretować? Na czym polega optymalizacja zapytań? Odpowiem na przykładzie powyżej:

```GroupAggregate (cost=21.83..21.86 rows=1 width=77) (actual time=0.802..0.803 rows=0 loops=1)```

GroupAggregate: Wskazuje na grupowanie wyników. 
cost=21.83..21.86: Szacowane koszty wykonania operacji grupowania.
rows=1: Szacowana liczba grup.
width=77: Szacowana szerokość wyniku wiersza.
actual time=0.802..0.803: Rzeczywisty czas wykonania operacji.
rows=0: Rzeczywista liczba wierszy zwróconych przez to zapytanie.
loops=1: Liczba iteracji (często 1 dla prostych zapytań).
Group Key: _user.first_name, _user.last_name, (EXTRACT(year FROM payment.creation_date))
Wskazuje kolumny, według których następuje grupowanie.

```Sort (cost=21.83..21.83 rows=1 width=51) (actual time=0.801..0.802 rows=0 loops=1)```

Sort: Wskazuje na sortowanie wyników.
cost=21.83..21.83: Szacowane koszty sortowania.
rows=0: Rzeczywista liczba wierszy zwróconych przez to sortowanie.
Sort Key: _user.first_name, _user.last_name: Kolumny, według których następuje sortowanie.
Sort Method: quicksort Memory: 25kB: Metoda sortowania i zużycie pamięci.

```Nested Loop Anti Join (cost=4.79..21.82 rows=1 width=51) (actual time=0.286..0.287 rows=0 loops=1)```

Nested Loop Anti Join: Wskazuje na zastosowanie operacji "anti join" z zagnieżdżonymi pętlami.
cost=4.79..21.82: Szacowane koszty wykonania.
rows=0: Rzeczywista liczba wierszy zwróconych przez to złączenie.
width=51: Szacowana szerokość wyniku wiersza.
actual time=0.286..0.287: Rzeczywisty czas wykonania operacji.

```Bitmap Heap Scan on contract_payments (cost=4.24..14.91 rows=11 width=4) (never executed)```

Bitmap Heap Scan: Wskazuje na skanowanie bitmapy na indeksie.
cost=4.24..14.91: Szacowane koszty skanowania bitmapy.
rows=11: Szacowana liczba wierszy zwróconych przez to skanowanie.
width=4: Szacowana szerokość wyniku wiersza.
never executed: Wskazuje, że ta część planu nie została wykonana.

```Planning Time: 0.386 ms```

Planning Time: Czas potrzebny na analizę i planowanie wykonania zapytania.

```Execution Time: 0.118 ms```

Execution Time: Całkowity czas wykonania zapytania.


Optymalizacja zapytań przy użyciu EXPLAIN/EXPLAIN ANALYZE polega na analizie planu wykonania zapytania SQL w bazie danych. 
Dzięki EXPLAIN ANALYZE można identyfikować potencjalne bottlenecks, nieefektywne operacje czy braki w indeksach, co umożliwia 
dostosowanie zapytań lub struktury bazy danych w celu poprawy wydajności, na podstawie rzeczywistych statystyk wykonania.

Przydatny link: https://thoughtbot.com/blog/advanced-postgres-performance-tips

**2. Wybór i wprowadzanie indeksów. Na podstawie dokumentacji wybranego systemu zarządzania bazą
danych do przygotowanych i wypełnionych tabel wprowadzić indeksy. Jakie są wady i zalety
poszczególnych typów indeksów i w jakich sytuacjach najlepiej ich używać?**



**3. Weryfikacja działania zapytań z poprzednich zajęć w świetle wyników funkcji EXPLAIN przed i po
wprowadzeniu indeksów. Czy można inaczej zdefiniować zapytania?**

