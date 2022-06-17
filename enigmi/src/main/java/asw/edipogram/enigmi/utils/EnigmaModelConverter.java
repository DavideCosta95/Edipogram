package asw.edipogram.enigmi.utils;

import asw.edipogram.enigmi.domain.Enigma;
import asw.edipogram.enigmi.messaging.EnigmaCreatedEvent;
import asw.edipogram.enigmi.rest.CreateEnigmaRequest;
import org.springframework.stereotype.Component;

@Component
public class EnigmaModelConverter {

	private EnigmaModelConverter() {}

	public Enigma toEnigma(CreateEnigmaRequest createEnigmaRequest) {
		String autore = createEnigmaRequest.getAutore();
		String tipo = createEnigmaRequest.getTipo();
		String tipoSpecifico = createEnigmaRequest.getTipoSpecifico();
		String titolo = createEnigmaRequest.getTitolo();
		String[] testo = createEnigmaRequest.getTesto();
		String[] soluzione = createEnigmaRequest.getSoluzione();
		return new Enigma(autore, tipo, tipoSpecifico, titolo, testo, soluzione);
	}

	public EnigmaCreatedEvent toEnigmaCreatedEvent(Enigma enigma) {
		String autore = enigma.getAutore();
		String tipo = enigma.getTipo();
		String tipoSpecifico = enigma.getTipoSpecifico();
		String titolo = enigma.getTitolo();
		String[] testo = enigma.getTesto();
		return new EnigmaCreatedEvent(autore, tipo, tipoSpecifico, titolo, testo);
	}
}
