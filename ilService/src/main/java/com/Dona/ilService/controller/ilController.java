package com.Dona.ilService.controller;

import com.Dona.ilService.exception.ilAlreadyExistsException;
import com.Dona.ilService.exception.ilNotFoundException;
import com.Dona.ilService.model.il;
import com.Dona.ilService.service.ilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/iller")
@RequiredArgsConstructor
public class ilController {
    private static final List<il> iller = new ArrayList<>();

    private final ilService ilService;

    //illeri isimle arama
    @GetMapping
    public ResponseEntity<List<il>> getIller(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(ilService.getIller(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<il> getIl(@PathVariable Long id) {
        return new ResponseEntity<>(ilService.getIlById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<il> createIl(@RequestBody il newIl) {
        return new ResponseEntity<>(ilService.createIl(newIl), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> getIl(@PathVariable Long id, @RequestBody il newIl) {
        ilService.updateIl(id, newIl);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIl(@PathVariable Long id) {
        ilService.deleteIl(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private il getById(Long id) {
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
