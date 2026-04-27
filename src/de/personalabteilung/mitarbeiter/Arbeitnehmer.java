package de.personalabteilung.mitarbeiter;





import de.personalabteilung.arbeitsdatenEnum.Abteilung;
import de.personalabteilung.arbeitsdatenEnum.PositionImAbteilung;
import de.personalabteilung.arbeitsdatenEnum.Statuss;
import de.personalabteilung.arbeitsdatenEnum.Vertragsart;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
//la clase Arbeitnehmer es una clase POJO, contendra informacion de los trabajadores tanto personales como de
// *     su situacion actual, en relacion con la empresa.
// *     idPerson, un valor unico
// *     List <String> einstellungsdaten, String con los datos personales
// *     Beschaeftigungsstatus clase enum, beschaeftigungsstatus, aqui estan los tipo de contratacion en la empresa
// *     Status clase enum, aktuellerStatus, estado actual de la persona en relacion con la empresa
/**
 * <pre>
 *     Die Klasse Arbeitnehmer ist eine POJO-Klasse und enthält Informationen über die Mitarbeiter,
 *     sowohl persönliche Daten als auch ihren aktuellen Status in Bezug auf das Unternehmen.
 *     idPerson: ein eindeutiger Wert
 *     List<String> einstellungsdaten: Strings mit persönlichen Daten
 *     Beschaeftigungsstatus (Enum-Klasse): beschaeftigungsstatus, hier sind die verschiedenen
 *     Vertragsarten im Unternehmen enthalten
 *     Status (Enum-Klasse): aktuellerStatus, der aktuelle Status der Person in Bezug auf das Unternehmen
 *
 * </pre>
 */
public class Arbeitnehmer  {
    /** Liste mit allen persoenlichen Daten des Mitarbeiters*/
    private List<String> personensDaten;
   /** Eindeutige Mitarbeiter-ID*/
    private int idPerson;
    private String vorname;
    private String nachname;
    private String emailAdresse;
    private String ausweisnummer;
    private String telefonnummer;
    private LocalDate geburtsdatum;
    private String adresse;
    private String geburtsort;
    private String staatsangehoerigkeit;
    private String familienstand;
    private String kinderAnzahl;
    /** Beshaeftigungsinformationen*/
    private Vertragsart vertragsart;
    private Abteilung abteilung;
    private PositionImAbteilung positionImAbteilung;
    private Statuss statuss;
    private LocalDate eintrittsdatum;
    private DateTimeFormatter formatter;

    //constructor principal con todos los datos
    //____________________________-
    /** vollstaendiger konstruktor mit allen Atributen*/

    public Arbeitnehmer( int idPerson, String vorname, String nachname, String emailAdresse, String ausweisnummer, String telefonnummer, LocalDate geburtsdatum, String adresse, String geburtsort, String staatsangehoerigkeit, String familienstand, String kinderAnzahl, Vertragsart vertragsart, Abteilung abteilung, PositionImAbteilung positionImAbteilung, Statuss statuss) {

        this.idPerson = idPerson;
        this.vorname = vorname;
        this.nachname = nachname;
        this.emailAdresse = emailAdresse;
        this.ausweisnummer = ausweisnummer;
        this.telefonnummer = telefonnummer;
        this.geburtsdatum = geburtsdatum;
        this.adresse = adresse;
        this.geburtsort = geburtsort;
        this.staatsangehoerigkeit = staatsangehoerigkeit;
        this.familienstand = familienstand;
        this.kinderAnzahl = kinderAnzahl;
        this.vertragsart = vertragsart;
        this.abteilung = abteilung;
        this.positionImAbteilung = positionImAbteilung;
        this.statuss = statuss;
        this.eintrittsdatum = LocalDate.now();
    }

