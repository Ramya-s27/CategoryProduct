package com.cloths.controller;

import com.cloths.entity.Cloth;
import com.cloths.service.ClothService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cloths")
public class ClothController {

    @Autowired
    private ClothService clothService;

    @GetMapping
    public List<Cloth> getAllClothProducts() {
        return clothService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cloth> getClothProductById(@PathVariable Long id) {
        Cloth cloth = clothService.getClothProductById(id);
        return ResponseEntity.ok().body(cloth);
    }

    @PostMapping("/create")
    public ResponseEntity<Cloth> createClothProduct(@Valid @RequestBody Cloth cloth) {
        Cloth createdCloth = clothService.createClothProduct(cloth);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCloth);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cloth> updateClothProduct(@PathVariable Long id, @Valid @RequestBody Cloth clothDetails) {
        Cloth updatedCloth = clothService.updateProduct(id, clothDetails);
        return ResponseEntity.ok().body(updatedCloth);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClothProduct(@PathVariable Long id) {
        clothService.deleteClothProduct(id);
        return ResponseEntity.noContent().build();
    }


}
