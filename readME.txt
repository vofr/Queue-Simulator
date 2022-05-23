THREADURI

Acest proiect Java verifica cunostiintele in domeniul thredurilor.
Aplicatia se doreste a fi un simulator de cozi. Se genereaza persoane cu niste trasaturi random. In functie de timpul curent aceste persoane au de efectuate niste actiuni
in mod simultan (sa intre in coada, as astepte la coada, sa paraseasca coada, etc...). Aplicatia incepe prin a cere intervale de referinte in crearea persoanelor.Dupa un
numar, dat de catre user, de persoane random incepe actiunea threadurilor care simultan desfasoare un simulator de cozi, in care fiecare persoane in functie de un timp
random dintr-un interval oferit de la tastatura se aseaza la coada, in alt interval random, tot oferit de user, i se sfarseste actiune pentru care asteapta la coada si poate
parasi coada fara probleme. 
Acest proiect este format din 4 pachete, "business_logic", contine clasele care creaza threadurile si lucreaza cu datele furnizate pentru a simula corect aceasta incerca de coada.
Pachetul "default_pack" cu osingura clasa, MainClass, care da startul aplicatiei.
"gui_pack" cu 2 clase de tip GUI, una pentru inserarea datelor, cea de a doua pentru afisarea rezultatelor.
"model" contine 2 clase care simuleaza un obiect cu caracteristice proprii de tipul numele clasei.