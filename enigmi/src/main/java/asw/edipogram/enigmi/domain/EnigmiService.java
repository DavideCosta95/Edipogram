package asw.edipogram.enigmi.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class EnigmiService {

	private final EnigmiRepository enigmiRepository;

	@Autowired
	private EnigmiService(EnigmiRepository enigmiRepository) {
		this.enigmiRepository = enigmiRepository;
	}

 	public Enigma createEnigma(String autore, String tipo, String tipoSpecifico, String titolo, String[] testo, String[] soluzione) {
		Enigma enigma = new Enigma(autore, tipo, tipoSpecifico, titolo, testo, soluzione); 
		return enigmiRepository.save(enigma);
	}

 	public Enigma getEnigma(Long id) {
		return enigmiRepository.findById(id).orElse(null);
	}

	public Collection<Enigma> getEnigmi() {
		return enigmiRepository.findAll();
	}

	public Collection<Enigma> getEnigmiByAutore(String autore) {
		return enigmiRepository.findByAutore(autore);
	}

	public Collection<Enigma> getEnigmiByAutori(Collection<String> autori) {
		return enigmiRepository.findByAutoreIn(autori);
	}

	public Collection<Enigma> getEnigmiByTipo(String tipo) {
		return enigmiRepository.findByTipo(tipo);
	}

	public Collection<Enigma> getEnigmiByTipi(Collection<String> tipi) {
		return enigmiRepository.findByTipoIn(tipi);
	}
}
