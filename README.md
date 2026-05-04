# mitarbeiterverwaltung-java
JavaFX-Anwendung zur Mitarbeiterverwaltung. Ermöglicht Speichern, Laden und Bearbeiten von Daten über eine Textdatei (mitarbeiterliste.txt). Nutzung von Map und Enums zur Strukturierung. Persistenz sorgt dafür, dass Daten beim Neustart erhalten bleiben.

JavaFX-Anwendung zur Mitarbeiterverwaltung

1. Einführung
Dieses Projekt verwaltet die Mitarbeiter (Arbeitnehmer) eines Unternehmens (Unternehmen).
Es ermöglicht das Speichern, Lesen und Schreiben von Mitarbeitern in eine Textdatei (mitarbeiterliste.txt).
Die Daten werden im Speicher gehalten, indem eine Map<Integer, Arbeitnehmer> in der Klasse Unternehmenverwendet wird.
Enums werden verwendet, um Abteilungen, Positionen, Status und Vertragsarten zu definieren. Die Anwendung verfügt über ein grafisches Frontend mit JavaFX 21, das die Benutzerfreundlichkeit verbessert und die Registrierung sowie Verwaltung der Mitarbeiter erleichtert.
Die Anwendung erfüllt Persistenz-Eigenschaften, das bedeutet, dass die Daten der Arbeitnehmer beim Schließen des Programms nicht verloren gehen.
Die Daten werden in einer Textdatei mitarbeiterliste.txt gespeichert, wobei die Methode toFileString()verwendet wird.
Wenn das Programm später erneut geöffnet wird, kann diese Datei gelesen werden und die Objekte Arbeitnehmer werden mithilfe der Methode fromString wiederhergestellt.
<img width="468" height="370" alt="image" src="https://github.com/user-attachments/assets/f2bce2d2-b6d8-46d8-b79d-8411ff8243f7" />

Systemfunktionalität
Im Folgenden wird die Funktionalität des Systems anhand von User Stories und Use Cases dargestellt, um zu zeigen, wie die verschiedenen Komponenten interagieren und welche Ziele für den Endbenutzer erreicht werden.
•	User Stories: Kurze narrative Beschreibungen, die erklären, welche Bedürfnisse der Benutzer hat und was er mit dem System erreichen möchte.
•	Use Cases: Detailliertere Szenarien, die die Abfolge von Aktionen und die Interaktionen zwischen Benutzer und System zeigen.
Diese Kombination ermöglicht es, sowohl die Perspektive des Benutzers als auch die interne Logik der Software zu verstehen, erleichtert die Validierung der Anforderungen und die Planung zukünftiger Verbesserungen.


Story 1: Neue Mitarbeiter im System erfassen, Duplikate vermeiden und Datenkonsistenz sicherstellen
Use Cases
Benutzer (Administrator):
•	Gibt die persönlichen Daten des Mitarbeiters ein (Vorname, Nachname, Geburtsdatum, Adresse, E-Mail, Ausweisnummer, Telefonnummer, Geburtsort, Staatsangehörigkeit, Familienstand, Kinderanzahl).
•	Gibt die beruflichen Daten ein (Abteilung, Position, Vertragsart, Status).
System:
•	Überprüft, dass alle Pflichtfelder ausgefüllt sind (z. B. Vorname, Nachname, E-Mail, Ausweisnummer).
•	Prüft, dass keine Duplikate vorhanden sind:
•	gleiche ID → automatisch über idZaehler generiert
•	gleiche E-Mail
•	gleiche Kombination Vorname + Nachname
•	gleiche Ausweisnummer
•	Erstellt ein Arbeitnehmer-Objekt mit allen eingegebenen Daten.
•	Speichert das Objekt im Speicher (arbeitnehmerMap in Unternehmen).
•	Wandelt das Objekt in das Datei-Format um (toFileString()) und speichert es in mitarbeiterliste.txt zur Persistenz.

•	/**
 * Dabei wird für jeden neuen Mitarbeiter mithilfe des idZaehler-Zählers eine eindeutige ID generiert.
 */
int neueid= neueId();
System.out.println(" zugewiesene ID :" + neueid);

/**
 * Erstellen Sie das Arbeitnehmerobjekt mit dem Hauptkonstruktor
 */
Arbeitnehmer neueArbeitnehmer = new Arbeitnehmer(neueid,vorname, nachname, emailAdresse, ausweisnummer, telefonnummer, geburtsdatum, adresse, geburtsort, staatsangehoerigkeit, familienstand, kinderAnzahl, vertragsart, abteilung, positionImAbteilung, statuss);



/**
 * Im Speicher speichern
 */
unternehmen.getArbeitnehmerMap().put(neueid, neueArbeitnehmer);

/**
 * in Datei speichern
 */
speichernMitarbeiter(neueArbeitnehmer);

Erwartetes Ergebnis:
•	Der Mitarbeiter wird korrekt registriert, ohne Duplikate.
•	Die Daten bleiben erhalten, auch wenn das Programm geschlossen und wieder geöffnet wird.


