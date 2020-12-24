# Opis projektu zaliczeniowego
## Cele projektu
Przedmiotem projektu zaliczeniowego części zajęć laboratoryjnych z przedmiotu Zarządzanie Bazami Danych SQL i NoSQL jest wykonanie prostej aplikacji, umożliwiającej definiowanie, przeglądanie i edytowanie danych, przechowywanych w bazie danych, przez użytkownika naiwnego (nie znającego technologii bazodanowych). Przed zaimplementowaniem aplikacji konieczne będzie zaprojektowanie i zaimplementowanie schematu relacyjnego, z którym będzie współpracowała aplikacja.  
  
Projekt ma być realizowany w grupach 2-osobowych. W uzasadnionych przypadkach liczba osób w grupie projektowej może ulec zmianie, jednak pociąga to za sobą zmianę wymagań co do stopnia złożoności projektu (np. inną minimalną liczbę encji).

Tematykę (modelowaną rzeczywistość) projektu grupa wymyśla samodzielnie. Ma być ona na tyle pojemna, aby spełnić wymagania, zawarte w punktach dot. wymagań dotyczących schematu relacyjnego.

## Wymagania dotyczące modelowanej bazy danych
  1.Rzeczywistość, wspierana przez aplikację, ma zostać zamodelowana przy zastosowaniu diagramu związków encji. Diagram ma składać się z min. 10 encji, połączonych związkami.
  2.Diagram związków encji musi zostać przygotowany w narzędziu Oracle SQL Developer lub Oracle SQL Developer Data Modeler.
  3.Diagram związków encji ma zostać przekształcony w schemat relacyjny. Oprócz relacji, implementujących encje, schemat powinien wykorzystywać:
    -sekwencje w sytuacji, gdy kolumny, będące kluczami podstawowymi relacji, będą liczbami (lub alternatywne metody automatycznego generowania wartości dla sztucznych kluczy liczbowych, np. kolumny Identity),
    -indeksy wspomagające wyszukiwanie danych w relacjach oraz wykonywanie innych operacji (np. sortowania, grupowania, itd.),
    -min. dwa podprogramy składowane w bazie danych: jedna funkcja i jedna procedura, wykonujące wskazane operacje (np. wyliczanie wartości złożonych wyrażeń); podprogramy mogą zostać połączone w jeden pakiet; podprogramy mają być wywoływane z aplikacji.
  4.Nie nakłada się żadnych ograniczeń co do systemu zarządzania bazą danych, w której będą przechowywane obiekty bazodanowe (dopuszczalne są dowolne produkty bazodanowe pod warunkiem, że będzie w nich możliwe zdefiniowanie obiektów wymienionych w wymaganiach, np. podprogramów składowanych). Przykładowe produkty: Microsoft SQL Server, MySQL, OracleDB, PostgreSQL, itd.
  5.Zadania projektowe dotyczące modelowania bazy danych są następujące:
    -zaprezentowanie krótkiego opisu funkcjonalności aplikacji i uzyskanie aprobaty prowadzącego: do 30 października 2020 r.,
    -zaprojektowanie diagramu związków encji, przedstawienie projektu prowadzącemu: do 22 listopada 2020 r., diagram musi zostać zaakceptowany przez prowadzącego,
    -prezentacja gotowego schematu relacyjnego wraz z obiektami dodatkowymi (sekwencjami, indeksami, podprogramami składowanymi, itd.): do 13 grudnia 2020 r. Przy prezentacji należy zaprezentować diagram relacji.

