package asw.edipogram.enigmi.rest;

import asw.edipogram.enigmi.domain.Enigma; 

import lombok.*; 

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

	public GetEnigmaResponse(Enigma r) {
		this.id = r.getId(); 
		this.autore = r.getAutore(); 
		this.tipo = r.getTipo(); 
		this.tipoSpecifico = r.getTipoSpecifico(); 
		this.titolo = r.getTitolo(); 
		this.testo = r.getTesto(); 
	}
}
