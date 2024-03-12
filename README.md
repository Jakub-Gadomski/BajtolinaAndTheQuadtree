# BajtolinaAndTheQuadtree
#gdd
#JakubGadomski
gra z dzewem 4-kowym

tytuł: "Gąsiennica i Drzewo Czwórkowe" (prawdopodobnie będzie zmieniony)

gracze:
gra jednoosobowa

analiza gry:
Gracz wcieli się w sympatyczną gąsiennię Bajtolinę. Żyje ona na drzewie czwórkowym, któro wyrosło w pobliżu ruin starej elektrowni atomowej.
Promieniowanie, które panuje w pobliżu spowodowało, że Bajtolina aby zrealizować swoje marzenie i przemienić się w motyla,
musi walczyć z innymi groźnymi stworzeniami, które zamieszkują drzewo. 
Gracz będzie musiał znaleść pożywienie oraz ciekawe przedmioty, które pomogą mu w walce z innymi zwierzętami.
Pomimo iż gewo czwórkowe zamieszkują groźne istoty to mogą się znaleść też przyjazne osobniki (albo i nie).

cel gry:
znaleźenie odpowiedniego miejsca do stwzorzenia kokonu i przepoczwarzenie się w motyla

platormy:
PC z systemem windows lub linux (albo i nie)

język programowania:
java (najprawdopodobniej)

mechaniki:
-podnoszenie przedmiotów, artefaktów, zasobów
-jedzenie liści, któro może skończyć się spadkiem w przepaść
-walka z wrogimi istotami
-rozmowa z niewrogimi istotami


Mechaniki, które muszą się pojawić w grze:
-walka
-przemieszczanie się po mapie
-przechodzenie z liścia na liść
-zbieranie artefaktów i innych zasobów
-śmierć
-wraz z czasem przeciwnicy rosną w siłę
-gra turowa CZASU CIĄGŁEGO

