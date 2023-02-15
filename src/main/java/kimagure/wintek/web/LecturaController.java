package kimagure.wintek.web;

import io.swagger.annotations.ApiOperation;
import kimagure.wintek.domain.ResponseVO;
import kimagure.wintek.domain.vo.LibroLecturaVO;
import kimagure.wintek.service.LecturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/lectura")
public class LecturaController {

    @Autowired
    LecturaService lecturaService;

    @GetMapping()
    public ResponseEntity<?> getLeido(){
        return ResponseEntity.ok().body(lecturaService.getLeido());
    }

    @ApiOperation(value = "Ejemplo de descripción de Api")
    @PostMapping
    public ResponseEntity<String> postLeido(@RequestBody LibroLecturaVO lecturaVO){

        ResponseVO resObj = lecturaService.postLeido(lecturaVO);
        return new ResponseEntity(resObj, HttpStatus.CREATED);
    }
}
