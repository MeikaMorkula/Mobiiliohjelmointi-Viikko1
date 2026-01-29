# Mobiiliohjelmointi-Viikko2

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

Kerro, miksi ViewModel on parempi kuin pelkkä remember:

remember muuttujat tuhotaan, aina kun UI päivittyy, kun taas ViewModel muuttujat säilyvät UI:n uudelleenpiirrosta