Mechaniki, które być może pojawią się w grze:
-zjadanie liścia pod sobą, który następnie zacznie się rozpadać (ale wcześniej nas uleczy/da nam premiedo statystyk// otrzymamy ciekawy efekt???)
-rozwój bohatera (nowe umiejętności)
-mgła wojny
-handel i rozmowa z postaciami pobocznymi

generator losowego świata:
-zależny od czasu???
-generuje kilka liści drzewa czwórkowego
    -drzewo jest ukorzenione
    -każdy wierzchołek ma 4 potomków
        -potomek może być 'biały', 'czarny', 'bajtolinowy'
        -biały nie ma potomków i "liść ten już spadł z drzewa" co oznacza, że nie ma tam nic (brak pomieszczenia w grze w miejscu tego wieszchołka.
        -czarny nie ma potomków i "liść ten wisi na drzewie" co oznacza, że w miejscu tego liścia jest pomieszczenie w grze po którym gracz się będzie poruszał
        -bajtolinowy ma dokładnie 4 potomków w dowolnych kolorach, jest odpowiednikiem gałęzi w drzewie (ale nie można się po nim przemieszczać)
    -'czarne' liście sąsiadują ze sobą jeżeli w drzewie czwórkowym 'czarne' liście stworzyłyby jedną spójną ciągłą figurę
    -gracz może przechodzić tylko pomiędzy sąsiadującymi ze sobą liśćmi
-generuje obszar liścia złożony z pól w kształcie sześciokątów foremnych lub kwadratów
    -obszar składać się będzie z 50-300 pól w zależności od tego jak długo będą trwały moje gry testowe (albo jak długo mogłby trwać)
    -na brzegach liścia będą się znajdowały 2 rodzaje specjalnych pól
        -jasnozielone - które prowadzą do innych liści
        -ciemnozielone - które są granicą liścia lub ścianą, której nie da się przejść
    -na pozostałych polach będą znajdowały się obiekty przeciwnicy i gracz, którzy się będą po nich poruszać
    -generator generuje pola liścia w ten sposób, że tworzy 1 pole gdzieś na środku a kolejne pola generują się od już obok tych co istnieją
-generuje przeciwników w losowych miejscach
-generuje przeciwników w nielosowych miejscach
-generuje obiekty w losowych miejscach
-generuje przyjaźnie nastawione byty, które nie zaatakują gracza(albo zaatakują jeśli zechcą lub gracz je sprowokuje) w losowych miejscach

Obiekty
-bronie
    -strzelające
    -amunicja
    -bronie białe
-pomniejsze przedmioty wywołujące efekty
    -pył z elektrowni???
    -patyki z drzewa???
    -owoce drzewa czwórkowego???
    -porzucone ubrania zmutowanych zwierząt zamieszkujących drzewo?
    -żywica drzewa (wiele typów)
    -miód pszczeli z ula na drzewie


Gracz

-wciela się w gąsienicę Bajtolinę
-statystki
    -obrona - ???
    -atak - ???
    -hp(punkty zdrowia) - ???
    -prędkość - 2 pola na turę — podnoszenie przedmiotów liczy się jako 1, atak liczy się jako 2
    -exp
    -poziom doświadczenia

-ekwipunek
    ???

-interakcje ze światem
    -zaatakowanie — zużywa ruch, atakuje przeciwnika
        -atak dystansowy
        -atak wręcz
    -handel
    -otrzymywanie obrażeń
    -możliwość podnoszenia przedmiotów
    -zjadanie liścia pod sobą
    -śmierć od spadku w przepaść
    -śmierć od przeciwników
    -widzi na 3/4 kratki od miejsca, w którym się znajduje
    -przedmioty mogą mu coś zasłonić (albo nie)
    -zdobywanie punktów doświadczenia i poziomów, które zwiększają statystyki i pozwalają wybrać nowe umiejętności

-dodatkowe umiejętności zdobywane wraz z rozwojem bohatera
    -gracz może je wybrać, gdy zdobędzie poziom doświadczenia
    -permanentne aż do zakończenia bieżącej rozgrywki
    lista umiejętności ( nazwy do zmiany):
    -sokoli wzrok - zwiększa pole widzenia o 1 pole
    -szczęście - gracz ma (10-50)% szans na zadanie większych obrażeń przeciwnikowi
    -prędkość BKP —-zwiększa ruch gracza o 1 pole na turę (BKP - Bajtockie Koleje Państwowe)
    -żywe kolce - gracz zadaje część obrażeń, które sam otrzymuje od przeciwników, którzy atakują go wręcz
    -atomowa ewolucja krwi - gracz regeneruje pewną ilość punktów zdrowia
    -celność RoBit Hooda - gracz spowalnia przeciwników na pewną ilość tur, gdy zaatakuje ich z dystansu
    -siła Mario P. - co 2/3/4 (zależnie od tego, jak trudna gra będzie) atak gracza wręcz ogłusza przeciwnika, przez co traci on ruch w sojej turze
    -żądza krwi - gracz leczy się o pewien procent obrażeń, które sam zadaje

Przeciwnicy
-statystyki
    -obrona
    -atak
    -hp(punkty zdrowia)
    -prędkość

-ekwipunek
    ???

-interakcje ze światem
    -zaatakowanie - zużywa ruch, atakuje przeciwnika
        -atak dystansowy
        -atak wręcz
    -otrzymywanie obrażeń
    -możliwość podnoszenia przedmiotów???
    -śmierć od spadku w przepaść
    -śmierć od przeciwników
    -widzi na 3/4/5/6 kratki od miejsca, w którym się znajduje
    -przedmioty mogą mu coś zasłonić (albo nie)



Lista przeciwników i krótki opis
-mrówki
    -niskie obrażenia
    -niska wytrzymałość
    -występuje ich od 2/4 do 3/7 (w zależności jak trudna gra się okarze) blisko siebie
    -średnia prędkość (może się ruszyć o 2 pola na ture (tyle, co gracz))
-pszczoły
    -średnie obrażenia
    -niska wytrzymałość
    -szybkie
-złe gąsienice
    -średnie obrażenia
    -wysoka wytrzymałość
    -wolne
-wiele(lub niewiele) innych

Przedmioty i ekwipunek
    -najprawdopodobniej będzie to wyglądać, tak że gracz będzie mógł mieć jedną broń jednocześnie w swoim ekwipunku
    -przeciwnicy również będą miały tylko jedną broń w ekwipunku

    -gracz i inne byty będą mogły mieć do tego inne przedmioty, ale tylko po jednym danego rodzaju
    rodzaje przedmiotów
        -bronie (zwiększają atak)
            -zasięgowe (pozwalają atakować na dystans)
                -łuk z patyków
                -proca
                -tajemnicza kusza
                -...
            -białe
                -kij
                -sztylet
                -miecz
                -...
        -zbroja (zwiększają obronę)
            -chitynowy pancerzyk
            -liściasta koszula
            -lśniąca zbroja
            -...
        -buty
            -buty szybkości (zwiększają prędkość)
            -buty siedmiomilowe (pozwalają skakać ponad przeszkodami i przeciwnikami)
            -modne kozaki (spowalniają, ale za to zmniejszają otrzymywane obrażenia)
        -hełmy
            -ocieplająca czapka z pomponem (regeneruje punktu zdrowia)
            -kapelusz Heisenberga (sprawia, że jego nosiciel jednocześnie jest i jednocześnie go nie ma - przenikanie przez przeszkody i byty)
            -hełm rycerza(zwiększają obronę)

