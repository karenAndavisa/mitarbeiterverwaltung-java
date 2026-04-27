package de.personalabteilung.main;

import de.personalabteilung.arbeitgeber.Unternehmen;
import de.personalabteilung.service.MitarbeiterService;

import java.io.IOException;

/**
 * <pr>
 *     hacer las consultas de trabajador
 * </pr>
 */

/**private int idPerson;
private List<String> einstellungsdaten;
private Beschaeftigungsstatus beschaeftigungsstatus;
private Statuss statuss;
private Map<LocalDate, Statuss> aktuellesStatusDatum;
private Map<Integer, String> arbeitnehmerdaten;
private Random neuerMitarbeiter;
 */
public class Main {
    public static void main(String[] args) throws IOException {

        /** Ein neues Unternehmen erstellen mit dem Namen "Mi empresa"*/
        Unternehmen unternehmen = new Unternehmen("Mi empresa");
        /** Ein MitarbeiterService-Objekt erzeugen, das das Unternehmen verwaltet*/
        MitarbeiterService service = new MitarbeiterService(unternehmen);
        /** Methode aufrufen, um einen neuen Mitarbeiter über die Konsole zu registrieren*/
        service.neueMitarbeiterRegistrieren();




    }
}
