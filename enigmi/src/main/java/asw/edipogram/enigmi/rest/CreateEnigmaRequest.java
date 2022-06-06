package asw.edipogram.enigmi.rest;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEnigmaRequest {

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

	@NonNull
	private String[] soluzione;

}

