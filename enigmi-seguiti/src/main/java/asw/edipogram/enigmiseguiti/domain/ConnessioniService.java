package asw.edipogram.enigmiseguiti.domain;

import java.util.*; 

public interface ConnessioniService {

	Collection<Connessione> getConnessioniByUtente(String utente);
	
}
