# Kalkulator makroskladnikow
Aplikacja służy do obliczania dziennego zapotrzebowania kalorycznego, wpisując dane tj.: wzrost, waga, wiek, ilość treningów w tygodniu, długość treningu, płeć czy rodzaj budowy ciała. Po obliczeniu dziennego zapotrzebowania użytkownik ma możliwość zapisania danych I sporządzenia listy posiłków tak, aby spożyć odpowiednie produkty w ciągu dnia dla jego warunków fizycznych. 
Aplikacja skierowana jest dla szerokiego grona odbiorców - osób ćwiczących, jak i niećwiczących.


## Aplikacja po uruchomieniu
Po uruchomieniu aplikacji wyświetla się kalkulator z prostym formularzem do wypełnienia. 
Aplikacja posiada menu nawigacyjne u dołu ekranu, które pozwala swobodnie poruszać się pomiędzy kalkulatorem, a listą posiłków.  
Posiada również – RESET - dodatkową opcje, która jest dostępna po przejściu do listy. Umożliwia ona zresetowanie listy oraz zapisanych danych o kaloriach i makroskładnikach.  
![alt text](https://github.com/ArkadiuszMlynarski/Kalkulator_makroskladnikow/blob/master/readmeimages/1.png "")

## Obliczanie
Gdy wypełnimy formularz i klikniemy przycisk OBLICZ zostanie obliczone dzienne zapotrzebowanie kaloryczne wraz z wcześniej ukrytymi makroskładnikami (białko, węglowodany, tłuszcze).  
![alt text](https://github.com/ArkadiuszMlynarski/Kalkulator_makroskladnikow/blob/master/readmeimages/2.png "")

## Zapisywanie
Gdy klikniemy przycisk ZAPISZ, nasze obliczone wartości zostaną zapisane i przeniesione do listy posiłków. Zostajemy powiadomieni, że dane zostały zapisane.  
![alt text](https://github.com/ArkadiuszMlynarski/Kalkulator_makroskladnikow/blob/master/readmeimages/3.png "")

## Lista posiłków
U góry okna widzimy formularz, dzięki któremu możemy dodawać posiłki, wprowadzając nazwe, makroskładniki (białko, węglowodany, tłuszcze oraz kalorie). U dołu znajdują się progressbary, które wypełniają się dynamicznie wraz z uzupełnianiem listy posiłków, informując użytkownika ile powinien jeszcze spożyć makroskładników w ciągu dnia.  
![alt text](https://github.com/ArkadiuszMlynarski/Kalkulator_makroskladnikow/blob/master/readmeimages/4.png "")

## Usuwanie posiłków z listy 
Aby usunąc dany posiłek musimy w niego kliknąć. Usuwając posiłek progressbar również się aktualizuje.  
![alt text](https://github.com/ArkadiuszMlynarski/Kalkulator_makroskladnikow/blob/master/readmeimages/5.png "")

## Resetowanie danych
Aplikacja umożliwia również szybkie zresetowanie listy oraz zapisanych wcześniej danych o makroskładnikach. Wykonać to możemy wybierając opcje RESET w menu nawigacyjnym u dołu ekranu. O pomyślnym resecie zostajemy powiadomieni komunikatem ‘Zresetowano dane’.  
![alt text](https://github.com/ArkadiuszMlynarski/Kalkulator_makroskladnikow/blob/master/readmeimages/6.png "")

## SharedPreferences – zapis stanu aplikacji
Aplikacja dzięki użyciu SharedPreferences zapisuje dane po wyjściu z niej do pliku .xml, dzięki czemu po ponownym uruchomieniu nie tracimy danych.  
saveData()  
![alt text](https://github.com/ArkadiuszMlynarski/Kalkulator_makroskladnikow/blob/master/readmeimages/7.png "")  

loadData()  
![alt text](https://github.com/ArkadiuszMlynarski/Kalkulator_makroskladnikow/blob/master/readmeimages/8.png "")  

shared preferences.xml  
![alt text](https://github.com/ArkadiuszMlynarski/Kalkulator_makroskladnikow/blob/master/readmeimages/9.png "")
