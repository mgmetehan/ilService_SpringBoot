package com.Dona.ilService.controller;

import com.Dona.ilService.exception.ilAlreadyExistsException;
import com.Dona.ilService.exception.ilNotFoundException;
import com.Dona.ilService.model.il;
import com.Dona.ilService.service.ilService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/iller")
@AllArgsConstructor
public class ilController {
    private static final List<il> iller = new ArrayList<>();

    private final ilService ilService;

    //illeri isimle arama
    @GetMapping
    public ResponseEntity<List<il>> getIller(@RequestParam(required = false) String name) {
        //Bu zorunlu olmayan parametre
        return new ResponseEntity<>(ilService.getIller(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<il> getIl(@PathVariable String id) {
        return new ResponseEntity<>(getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<il> createIl(@RequestBody il newIl) {
        return new ResponseEntity<>(ilService.createIl(newIl), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> getIl(@PathVariable String id, @RequestBody il newIl) {
        ilService.updateIl(id, newIl);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIl(@PathVariable String id) {
        ilService.deleteIl(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private il getById(String id) {
        return ilService.getIlById(id);
    }

    @ExceptionHandler(ilNotFoundException.class)
    public ResponseEntity<String> handleIlNotFoundException(ilNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ilAlreadyExistsException.class)
    public ResponseEntity<String> handleIlIlAlreadyExistsException(ilAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }


}
