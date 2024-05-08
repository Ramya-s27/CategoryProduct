package com.cloths.service;

import com.cloths.entity.Cloth;
import com.cloths.exception.ResourceNotFoundException;
import com.cloths.repository.ClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothService {

    @Autowired
    private ClothRepository clothRepository;

    public List<Cloth> getAllProducts() {
        return clothRepository.findAll();
    }

    public Cloth getClothProductById(Long id) {
        return clothRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    public Cloth createClothProduct(Cloth cloth) {
        return clothRepository.save(cloth);
    }

    public Cloth updateProduct(Long id, Cloth clothDetails) {
        Cloth cloth = getClothProductById(id);
        cloth.setName(clothDetails.getName());
        cloth.setDescription(clothDetails.getDescription());
        cloth.setPrice(clothDetails.getPrice());
        return clothRepository.save(cloth);
    }

    public void deleteClothProduct(Long id) {
        Cloth cloth = getClothProductById(id);
        clothRepository.delete(cloth);
    }
}
