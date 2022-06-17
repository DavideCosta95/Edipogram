package asw.edipogram.enigmi.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnigmaCreatedEvent {
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
}
