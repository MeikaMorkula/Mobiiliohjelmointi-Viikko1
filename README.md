# Mobiiliohjelmointi-Viikko1

Tämä sovellus on simppeli android tehtävälista sovellus.

Sovellus näyttää olemassa olevat taskit listana

Sovelluksessa on tekstikenttä, johon voi kirjoittaa tehtävän nimen.
Tekstikentän vieressä on "add new task"-nappi, jota painamalla uusi taski lisätään listaan

Nappi käyttää addTask-funktiota, jolle annetaan parametreinä Task-olioista sisältyvä lista, uusi Task-olio, joka lisätään annettuun listaan. Addtask palauttaa uuden listan, johon uusi task olio on lisätty.

Listassa olevien taskien vieressä on "Done/Undo"-nappi, joka muuttaa taskin done-tilaa, riippuen siitä onko done true vai false

Nappi käyttää toggleDone -funktiota, jolle annettaan parameterinä Task-olioista muodostuva lista, sekä muutettavan taskin ID. Funktio käy map-funktiolla listan läpi, kunnes se löytää oikean taskin id:n mukaan, ja muuttaa taskin done-tilaa seuraavasti: false-> true/true->false

Sovelluksen alareunassa on kolme filtteröintinappia.

"Sort by priority"-nappi järjestää listan taskit prioriteetin mukaan, nousevassa järjestyksessä

"Sort by Date"-nappi järjestää listan "Due-date":n mukaan nousevassa järjestyksessä.

"Sort by Done"- nappi järjestää listan tehtyjen ja ei tehtyjen taskien mukaan

Kaikki kolme filtteröintinappia käyttävät omia toimintojaan vastaavia funktioita: sortTasksByPriority, sortTasksByDueDate ja sortTasksByDone. Kaikki funktiot ottavat sisään Task-olioita sisältävän listan, joka käydään läpi listan mukana tulleella sortedBy-funktiolla. Funktio järjestää listan funktiota vastaavan arvon mukaan it.priority/dueDate/Done ja palauttaa uuden järjestetyn listan.


