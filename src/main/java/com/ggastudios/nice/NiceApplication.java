package com.ggastudios.nice;

import com.ggastudios.nice.DTO.Record;
import com.ggastudios.nice.DTO.RecordResponse;
import com.ggastudios.nice.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@SpringBootApplication
@RestController()
@RequestMapping("/records")
public class NiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NiceApplication.class, args);
	}

	@Autowired
	private RecordService recordService;

	/**
	 * Metodo para insertar una puntuacion y si existe alguna del mismo usuario actualizar
	 * @param record objeto a actualizar
	 * @return estado de la operacion
	 */
	@PostMapping(value = "/insert" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RecordResponse insert(@RequestBody Record record){
		return recordService.insert(record);
	}

	/**
	 * Metodo para obtener los 10 mejores resultados
	 * @param idApplication identificador de aplicacion
	 * @param level nivel de juego
	 * @return
	 */
	@GetMapping(value = "/top-ten",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RecordResponse> getTopTen(@PathParam("idApplication") String idApplication, @PathParam("level") int level){
		return recordService.getTopTen(idApplication,level);
	}

	/**
	 * Devuelve las 10 puntuaciones anteriores a la recibida.
	 * @param idApplication identificador de aplicacion
	 * @param level nivel de juego
	 * @param score puntuacion
	 * @return
	 */
	@GetMapping(value = "/previous-ten",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RecordResponse> getPreviousTen(@PathParam("idApplication") String idApplication,@PathParam("level") int level,@PathParam("score")int score){
		return recordService.getPreviousTen(idApplication,level,score);
	}

}
