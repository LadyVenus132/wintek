package kimagure.wintek.web;

import kimagure.wintek.service.RecomendacionService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.util.Calendar;

@Log
@RestController
@RequestMapping("/recomendacion")
public class RecomendacionController {

    @Autowired
    private RecomendacionService recomendacionService;

    @GetMapping("/top/{cant}")
    public ResponseEntity<?> getTop(@PathVariable Integer cant){
        Integer anio = Year.now().getValue();
        //Integer top = 20;
        return ResponseEntity.ok().body(recomendacionService.obtenerTop(anio, cant));
    }

    @GetMapping("/grilla")
    public ResponseEntity<?> getGrilla(){
        Integer anio = Year.now().getValue();
        return ResponseEntity.ok().body(recomendacionService.getGrilla(anio));
    }
}