Story 2: Mitarbeiter aus der Datei laden
Ziel: Ermöglichen, dass das System die zuvor gespeicherte Mitarbeiterliste aus mitarbeiterliste.txt wiederherstellt.
Use Cases
Benutzer (Administrator):
•	Startet den Ladevorgang der gespeicherten Mitarbeiterliste.
System:
•	Liest alle Zeilen aus der Datei mitarbeiterliste.txt.
•	Wandelt jede Zeile in ein Arbeitnehmer-Objekt um, indem die Methode Arbeitnehmer.fromString() verwendet wird.
•	Die Enums (Vertragsart, Abteilung, PositionImAbteilung, Statuss) werden automatisch aus den Textwerten erstellt.
•	Die Daten werden aus Strings in das richtige Format konvertiert, z. B. Datumsangaben im Format dd.MM.yyyy.
•	Fügt jedes Objekt direkt in das arbeitnehmerMap in Unternehmen ein.
•	Aktualisiert den internen ID-Zähler (idZaehler), um zukünftige eindeutige IDs zu gewährleisten.
public void ladenMitarbeiterAusMitarbeiterliste(){

    List<String> datenMitarbeiteliste = Datei.lessenDatei();

    int letzteid =-1;
    for (String zeile: datenMitarbeiteliste){
        Arbeitnehmer arbeitnehmerZeile = Arbeitnehmer.fromString(zeile);
        unternehmen.getArbeitnehmerMap().put(arbeitnehmerZeile.getIdPerson(),arbeitnehmerZeile);
    if (arbeitnehmerZeile.getIdPerson() > letzteid){
        letzteid = arbeitnehmerZeile.getIdPerson();
        }
    }
    this.idZaehler = letzteid +1; // se inicializa idZaehler
}

Erwartetes Ergebnis:
•	Alle Mitarbeiter werden korrekt aus der Datei geladen.
•	Die Objekte stehen vollständig im Speicher zur Verfügung und können weiterverarbeitet werden.

Story 3: Anzeige der Mitarbeiterliste
Ziel: Dem Administrator ermöglichen, alle registrierten Mitarbeiter einzusehen.

Use Cases
Benutzer (Administrator):
•	Fordert die Anzeige aller registrierten Mitarbeiter an.
System:
•	Liest die Mitarbeiter aus dem Arbeitsspeicher (arbeitnehmerMap in Unternehmen).
•	Iteriert über die Mitarbeiter, sortiert nach ID.
•	Zeigt alle relevanten Attribute in lesbarer Form an.
•	Verwendet ein konsistentes Datumsformat (dd.MM.yyyy) und gibt bei fehlenden Werten ein Platzhalterzeichen (-) aus.

public String toFileString(){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    return idPerson + ";"
            + vorname + ";"
            + nachname + ";"
            + emailAdresse + ";"
            + ausweisnummer + ";"
            + telefonnummer + ";"
            + geburtsdatum.format(formatter) + ";"
            + adresse + ";"
            + geburtsort + ";"
            + staatsangehoerigkeit + ";"
            + familienstand + ";"
            + kinderAnzahl + ";"
            + vertragsart.name() +";"
            + abteilung.name() +";"
            + positionImAbteilung.name() + ";"
            + statuss.name() + ";"
            + eintrittsdatum.format(formatter) +";";
}

public static Arbeitnehmer fromString(String zeile){
    String [] teile = zeile.split(";");
   int idperson = Integer.parseInt(teile[0]);
   //crea la lista con los demas campos
    List<String> personesDaten = new ArrayList<>();
    for (int i = 1; i <= 11 ; i++) {
        personesDaten.add(teile[i]);
    }

/** Enums und weitere Felder separat lesen*/
    Vertragsart vertragsart = Vertragsart.valueOf(teile[12]);
    Abteilung abteilung = Abteilung.valueOf(teile[13]);
    PositionImAbteilung positionImAbteilung = PositionImAbteilung.valueOf(teile[14]);
    Statuss statuss = Statuss.valueOf(teile[15]);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate eintrittsdatum = LocalDate.parse(teile[16], formatter );
    /** Neues Objekt mit den extrahierten Werten erstellen*/
    return new Arbeitnehmer(idperson, personesDaten, vertragsart, abteilung, positionImAbteilung, statuss, eintrittsdatum);
}

