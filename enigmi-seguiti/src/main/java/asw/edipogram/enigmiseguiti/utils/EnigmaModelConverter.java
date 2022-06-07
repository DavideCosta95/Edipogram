package asw.edipogram.enigmiseguiti.utils;

import asw.edipogram.enigmiseguiti.domain.Enigma;
import asw.edipogram.enigmiseguiti.messaging.EnigmaCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class EnigmaModelConverter {

	private EnigmaModelConverter() {}

	public EnigmaCreatedEvent toEnigmaCreatedEvent(Enigma enigma) {
		String autore = enigma.getAutore();
		String tipo = enigma.getTipo();
		String tipoSpecifico = enigma.getTipoSpecifico();
		String titolo = enigma.getTitolo();
		String[] testo = enigma.getTesto();
		String[] soluzione = enigma.getSoluzione();
		return new EnigmaCreatedEvent(autore, tipo, tipoSpecifico, titolo, testo, soluzione);
	}

	public Enigma toEnigma(EnigmaCreatedEvent enigmaCreatedEvent) {
		String autore = enigmaCreatedEvent.getAutore();
		String tipo = enigmaCreatedEvent.getTipo();
		String tipoSpecifico = enigmaCreatedEvent.getTipoSpecifico();
		String titolo = enigmaCreatedEvent.getTitolo();
		String[] testo = enigmaCreatedEvent.getTesto();
		String[] soluzione = enigmaCreatedEvent.getSoluzione();
		return new Enigma(autore, tipo, tipoSpecifico, titolo, testo, soluzione);
	}
}
