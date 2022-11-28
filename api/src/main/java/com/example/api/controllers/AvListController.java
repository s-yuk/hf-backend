package com.example.api.controllers;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.api.model.AvList;
import com.example.api.services.AvListService;


@RequestMapping(path = AvListController.BASE_URL)
@RestController
public class AvListController {
  public static final String BASE_URL = "/api/v1/avlist";

  @Autowired
  private AvListService _avListService;

  @GetMapping(path = "")
  public ResponseEntity<List<AvList>> getAvLists() {
    List<AvList> avLists = _avListService.getAvLists();
    return ResponseEntity.ok(avLists);
  }

  @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<AvList>> getAvList(@PathVariable int id) {
        Optional<AvList> avList = _avListService.getAvListById(id);
        return ResponseEntity.ok(avList);
    }

    @PostMapping(path = "")
    public ResponseEntity<AvList> createAvList(@RequestBody AvList newAvList) {
        AvList savedAvList = _avListService.saveAvList(newAvList);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAvList.getId()).toUri();
        return ResponseEntity.created(location).body(savedAvList);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateAvList(@PathVariable int id, @RequestBody AvList newAvList) {
        _avListService.updateAvListById(id, newAvList);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> removeAvList(@PathVariable int id) {
        _avListService.removeAvListById(id);
        return ResponseEntity.noContent().build();
    }
}
