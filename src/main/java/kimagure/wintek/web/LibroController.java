package kimagure.wintek.web;

import kimagure.wintek.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;

@CrossOrigin
@RestController
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    LibroService libroService;

    @GetMapping("/basico")
    public ResponseEntity<?> getLibroBasico(){
        return ResponseEntity.ok().body(libroService.getLibroBasico());
    }
}
