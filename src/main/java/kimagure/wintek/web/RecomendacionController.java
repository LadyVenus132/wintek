package kimagure.wintek.web;

import kimagure.wintek.service.RecomendacionService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@RequestMapping("/recomendacion")
public class RecomendacionController {

    @Autowired
    private RecomendacionService recomendacionService;

    @GetMapping("/top")
    public ResponseEntity<?> getTop(){
        log.info("[getTop] : ");
        return ResponseEntity.ok().body(recomendacionService.getTop());
    }

    @GetMapping("/grilla")
    public ResponseEntity<?> getGrilla(){
        log.info("[getGrilla] : ");
        return ResponseEntity.ok().body(recomendacionService.getGrilla());
    }
}