Erwartetes Ergebnis:
•	Alle Mitarbeiter werden korrekt und einheitlich formatiert angezeigt.
•	Die Ausgabe ist sortiert (ID aufsteigend) und robust gegenüber fehlenden Feldern.
Story 4: Änderungen in der Datei speichern
Ziel: Sicherstellen der Persistenz der Daten, indem alle Mitarbeiterinformationen in einer Textdatei gespeichert werden.
Use Cases
Benutzer (Administrator):
•	Initiiert den Speicherprozess der Mitarbeiterdaten.
System:
•	Öffnet die Datei mitarbeiterliste.txt im Schreibmodus.
•	Iteriert über die Liste der Mitarbeiter (arbeitnehmerMap in Unternehmen).
•	Schreibt jeden Mitarbeiter mit der Methode toFileString() in die Datei.
•	Schließt die Datei nach Abschluss des Schreibvorgangs.
public static void speichernMitarbeiter(Arbeitnehmer neueArbeitnehmer){
    try(BufferedWriter writer = new BufferedWriter
            (new FileWriter("mitarbeiterliste.txt", true))){
        writer.write(neueArbeitnehmer.toFileString());
        writer.newLine();
        System.out.println("Mitarbeiter gespeichert in mitarbeiterliste.txt");

    }catch (IOException exception) {
        System.out.println("error al guardar el archivo:"+ exception.getMessage());
    }
}
•	Erwartetes Ergebnis:
•	Alle Mitarbeiterdaten werden korrekt in der Datei gespeichert.
•	Die Daten bleiben auch nach dem Schließen des Programms erhalten.

Klassen und Enums im System
POJO-Klasse
Arbeitnehmer
•	Repräsentiert einen Mitarbeiter mit allen persönlichen und beruflichen Daten.
•	Enthält Attribute wie vorname, nachname, geburtsdatum, adresse, abteilung, positionImAbteilung, vertragsart, statuss usw.
•	Bietet mehrere Konstruktoren:
•	Mit allen Parametern.
•	Mit einer List<String> für das Laden aus der Datei.
•	Methoden:
•	toFileString(): Wandelt das Objekt in ein speicherbares Textformat um.
•	fromString(String zeile): Statischer Factory-Method, erstellt ein Objekt aus einer Zeile der Datei.
•	Getter und Setter für alle Attribute.
•	Enthält eine interne Liste personensDaten, die alle Felder als Strings enthält und synchron zu den Attributen bleibt.
Enums
Statuss
•	Definiert den Status eines Mitarbeiters: ANWESEND, ABWESEND, KRANK, URLAUB, FORTBILDUNG, DIENSTREISE, FREISTELLUNG, GEKUENDIGT.
Vertragsart
•	Definiert die Art des Arbeitsvertrags: BEFRISTET, UNBEFRISTET, VOLLZEIT, TEILZEIT, MINIJOB, WERKSTUDENT, AUSZUBILDENDER, PRAKTIKANT, ARBEITSLOS.
Abteilung
•	Definiert die Abteilung des Mitarbeiters: z. B. IT, MARKETING, LOGISTIK, PERSONAL, PRODUKTION usw.
PositionImAbteilung
•	Definiert die Position innerhalb einer Abteilung: z. B. GESCHAEFTSFUEHRER, TEAMLEITER, KOCH, LAGERMITARBEITER usw.
Hauptklasse für die Mitarbeiterverwaltung
Unternehmen
•	Enthält die Map <Integer, Arbeitnehmer> namens arbeitnehmerMap, die alle Mitarbeiterobjekte im Speicher hält.
•	Methode getArbeitnehmerMap(): Ermöglicht das Lesen und Verändern von Mitarbeitern in anderen Klassen.
•	Methode addMitarbeiter(int id, Arbeitnehmer mitarbeiter): Fügt einen neuen Mitarbeiter zur Map hinzu.
Service-Klasse
MitarbeiterService
•	Implementiert die Registrierung neuer Mitarbeiter (neueMitarbeiterRegistrieren()).
•	Verwaltet die Liste aller Mitarbeiter (alleArbeitnehmer).
•	Prüft Duplikate bei Ausweisnummer, Email oder Vorname+Nachname.
Dateiverwaltung
ArbeitnehmerPersistens / Datei
•	Methoden zum Lesen und Schreiben der Datei mitarbeiterliste.txt.
•	lessenDatei() / lessenAllerArbeitnehmer(): Liest alle Zeilen aus der Datei und wandelt sie bei Bedarf in Arbeitnehmer-Objekte um.
•	letzteidExtrahieren(): Optional, extrahiert die letzte ID aus der Datei für neue Mitarbeiter.






Verbesserungspotenzial / Things to Improve
•	Validierung der gelesenen Datei
o	Prüfen der Länge des Arrays teile bevor auf Indizes zugegriffen wird:

So können fehlerhafte oder unvollständige Zeilen erkannt und behandelt werden.
•  Trennung der Verantwortlichkeiten (Separation of Concerns)
•	Momentan übernimmt die Klasse Arbeitnehmer mehrere Aufgaben: speichern, laden, anzeigen.
•	Zukünftig wäre es besser, eine Persistenzschicht (Lesen/Schreiben der Datei) von der Geschäftslogik zu trennen.
•  Tests / Prüfungen
•	Erstellen von Testdaten, um alle Randfälle zu prüfen:
o	Ungültige Daten (z. B. falsches Datum)
o	Leere Datei
o	Unbekannte Enum-Werte
•	So können Fehler frühzeitig erkannt werden, bevor sie in der Produktion auftreten.
<img width="468" height="632" alt="image" src="https://github.com/user-attachments/assets/08aedd17-8704-4444-91b0-7db57a30d37b" />





