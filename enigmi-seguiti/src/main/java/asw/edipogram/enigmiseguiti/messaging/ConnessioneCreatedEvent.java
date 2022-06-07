package asw.edipogram.enigmiseguiti.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConnessioneCreatedEvent {
	@NonNull
	private String utente;

	@NonNull
	private String tipo;
}
