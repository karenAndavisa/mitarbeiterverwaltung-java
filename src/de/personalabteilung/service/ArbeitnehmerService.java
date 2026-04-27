package de.personalabteilung.service;

import de.personalabteilung.datei.ArbeitnehmerPersistens;
import de.personalabteilung.mitarbeiter.Arbeitnehmer;

import java.util.List;

/**
 * Jeder Mitarbeiter ist ein Arbeitnehmer-Objekt.
 *  * ArbeitnehmerService verwaltet die Liste aller Mitarbeiter (alleArbeitnehmer).
 *  * Die Klasse ArbeitnehmerPersistens liest und speichert die Daten in einer Datei
 *  * oder zukünftig in einer Datenbank.
 */

public class ArbeitnehmerService {
    /** Liste aller Arbeitnehmer, die vom Service verwaltet wird*/
    private List <Arbeitnehmer> alleArbeitnehmer;
    /** Konstruktor: initialisiert die Liste alleArbeitnehmer mit Daten aus der Persistenz*/
    public ArbeitnehmerService() {
        ArbeitnehmerPersistens arbeitnehmerPersistens = new ArbeitnehmerPersistens();
        alleArbeitnehmer = arbeitnehmerPersistens.lessenAllerArbeitnehmer();
    }

    public boolean pruefendoopelAusweisnummer(Arbeitnehmer arbeitnehmer){
        String neue = arbeitnehmer.getAusweisnummer()==null?"":arbeitnehmer.getAusweisnummer().trim();
        for (Arbeitnehmer arbeitnehmer1 : alleArbeitnehmer) {
            String vorhanden = arbeitnehmer1.getAusweisnummer() == null?"":arbeitnehmer1.getAusweisnummer().trim();
            if (!vorhanden.isEmpty() && vorhanden.equalsIgnoreCase(neue)){
                return false;
            }
        }
        return true;
    }

    public boolean pruefendoopelEmail(Arbeitnehmer arbeitnehmer){
        String neue = arbeitnehmer.getEmailAdresse()==null?"":arbeitnehmer.getEmailAdresse().trim();
        for (Arbeitnehmer arbeitnehmer1 : alleArbeitnehmer) {
            String vorhanden = arbeitnehmer1.getEmailAdresse() == null?"":arbeitnehmer1.getEmailAdresse().trim();
            if (!vorhanden.isEmpty() && vorhanden.equalsIgnoreCase(neue)){
                return false;
            }
        }
        return true;
    }
    public boolean pruefendoopelNameNachname(Arbeitnehmer arbeitnehmer){
        String vornameneue = arbeitnehmer.getVorname()==null?"":arbeitnehmer.getVorname().trim();
        String nachnameneue = arbeitnehmer.getNachname()==null?"":arbeitnehmer.getNachname().trim();
        for (Arbeitnehmer arbeitnehmer1 : alleArbeitnehmer) {
            String vvorhanden = arbeitnehmer1.getVorname() == null?"":arbeitnehmer1.getVorname().trim();
            String nvorhanden = arbeitnehmer1.getNachname() == null?"":arbeitnehmer1.getNachname().trim();
            if (!vvorhanden.isEmpty() && !nvorhanden.isEmpty() && vvorhanden.equalsIgnoreCase(vornameneue) && nvorhanden.equalsIgnoreCase(nachnameneue)){
                return false;
            }
        }
        return true;
    }

    public List<Arbeitnehmer> getAlleArbeitnehmer() {
        return alleArbeitnehmer;
    }

    public void setAlleArbeitnehmer(List<Arbeitnehmer> alleArbeitnehmer) {
        this.alleArbeitnehmer = alleArbeitnehmer;
    }

    /**
     * Prüfen, ob eine E-Mail vorhanden ist (normalisiert)
     * @param email
     * @return
     */

    public boolean existsByEmail(String email) {
        if (email == null) return false;
        String e = email.trim().toLowerCase();
        for (Arbeitnehmer a : alleArbeitnehmer) {
            String existingEmail = a.getEmailAdresse() == null ? "" : a.getEmailAdresse().trim().toLowerCase();
            if (!existingEmail.isEmpty() && existingEmail.equals(e)) return true;
        }
        return false;
    }
    /**
     * Prüfen, ob eine ausweis vorhanden ist (normalisiert)
     * @param
     * @return ausweis
     */

    public boolean existsByAusweis(String ausweis) {
        if (ausweis == null) return false;
        String aStr = ausweis.trim().toLowerCase();
        for (Arbeitnehmer a : alleArbeitnehmer) {
            String existingausweis = a.getAusweisnummer() == null ? "" : a.getAusweisnummer().trim().toLowerCase();
            if (!existingausweis.isEmpty() && existingausweis.equals(aStr)) return true;
        }
        return false;
    }
    /**
     * Prüfen, ob eine vorname/nachname vorhanden ist (normalisiert)
     * @param  vorname,  nachname
     * @return
     */
    public boolean existsByName(String vorname, String nachname) {
        String v = vorname == null ? "" : vorname.trim().toLowerCase();
        String n = nachname == null ? "" : nachname.trim().toLowerCase();
        if (v.isEmpty() || n.isEmpty()) return false;
        for (Arbeitnehmer a : alleArbeitnehmer) {
            String av = a.getVorname() == null ? "" : a.getVorname().trim().toLowerCase();
            String existinexistinNameVorname = a.getNachname() == null ? "" : a.getNachname().trim().toLowerCase();
            if (!av.isEmpty() && !existinexistinNameVorname.isEmpty() && av.equals(v) && existinexistinNameVorname.equals(n)) return true;
        }
        return false;
    }

}
