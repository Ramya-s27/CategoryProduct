package com.cloths;

import com.cloths.entity.Cloth;
import com.cloths.repository.ClothRepository;
import com.cloths.service.ClothService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ClothServiceTest {

    @Mock
    private ClothRepository clothRepository;

    @InjectMocks
    private ClothService clothService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {

        Cloth cloth1 = new Cloth(1L, "Half Saree", "South Traditional Dress", new BigDecimal("1000.00"), LocalDateTime.now(), LocalDateTime.now(), null);
        Cloth cloth2 = new Cloth(2L, "Punjabi", "Punjabi Traditional Dress", new BigDecimal("1500.00"), LocalDateTime.now(), LocalDateTime.now(), null);

        when(clothRepository.findAll()).thenReturn(List.of(cloth1, cloth2));


        var result = clothService.getAllProducts();


        assertEquals(2, result.size());
        assertEquals("Half Saree", result.get(0).getName());
        assertEquals("Punjabi", result.get(1).getName());
    }

    @Test
    void testGetProductById() {

        Long productId = 1L;
        Cloth cloth = new Cloth(productId, "Half Saree", "South Traditional Dress", new BigDecimal("1000.00"), LocalDateTime.now(), LocalDateTime.now(), null);

        when(clothRepository.findById(productId)).thenReturn(Optional.of(cloth));

        var result = clothService.getClothProductById(productId);


        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals("Half Saree", result.getName());
    }

    @Test
    void testCreateProduct() {
        Cloth cloth = new Cloth(null, "New Product", "New Description", new BigDecimal("15.00"), LocalDateTime.now(), LocalDateTime.now(), null);
        Cloth savedCloth = new Cloth(1L, "New Product", "New Description", new BigDecimal("15.00"), LocalDateTime.now(), LocalDateTime.now(), null);

        when(clothRepository.save(any(Cloth.class))).thenReturn(savedCloth);


        var result = clothService.createClothProduct(cloth);


        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New Product", result.getName());
    }

    @Test
    void testUpdateProduct() {

        Long productId = 1L;
        Cloth existingCloth = new Cloth(productId, "Half Saree", "South Traditional Dress", new BigDecimal("1000.00"), LocalDateTime.now(), LocalDateTime.now(), null);
        Cloth updatedCloth = new Cloth(productId, "Updated Product", "Updated Description", new BigDecimal("1500.00"), LocalDateTime.now(), LocalDateTime.now(), null);

        when(clothRepository.findById(productId)).thenReturn(Optional.of(existingCloth));
        when(clothRepository.save(any(Cloth.class))).thenReturn(updatedCloth);


        var result = clothService.updateProduct(productId, updatedCloth);


        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals("Updated Product", result.getName());
    }

    @Test
    void testDeleteProduct() {

        Long productId = 1L;
        Cloth cloth = new Cloth(productId, "Half Saree", "South Traditional Dress", new BigDecimal("1000.00"), LocalDateTime.now(), LocalDateTime.now(), null);

        when(clothRepository.findById(productId)).thenReturn(Optional.of(cloth));


        assertDoesNotThrow(() -> clothService.deleteClothProduct(productId));
    }
}
