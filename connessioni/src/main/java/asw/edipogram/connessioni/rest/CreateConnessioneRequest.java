package asw.edipogram.connessioni.rest;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateConnessioneRequest {

	@NonNull
	private String utente;

	@NonNull
	private String tipo;

}
