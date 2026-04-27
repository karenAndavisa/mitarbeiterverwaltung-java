package de.personalabteilung.service;

import de.personalabteilung.arbeitgeber.Unternehmen;
import de.personalabteilung.arbeitsdatenEnum.Abteilung;
import de.personalabteilung.arbeitsdatenEnum.PositionImAbteilung;
import de.personalabteilung.arbeitsdatenEnum.Statuss;
import de.personalabteilung.arbeitsdatenEnum.Vertragsart;
import de.personalabteilung.datei.Datei;
import de.personalabteilung.mitarbeiter.Arbeitnehmer;
import de.personalabteilung.service.ArbeitnehmerService;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MitarbeiterService {

    private Unternehmen unternehmen;
    private int idZaehler =1;
    private List <Arbeitnehmer> diearbeitnehmer ;


    public MitarbeiterService(Unternehmen unternehmen) {
        this.unternehmen = unternehmen;
        ladenMitarbeiterAusMitarbeiterliste();

    }
    public int neueId(){

        return idZaehler++;
    }

//    /**
//     * cargar el sistema desde mitarbeiterliste.txt
//     * pasos:leerlo
//     * hacer un objeto de cada linea del archivo
//     * y lo uni con otro metodo abletzteid para que no lea dos veces
//     */

    /**
     * <pre>
     * Lädt alle Mitarbeiter aus der Datei "mitarbeiterliste.txt" in das System.
     * Dieser Methodenaufruf liest jede Zeile der Datei, erstellt ein Arbeitnehmer-Objekt
     * über die statische Methode fromString() und speichert es im Arbeitsspeicher
     * der Klasse Unternehmen (arbeitnehmerMap).
     * Außerdem wird die höchste vorhandene ID ermittelt, um sicherzustellen, dass
     * neue Mitarbeiter eindeutige IDs erhalten (idZaehler wird entsprechend gesetzt).
     * Verwendung:
     *  - Aufruf beim Initialisieren des MitarbeiterService, um das System mit gespeicherten
     *    Mitarbeitern zu füllen.
     *  - Stellt sicher, dass alle Mitarbeiterobjekte vollständig im Speicher verfügbar sind.
     *  Verwendung:
     *  - Aufruf beim Initialisieren des MitarbeiterService, um das System mit gespeicherten
     *    Mitarbeitern zu füllen.
     *  - Stellt sicher, dass alle Mitarbeiterobjekte vollständig im Speicher verfügbar sind.
     *</pre>
     */
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

    /**
     * <pre>
     *     Methode zur Registrierung neuer Mitarbeiter im System.
     *   Ermöglicht dem Administrator die Eingabe persönlicher und beruflicher Daten eines oder mehrerer Mitarbeiter über die Konsole.
     *   Es wird überprüft, dass Pflichtfelder nicht leer sind und dass keine Duplikate vorhanden sind:
     *     - Vorname + Nachname
     *     - E-Mail
     *     - Ausweisnummer
     *
     *   Für jeden Mitarbeiter:
     *     1. Eingabe der persönlichen Daten (Vorname, Nachname, E-Mail, Ausweisnummer, Telefonnummer, Geburtsdatum, Adresse, Geburtsort, Staatsangehörigkeit, Familienstand, Kinderanzahl)
     *     2. Eingabe der Arbeitsdaten (Vertragsart, Abteilung, Position, Status)
     *     3. Automatische Generierung einer eindeutigen ID über idZaehler
     *     4. Erstellung eines Arbeitnehmer-Objekts mit allen Daten
     *     5. Speicherung des Objekts im Speicher (arbeitnehmerMap des Unternehmens)
     *     6. Speicherung des Objekts in der Datei mitarbeiterliste.txt über die Methode speichernMitarbeiter
     *
     *   Ermöglicht die Registrierung mehrerer Mitarbeiter in einem Durchgang, bis der Benutzer die Eingabe beendet.
     * </pre>
     * @throws IOException
     */
    public void neueMitarbeiterRegistrieren () throws IOException {

        /** Registramos por consola*/
        Scanner scanner = new Scanner(System.in);


        //inicializamos para entrar al while

        String antwort= "ja";
        while ("ja".equalsIgnoreCase(antwort)){
            System.out.println("\n Neuen Mitarbeiter registrierung:\n" );

            // se comprueba que el nombre no se quede vacio
            /** Es wird ueprueft, dass das Namensfeld nicht leer bleibt*/
            String vorname = "";
            while (vorname.isEmpty()){
                System.out.println(" Vorname eingeben:");
                vorname = scanner.nextLine().trim().toLowerCase();
                if (vorname.isEmpty()){
                    System.out.println("Dieses Feld darf nicht leer sein");
                }
            }
            /** Es wird ueprueft, dass das Namensfeld nicht leer bleibt*/
            String nachname = "";
            while (nachname.isEmpty()){
                System.out.println("Nachnamen eingeben:");
                nachname = scanner.nextLine().trim().toLowerCase();
                if (nachname.isEmpty()){
                    System.out.println("Dieses Feld darf nicht leer sein");
                }
            }
            /** Es wird ueprueft, dass (vorname, nachname) sind noch nicht registriert */
            if (nameNachnameExistiert(vorname, nachname)){
                System.out.println("Die Person ist bereits registriert");
                antwort = frageJaNein(scanner," Moechten Sie einen weiteren Mitarbeiter anmelden? (ja/nein)");
                if (!"ja".equalsIgnoreCase(antwort))break;
                else continue;
            }
            /** Es wird ueprueft, dass das emailAdresse nicht leer bleibt*/
            String emailAdresse = "";
            while (emailAdresse.isEmpty()){
                System.out.println("E-Mail Adresee eingeben:");
                emailAdresse = scanner.nextLine().trim().toLowerCase();
                if (emailAdresse.isEmpty()){
                    System.out.println("Dieses Feld darf nicht leer sein");
                }
            }

            /** Es wird ueprueft, dass die emailAdresse ist noch nicht registriert */
            if (emailExistiert(emailAdresse)){
                System.out.println("Die E-Mail ist bereits registriert");
                antwort = frageJaNein(scanner, " Moechten Sie einen weiteren Mitarbeiter anmelden? (ja/nein)");
                if (!"ja".equalsIgnoreCase(antwort))break;
                else continue;
            }
            /** Es wird ueprueft, dass das emailAdresse nicht leer bleibt*/
            String ausweisnummer = "";
            while (ausweisnummer.isEmpty()){
                System.out.println("Personalausweisnummer eingeben  :");
                ausweisnummer = scanner.nextLine().trim().toLowerCase();
                if ((ausweisnummer.isEmpty())){
                    System.out.println("Dieses Feld darf nicht leer sein");
                }
            }

            /** Es wird ueprueft, dass die emailAdresse ist noch nicht registriert */
            if (ausweisnummerExistiert(ausweisnummer)){
                System.out.println("Die Personalausweisnummer ist bereits registriert");
                antwort = frageJaNein (scanner, " Moechten Sie einen weiteren Mitarbeiter anmelden? (ja/nein)");
                if (!"ja".equalsIgnoreCase(antwort))break;
                else continue;
            }

            System.out.println(" Telefonnummer eingeben:");
            String telefonnummer = scanner.nextLine().trim();
            /**
             * Zeigt eine Fehlermeldung an und wiederholt die Schleife, bis das Datum korrekt eingegeben wird, wodurch ein Absturz des Systems durch eine falsche Datumseingabe vermieden wird.
             */
            // muestra mensaje de rror y y repite el bucle hasta que la fecha este correcta, evitando caida de l sistema por ingreso de datos en el formato incorrecto
            LocalDate geburtsdatum = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            while (geburtsdatum ==null){
                System.out.println(" Geburtsdatum eingeben Format: dd.MM.yyyy");
                String geburtsdatumString = scanner.nextLine().trim();
                try{
                    geburtsdatum= LocalDate.parse(geburtsdatumString, formatter);
                } catch (java.time.format.DateTimeParseException e){
                    System.out.println("Fehler: Bitte geben Sie das Datum im Format dd.MM.yyyy ein ");
                }

            }

            System.out.println(" Adresse eingeben:");
            String adresse = scanner.nextLine().trim().toLowerCase();

            System.out.println(" Geburtsort eingeben:");
            String geburtsort = scanner.nextLine().trim().toLowerCase();

            System.out.println(" Staatsangehörigkeit eingeben:");
            String staatsangehoerigkeit = scanner.nextLine().trim().toLowerCase();

            System.out.println(" Familienstand eingeben:");
            String familienstand = scanner.nextLine().trim().toLowerCase();


            System.out.println(" Anzahl der Kinder eingeben:");
            String kinderAnzahl = scanner.nextLine().trim().toLowerCase();

            System.out.println("Eintrittsdatum eingeben (Format:)");
            /**
             * Um Systemabstuerze zu vermeiden: Es wird eine while Schleife verbendet, die null prueft, mit try,
             * bis ein korrekter Wert eingegeben wird, andernfalls wird weiter nachgefragt
             */
            //para evitar caidas del sistema: se usa while que es null try hasta que se ingrese un valor corecto , si no sigue preguntando
            Vertragsart vertragsart = null;
            while (vertragsart == null){
                System.out.println("Waehlen Sie die Vertragsart"+ Arrays.toString(Vertragsart.values()));
                String input = scanner.nextLine().trim().toUpperCase();
                try{
                    vertragsart= Vertragsart.valueOf(input);
                }catch (IllegalArgumentException e){
                    System.out.println("Ungueltige Auswhahl, bitte erneut eingeben .");
                }
            }
            Abteilung abteilung = null;
            while( abteilung ==null){
                System.out.println("Waehlen Sie die Abteilung"+Arrays.toString(Abteilung.values()));
                String input = scanner.nextLine().trim().toUpperCase();
                try{
                    abteilung = Abteilung.valueOf(input);
                }catch (IllegalArgumentException e){
                    System.out.println("Ungueltige Abteilung, bitte erneut eingeben .");
                }
            }
            PositionImAbteilung positionImAbteilung = null;
            while (positionImAbteilung == null){
                System.out.println("Waehlen Sie die Position im Position im Abteilung" + Arrays.toString(PositionImAbteilung.values()));
                String input = scanner.nextLine().trim().toUpperCase();
                try {
                    positionImAbteilung = PositionImAbteilung.valueOf(input);
                }catch (IllegalArgumentException e){
                    System.out.println("Ungueltige Position im Abteilung, bitte erneut eingeben .");
                }
            }

            Statuss statuss = null;
            while (statuss == null){
                System.out.println("waehlen Sie den aktuelle Status des Mitarbeites im unternehmen"+ Arrays.toString(Statuss.values()));
                String input = scanner.nextLine().trim().toUpperCase();
                try {
                    statuss = Statuss.valueOf(input);
                }catch (IllegalArgumentException e) {
                    System.out.println("Ungueltige Stauss, bitte erneut eingeben .");
                }
            }


            // aqui se genera un id único para cada nuevo empleado usando el contador idZaehler
            /**
             * Dabei wird für jeden neuen Mitarbeiter mithilfe des idZaehler-Zählers eine eindeutige ID generiert.
             */
            int neueid= neueId();
            System.out.println(" zugewiesene ID :" + neueid);

            //crear el objeto Arbeitnehmer usando el constructor principal
            /**
             * Erstellen Sie das Arbeitnehmerobjekt mit dem Hauptkonstruktor
             */
            Arbeitnehmer neueArbeitnehmer = new Arbeitnehmer(neueid,vorname, nachname, emailAdresse, ausweisnummer, telefonnummer, geburtsdatum, adresse, geburtsort, staatsangehoerigkeit, familienstand, kinderAnzahl, vertragsart, abteilung, positionImAbteilung, statuss);


            // cada vez que se agrega empleado se almacena en memoria (la empresa) y se puede acceder a ella
            /**
             * Im Speicher speichern
             */
            unternehmen.getArbeitnehmerMap().put(neueid, neueArbeitnehmer);

            //llamo al metodo guardar para guardar el nuevo empleado en archivo
            /**
             * in Datei speichern
             */
            speichernMitarbeiter(neueArbeitnehmer);

            //preguntar si desea ingresar otro empleado
            antwort = frageJaNein(scanner, " Moechten Sie einen weiteren Mitarbeiter anmelden? (ja/nein)" );
//            // empezar el registro datos laborales
//            System.out.println(" Anfang der Arbeitsdaten für Mitarbeiter-ID");
//
//            System.out.println(" Vorname eingeben:");
//            String  = scaner.nextLine().trim().toLowerCase();


            //preguntar si desea agregar nuevo empleado

        }
    }
    // metodo para validar ja oder nein
    private String frageJaNein(Scanner scanner, String textnachricht){
        String input;
        while (true){
            System.out.println(textnachricht);
            input = scanner.nextLine().trim().toLowerCase();
            if ("ja".equals(input)||"nein".equals(input)) return input;
            System.out.println("Bitte 'ja' oder ' nein ' eingeben");
        }

    }

//    /** comprueba que no haya otra persona con el mismo nombre y apellido
//     * unternehmen.getArbeitnehmerMap().values() obtengo todos los empleados registrados en memoria
//     *  .stream().anyMatch arecorre cada empleado y verifica si cumplen öla condicion
//     *  a->a. accede a los datos guardados en Einstellungsdaten
//     *  equalsIgnoreCase para que de igual si escribe con mayusculas o minusculas
//     *  hecho con un map de Unternehmer Map<Integer, Arbeitnehmer> arbeitnehmerMap (toda la informacion de empleados)
//     * */

    /**
     * unternehmen.getArbeitnehmerMap().values() ruft alle Mitarbeiter im Speicher ab.
     * .stream().anyMatch durchläuft jeden Mitarbeiter und überpruft die Bedingung.
     * equalsIgnoreCase vergleicht unabhängig von Gross -und Kleinschreibung.
     * Dies erfolgt auf der Map<Integer, Arbeitnehmer> des Unternehmens
     * @param vorname
     * @param nachnamen
     * @return true wenn gib es ein Mitarbeiter mit die gleiche vorname-nachname oder false
     */

    private boolean nameNachnameExistiert(String vorname, String nachnamen){

        return unternehmen.getArbeitnehmerMap().values().stream().anyMatch(a->a.getVorname()
                        .equalsIgnoreCase(vorname) && a.getNachname().equalsIgnoreCase(nachnamen));
    }

    private boolean emailExistiert (String emailAdresse){
        return unternehmen.getArbeitnehmerMap().values().stream().anyMatch(a->a.getEmailAdresse()
                .equalsIgnoreCase(emailAdresse));
    }

    private boolean ausweisnummerExistiert(String ausweisnummer) {
        return unternehmen.getArbeitnehmerMap().values().stream().anyMatch(a -> a.getAusweisnummer()
                .equalsIgnoreCase(ausweisnummer));
    }
    // metodo que utilizaran todos los enum 'ref: https://stackoverflow.com/questions/72060257/java-generics-with-enum-return-type?utm_source=chatgpt.com '
    /**
     * 'ref: https://stackoverflow.com/questions/72060257/java-generics-with-enum-return-type?utm_source=chatgpt.com '

     * <T extends Enum<T>> T kann jeder beliebige Enum-Typ sein
     * Class<T> enumKlasse: die Enum-Klasse, die angezeigt werden soll
     * Eine einzige Methode funktioniert für alle Enums
     * getEnumConstants(): Methode der Klasse java.lang.Class, die ein Array vom Typ T[] zurückgibt
     */

    private <T extends Enum <T>> T waehleEnumOption (Scanner scanner , Class<T> enumKlasse, String nachricht){
        T[] valuesEnum = enumKlasse.getEnumConstants();
        System.out.println(nachricht);
        for (int i = 0; i < valuesEnum.length; i++) {
            System.out.println((i+1)+"."+valuesEnum[i]);
        }
        while (true){
            System.out.println("Nummer eingeben:");
            String eingabe = scanner.nextLine().trim();
            try{
                int wahl = Integer.parseInt(eingabe);
                if (wahl >=0 && wahl < valuesEnum.length){
                    return valuesEnum[wahl];
                }
            }catch (NumberFormatException ignored){}
            System.out.println("Bitte eine gültige Zahl eingeben");
        }
    }

//    /**
//     * Metodo que guarda los datos ingresados por consola en un documento mitarbeiterliste.txt
//     * el nuevo empleado ingresado creara un objeto Arbeitnehmer, y escribe el eempleado
//     * en un archivo mitarbeiterliste.txt
//     * @param neueArbeitnehmer es un objeto, es un nuevo empleado
//     */

    /**
     * Speichert die Daten eines Mitarbeiters in der Datei "Mitarbeiteliste.txt.
     * Dabei wird das Arbeitnehmer-Object in einen String ungewandelt (toFileString) und in die Datei geschrieben.
     * Dies stellt sicher, dass die Daten auch nach dem Schliessen des Programms erhalten
     * @param neueArbeitnehmer Das zu speichernde Arbeitnehmer-Objekt.
     */
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


}