    /**
     * Konstruktor nur mit Basisdaten (Liste personensDaten wird automatisch erstellt)
     */
    public Arbeitnehmer(int idPerson, String vorname, String nachname, String emailAdresse, String ausweisnummer, String telefonnummer, LocalDate geburtsdatum, String adresse, String geburtsort, String staatsangehoerigkeit, String familienstand, String kinderAnzahl) {
        this.idPerson = idPerson;
        this.vorname = vorname;
        this.nachname = nachname;
        this.emailAdresse = emailAdresse;
        this.ausweisnummer = ausweisnummer;
        this.telefonnummer = telefonnummer;
        this.geburtsdatum = geburtsdatum;
        this.adresse = adresse;
        this.geburtsort = geburtsort;
        this.staatsangehoerigkeit = staatsangehoerigkeit;
        this.familienstand = familienstand;
        this.kinderAnzahl = kinderAnzahl;

        this.vertragsart = vertragsart;
        this.abteilung = abteilung;
        this.positionImAbteilung = positionImAbteilung;
        this.statuss = statuss;
        this.eintrittsdatum = eintrittsdatum;
        //Construimos automaticamente la lista personensDaten
        /**  Liste automatisch erstellen und alle Daten hinzufügen*/
        this.personensDaten = new ArrayList<>();

        personensDaten.add(vorname);
        personensDaten.add(nachname);
        personensDaten.add(emailAdresse);
        personensDaten.add(ausweisnummer);
        personensDaten.add(telefonnummer);
        personensDaten.add(geburtsdatum.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        personensDaten.add(adresse);
        personensDaten.add(geburtsort);
        personensDaten.add(staatsangehoerigkeit);
        personensDaten.add(familienstand);
        personensDaten.add(kinderAnzahl);
        /** Platzhalter für Enums (noch nicht gesetzt)*/
        personensDaten.add(vertragsart.name());
        personensDaten.add(abteilung.name());
        personensDaten.add(positionImAbteilung.name());
        personensDaten.add(statuss.name());
        personensDaten.add(eintrittsdatum.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

    }

    // constructor desde lista de String (al leer el archivo)
    public Arbeitnehmer(int idPerson, List<String> personensDaten) {
        this.idPerson = idPerson;

        //campos de la lista
        this.vorname = personensDaten.get(0);
        this.nachname = personensDaten.get(1);
        this.emailAdresse = personensDaten.get(2);
        this.ausweisnummer = personensDaten.get(3);
        this.telefonnummer = personensDaten.get(4);
        this.geburtsdatum = LocalDate.parse(personensDaten.get(5), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        this.adresse = personensDaten.get(6);
        this.geburtsort = personensDaten.get(7);
        this.staatsangehoerigkeit = personensDaten.get(8);
        this.familienstand = personensDaten.get(9);
        this.kinderAnzahl = personensDaten.get(10);

        // convertir los String a enums
        this.vertragsart = Vertragsart.valueOf(personensDaten.get(11));
        this.abteilung =  Abteilung.valueOf(personensDaten.get(12));
        this.positionImAbteilung = PositionImAbteilung.valueOf(personensDaten.get(13));
        this.statuss = Statuss.valueOf(personensDaten.get(14));

        this.eintrittsdatum = LocalDate.parse(personensDaten.get(15),DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        // reconstruir la lista personanDaten completa si la usan
        this.personensDaten = new ArrayList<>(personensDaten);
    }
    // constructor con parametros extra (enums + eintrittsdatum
    public Arbeitnehmer(int idPerson, List<String> personensDaten, Vertragsart vertragsart, Abteilung abteilung, PositionImAbteilung positionImAbteilung, Statuss statuss, LocalDate eintrittsdatum) {
        this.idPerson = idPerson;

        //campos de la lista
        this.vorname = personensDaten.get(0);
        this.nachname = personensDaten.get(1);
        this.emailAdresse = personensDaten.get(2);
        this.ausweisnummer = personensDaten.get(3);
        this.telefonnummer = personensDaten.get(4);
        this.geburtsdatum = LocalDate.parse(personensDaten.get(5), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        this.adresse = personensDaten.get(6);
        this.geburtsort = personensDaten.get(7);
        this.staatsangehoerigkeit = personensDaten.get(8);
        this.familienstand = personensDaten.get(9);
        this.kinderAnzahl = personensDaten.get(10);

        // ya vienen como parametros
        this.vertragsart = vertragsart;
        this.abteilung =  abteilung;
        this.positionImAbteilung = positionImAbteilung;
        this.statuss = statuss;

        this.eintrittsdatum = eintrittsdatum;

        // reconstruir la lista personanDaten completa si la usan
        this.personensDaten = new ArrayList<>(personensDaten);
    }

//-------------------------------------------------------------------
//Metodo para guardar el archivo , vamos a cambiar el formato al guardar en el archivo toFileString, para poder usar mejor los datos
    /**
     * Methode zum Speichern des Mitarbeiters in einer Textdatei
     * Wandelt das Objekt Arbeitnehmer in eine formatierte Textzeile um,
     * die zum Speichern in einer Datei geeignet ist.
     *
     *- Verwendet das Trennzeichen ";" zwischen den Attributen.
     *- Formatiert Datumsangaben im Muster dd.MM.yyyy.
     *- Reihenfolge der Felder entspricht der Reihenfolge,
     * die vom statischen fromString()-Parser erwartet wird.
     *  @return String-Repräsentation des Mitarbeiters für die Datei.
     */
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

    //____________________________________________________________

    /**
     * Statischer Parser: erzeugt Arbeitnehmer aus einer Datei-Zeile
     * Erzeugt ein Arbeitnehmer-Objekt aus einer gespeicherten Textzeile.
     * - Erwartet ein durch ";" getrenntes Format, das von toFileString() geliefert wurde.
     *  - Liest zuerst die Grunddaten in eine Liste (personensDaten).
     *  - Konvertiert anschließend die Werte für Enums und Datum.
     * @param zeile Eine einzelne Zeile aus der Mitarbeiter-Datei.
     * @return Ein neues Arbeitnehmer-Objekt mit allen Attributen.
     */
    public static Arbeitnehmer fromString(String zeile){
        String [] teile = zeile.split(";");
       int idperson = Integer.parseInt(teile[0]);
       //crea la lista con los demas campos
        /** Erst die persönlichen Basisdaten in die Liste übernehmen*/
        List<String> personesDaten = new ArrayList<>();
        for (int i = 1; i <= 11 ; i++) {
            personesDaten.add(teile[i]);
        }
        // leer enums y String datos por separado
        /** Enums und weitere Felder separat lesen*/
        Vertragsart vertragsart = Vertragsart.valueOf(teile[12]);
        Abteilung abteilung = Abteilung.valueOf(teile[13]);
        PositionImAbteilung positionImAbteilung = PositionImAbteilung.valueOf(teile[14]);
        Statuss statuss = Statuss.valueOf(teile[15]);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate eintrittsdatum = LocalDate.parse(teile[16], formatter );
        // crear objeto usando nuevpo constructor
        /** Neues Objekt mit den extrahierten Werten erstellen*/
        return new Arbeitnehmer(idperson, personesDaten, vertragsart, abteilung, positionImAbteilung, statuss, eintrittsdatum);
    }



    //-----------------getter------------------

//    /**
//     * devuelve todos los datos personales del empleado como lista de string.
//     *
//     * reconstruye la lista a partir de los campos (vorname ...etc) Asegura que los cambios en los atributosse reflejen automaticamente en la lista
//     * @return
//     */
    public List<String > getPersonensDaten(){
        List<String> daten = new ArrayList<>();
        daten.add(vorname);
        daten.add(nachname);
        daten.add(emailAdresse);
        daten.add(ausweisnummer);
        daten.add(telefonnummer);
        daten.add(geburtsdatum.toString());
        daten.add(adresse);
        daten.add(geburtsort);
        daten.add(staatsangehoerigkeit);
        daten.add(familienstand);
        daten.add(kinderAnzahl);

        return daten;
    }
    public int getIdPerson() {
        return idPerson;
    }

    public Vertragsart getVertragsart() {
        return vertragsart;
    }

    public Statuss getStatuss() {
        return statuss;
    }

    public Abteilung getAbteilung() {
        return abteilung;
    }

    public PositionImAbteilung getPositionImAbteilung() {
        return positionImAbteilung;
    }

    public LocalDate getEintrittsdatum() {
        return eintrittsdatum;
    }


    //-----------------------------------------------------

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getEmailAdresse() {
        return emailAdresse;
    }

    public String getAusweisnummer() {
        return ausweisnummer;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getGeburtsort() {
        return geburtsort;
    }

    public String getStaatsangehoerigkeit() {
        return staatsangehoerigkeit;
    }

    public String getFamilienstand() {
        return familienstand;
    }

    public String getKinderAnzahl() {
        return kinderAnzahl;
    }

    //---------------setter-------------------


    public void setVertragsart(Vertragsart vertragsart) {
        this.vertragsart = vertragsart;
    }

    public void setStatuss(Statuss statuss) {
        this.statuss = statuss;
    }

    public void setAbteilung(Abteilung abteilung) {
        this.abteilung = abteilung;
    }

    public void setPositionImAbteilung(PositionImAbteilung positionImAbteilung) {
        this.positionImAbteilung = positionImAbteilung;
    }

    public void setEintrittsdatum(LocalDate eintrittsdatum) {
        this.eintrittsdatum = eintrittsdatum;
    }
}
