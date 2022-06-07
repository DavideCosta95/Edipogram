package asw.edipogram.connessioni.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConnessioneCreatedEvent {
	@NonNull
	private String tipo;

	@NonNull
	private String utente;
}
