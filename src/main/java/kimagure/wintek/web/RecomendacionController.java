package kimagure.wintek.web;

import kimagure.wintek.service.RecomendacionService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.Calendar;

@CrossOrigin
@RestController
@RequestMapping("/recomendacion")
public class RecomendacionController {

    @Autowired
    private RecomendacionService recomendacionService;

    @GetMapping("/top/{cant}")
    public ResponseEntity<?> getTop(@PathVariable Integer cant){
        Integer anio = Year.now().getValue();
        return ResponseEntity.ok().body(recomendacionService.obtenerTop(anio, cant));
    }

    @GetMapping("/grilla")
    public ResponseEntity<?> getGrilla(){
        Integer anio = Year.now().getValue();
        return ResponseEntity.ok().body(recomendacionService.getGrilla(anio));
    }

    @PostMapping("/eliminar")
    public ResponseEntity<?> postEliminarRecomendacion(){
        recomendacionService.postEliminarRecomendacion();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/calcular")
    public ResponseEntity<?> postCalcular(){
        Integer anio = Year.now().getValue();
        recomendacionService.postCalcular(anio);
        return ResponseEntity.ok().build();
    }
}