## Wymagania dotyczące aplikacji
Aplikacja ma składać się z kilku ekranów (okien), za pomocą których użytkownik będzie mógł realizować operacje na danych tabel ze schematu relacyjnego, na którym działa aplikacja.
Uwaga! Aplikacja powinna obsługiwać cały schemat. Oznacza to, że nie jest dopuszczalna sytuacja, w której pewne operacje (np. dodanie danych do jakiejś relacji) musi zostać wykonane poza aplikacją (np. przez uruchomienie polecenia SQL w edytorze SQL). Mogą istnieć odstępstwa od tej reguły (np. tabela wypełniana automatycznie przez procedurę wyzwalaną), jednak wymagane jest przedstawienie uzasadnienia.
Ekran aplikacji ma zostać wyposażony w mechanizmy, pozwalające użytkownikowi ma wykonywanie operacji na danych, takie jak:
wyświetlanie i przeglądanie danych,
wyszukiwanie danych – możliwość wprowadzania wzorca poszukiwanej informacji,
dodawanie danych,
modyfikacja danych,
usuwanie danych.
Uwaga! Ekran nie musi oferować wszystkich operacji – np. ekran typu "raport" jedynie prezentuje dane, nie pozwalając na ich edycję, z kolei ekran typu "formularz" pozwala definiować nowe dane i edytować dane istniejące, itd.
Formularz (ekran pozwalający na definicję i modyfikację danych) ma wspomagać użytkownika w procesie definiowania czy modyfikowania danych, podpowiadając użytkownikowi wartości dostępne dla określonego pola. Np. przy edycji danych pracownika (relacja Pracownicy) dostarczenie listy zespołów (z relacji Zespoly), do których pracownik może zostać przypisany, listy etatów, na których pracownik może zostać zatrudniony, itd.
Formularz ma kontrolować poprawność edytowanych przez użytkownika danych z punktu widzenia ograniczeń integralnościowych, zdefiniowanych w bazie danych, takich jak:
kontrola obecności określonych danych (np. wymuszenie podania nazwiska przy definiowaniu danych nowego pracownika),
kontrola wartości wpisywanych w polach (np. sprawdzanie zgodności wpisanej do pola wartości z wartościami dopuszczalnymi dla kolumny relacji, z którą pole jest skojarzone),
kontrola unikalności rekordów (klucz podstawowy i klucz unikalny),
kontrola usuwania rekordów (klucz obcy),
itd.
Uwaga! Należy unikać prezentowania użytkownikowi aplikacji komunikatów systemowych (np. "ORA-00001: naruszono więzy unikatowe" przy definiowaniu danych, naruszających klucz podstawowy). Zamiast tego, w sytuacji wystąpienia błędu, aplikacja powinna prezentować komunikat zrozumiały dla użytkownika "naiwnego" ("Próbujesz zdefiniować zespół, którego nazwa jest identyczna z nazwą już istniejącego zespołu").
Przy definiowaniu nowych danych formularz ma korzystać z mechanizmów systemu bazy danych, wspomagających generowanie wartości dla kolumn numerycznych będących kluczami podstawowymi (czyli np. z sekwencji w OracleDB, itd.).
Utworzone w bazie danych podprogramy składowane mają być wywoływane z chociaż jednego ekranu aplikacji (w przypadku funkcji jej wynik ma zostać zaprezentowany w ramach danych ekranu).
Nie ma żadnych ograniczeń jeśli chodzi o wykorzystywane przy budowaniu aplikacji technologie (może to być aplikacja webowa, mobilna, desktopowa, ...).
Zadania projektowe dotyczące aplikacji są następujące:
prezentacja projektu ekranów aplikacji: do 23 grudnia 2020 r. Projekt ma w schematycznie pokazać, jakie dane ekran zaprezentuje, w jakim układzie (tabelarycznym, formularza, itd.) i jakie operacje użytkownik będzie mógł na nim wykonać (definiowanie danych, odczyt danych, itd.),
prezentacja gotowych aplikacji i uzyskanie oceny projektu: do 31 stycznia 2021 r.
Harmonogram prac
do 30 października 2020 r. - zaprezentowanie krótkiego opisu funkcjonalności aplikacji i uzyskanie aprobaty prowadzącego,
do 22 listopada 2020 r. - zaprojektowanie diagramu związków encji, przedstawienie projektu diagramu prowadzącemu,
do 13 grudnia 2020 r. - prezentacja gotowego schematu relacyjnego wraz z obiektami dodatkowymi (sekwencjami, indeksami, podprogramami składowanymi, itd.),
do 23 grudnia 2020 r. - prezentacja projektu ekranów aplikacji,
do 31 stycznia 2021 r. - prezentacja gotowych aplikacji i uzyskanie oceny projektu.
Zasady oceniania
Ocena uzyskana z projektu odzwierciedla subiektywne wrażenie prowadzącego co do jakości zaprojektowanej aplikacji (jej funkcjonalności, łatwości użycia przez użytkownika, itd.). Istotnym elementem, wpływającym na wrażenie, jest jakość prezentacji aplikacji przez zespół.
Opóźnienie w stosunku do założonego harmonogramu skutkuje przypisaniem 1 punktu karnego za każdy rozpoczęty tydzień spóźnienia (dotyczy wszystkich punktów harmonogramu).
Każdy błąd, który pojawi się przy prezentacji gotowej aplikacji (np. nieudana próba modyfikacji danych, itd.), powoduje przypisanie 0,5 punktu karnego (przy czym wielokrotne występowanie w aplikacji tego samego błędu skutkuje przypisaniem liczby punktów równej liczbie wystąpień tego błędu).
Uwaga! Jeśli błąd uniemożliwia działanie aplikacji (w całości lub części), aplikacja zostaje odrzucona.
Pominięcie przy wykonaniu projektu elementu, wymaganego w projekcie, powoduje przypisanie dodatkowej liczby karnych punktów wg uznania prowadzącego (liczba punktów karnych jest zależna od ważności pomijanego elementu: np. brak implementacji procedury składowanej: 2 punkty karne, wyświetlenie użytkownikowi niezrozumiałego komunikatu systemowego: 0,5 punktu karnego, itd.).
Dopuszczalne jest zgromadzenie przez zespół 4 punktów karnych. Punkty powyżej czwartego przekładają się na obniżenie oceny z projektu wg następującej zasady: każde dwa punkty karne przekładają się na obniżenie oceny z projektu o 0,5 stopnia.
