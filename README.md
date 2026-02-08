# Mobiiliohjelmointi-Viikko 4

Tämä sovellus on simppeli android tehtävälista sovellus.

Sovellus näyttää olemassa olevat taskit listana

Sovelluksessa on tekstikenttä, johon voi kirjoittaa tehtävän nimen.
Tekstikentän alla on "add new task"-nappi, jota painamalla uusi taski lisätään listaan

Nappi käyttää addTask-funktiota, jolle annetaan parametreinä Task-olioista sisältyvä lista, uusi Task-olio, joka lisätään annettuun listaan. Addtask palauttaa uuden listan, johon uusi task olio on lisätty.

Listassa olevien taskien vieressä on "Done/Undo"-checkbox, joka muuttaa taskin done-tilaa, riippuen siitä onko done true vai false

Nappi käyttää toggleDone -funktiota, jolle annettaan parameterinä Task-olioista muodostuva lista, sekä muutettavan taskin ID. Funktio käy map-funktiolla listan läpi, kunnes se löytää oikean taskin id:n mukaan, ja muuttaa taskin done-tilaa seuraavasti: false-> true/true->false

Sovelluksen alareunassa on kolme filtteröintinappia.

"Sort by Date"-nappi järjestää listan "Due-date":n mukaan nousevassa järjestyksessä.

"Sort by Done"- nappi järjestää listan tehtyjen ja ei tehtyjen taskien mukaan

Selitä, miten Compose-tilanhallinta toimii:
Compose-tilanhallinta pitää UI-ta tilan funktiona, joka johtaa siihen, että kun tila muuttuu, Compose piirtään UI:n automaattisesti uudelleen.


Selitä MVVM, miksi se on hyödyllinen Compose-sovelluksissa.

Model-View-ViewModel (MVVM) on suunnittelumalli joka eriyttää sovelluksen eri kerroksiin. View sisältää UI:n ja käyttäjän vuorovaikutuksen, ViewModel sisältää UI-logiikan ja tilanhallinan ja model sisältää liiketoimintalogiikan ja datan. Tämä on hyödyllistä siksi, että UI-uudelleenpiirretään automaattisesti kun tilat muuttuvat, mutta data säilyy. MVVM myös selkeyttää koodia.

Kerro miten StateFlow toimii.

Stateflow on muuttuja jota UI kuuntelee. Kun StateFlown tila muuttuu UI päivityy automaattisesti. 


Kerro, miksi ViewModel on parempi kuin pelkkä remember:

remember muuttujat tuhotaan, aina kun UI päivittyy, kun taas ViewModel muuttujat säilyvät UI:n uudelleenpiirrosta


Mitä tarkoittaa navigointi Jetpack Composessa:
  Jetpack composessa navigointi tarkoittaa eri käyttöliittymä screenien välillä siirtymistä

Mitä ovat NavHost ja NavController:
  NavController vastaa navigoinnin ohjaamisestä, esim ruutujen välillä siirtymisestä ja takaisin menosta
  NavHost vastaa sovelluksen navigointirakenteesta, se yhdistää routet composable funktioihin.

Miten MVVM ja navigointi yhdistyvät (yksi ViewModel kahdelle screenille:
    Homescreen ja Calendar screen käyttävät samaa viewmodelia, joka annetaan molemmille näkymille parametreina. Viewmodel luodaan mainActivityssä navHosin yläpuollella.

Miten ViewModelin tila jaetaan kummankin ruudun välillä:
  ViewModeli sisältää tehtävät-tilan, joka on toteutettu stateflow:lla. Kun tehtävien tila muuttuu, niin myös Homescreen ja CalendarScreen päivittyvät automattisesti viewmodelin kautta:

Miten CalendarScreen on toteutettu (miten tehtävät ryhmitellään / esitetään kalenterimaisesti):
  Tehtävät ryhmitellään deadlinen mukaan, näkymässä näkyy päivämäärä (esim: 2026-2-2 ja sen alla sille päivälle merkatut tehtävät.

Miten AlertDialog hoitaa addTask ja editTask:
  AlertDiagog käyttää samaa komponenttia sekä taskin lisäämiseen ja muokkaukseen. Jos taskilla ei ole id:tä, niin alertDialog näyttää taskin lisäämiseen tarvittavat kentät, jos taas taskilla on Id: niin taskile näytetään muokkaus ja poistoominaisuudet.


