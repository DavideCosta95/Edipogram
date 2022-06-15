package asw.edipogram.enigmiseguiti.rest;

import asw.edipogram.enigmiseguiti.domain.EnigmaSeguito;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/* Enigma, in formato breve (senza soluzione). */ 
@Data
@NoArgsConstructor
public class GetEnigmaResponse {

	@NonNull
	private Long id; 

	@NonNull
	private String autore;

	@NonNull
	private String tipo;

	@NonNull
	private String tipoSpecifico;

	@NonNull
	private String titolo; 

	@NonNull
	private String[] testo;

	public GetEnigmaResponse(EnigmaSeguito enigmaSeguito) {
		this.id = enigmaSeguito.getIdEnigma();
		this.autore = enigmaSeguito.getAutoreEnigma();
		this.tipo = enigmaSeguito.getTipoEnigma();
		this.tipoSpecifico = enigmaSeguito.getTipoSpecificoEnigma();
		this.titolo = enigmaSeguito.getTitoloEnigma();
		this.testo = enigmaSeguito.getTestoEnigma();
	}
}
