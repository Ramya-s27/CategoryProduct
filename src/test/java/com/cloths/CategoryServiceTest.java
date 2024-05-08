package com.cloths;

import com.cloths.entity.Category;
import com.cloths.repository.CategoryRepository;
import com.cloths.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Category 1", "South Traditional Dress", new ArrayList<>()));
        categories.add(new Category(2L, "Category 2", "Punjabi Traditional Dress", new ArrayList<>()));

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAllCategories();

        assertEquals(categories.size(), result.size());
        for (int i = 0; i < categories.size(); i++) {
            assertEquals(categories.get(i).getCategory_id(), result.get(i).getCategory_id());
            assertEquals(categories.get(i).getName(), result.get(i).getName());
            assertEquals(categories.get(i).getDescription(), result.get(i).getDescription());
        }
    }

    @Test
    void testGetCategoryById() {
        Long categoryId = 1L;
        Category category = new Category(categoryId, "Category 1", "South Traditional Dress", new ArrayList<>());

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        Category result = categoryService.getCategoryById(categoryId);

        assertNotNull(result);
        assertEquals(categoryId, result.getCategory_id());
        assertEquals("Category 1", result.getName());
        assertEquals("South Traditional Dress", result.getDescription());
    }

    @Test
    void testCreateCategory() {
        Category category = new Category(1L, "Category 1", "South Traditional Dress", new ArrayList<>());

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category result = categoryService.createCategory(category);

        assertNotNull(result);
        assertEquals(category, result);
    }

    @Test
    void testUpdateCategory() {
        Long categoryId = 1L;
        Category existingCategory = new Category(categoryId, "Category 1", "South Traditional Dress", new ArrayList<>());
        Category updatedCategory = new Category(categoryId, "Updated Category", "Updated Description", new ArrayList<>());

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(updatedCategory);

        Category result = categoryService.updateCategory(categoryId, updatedCategory);

        assertNotNull(result);
        assertEquals("Updated Category", result.getName());
        assertEquals("Updated Description", result.getDescription());
    }

    @Test
    void testDeleteCategory() {
        Long categoryId = 1L;
        Category category = new Category(categoryId, "Category 1", "South Traditional Dress", new ArrayList<>());

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        assertDoesNotThrow(() -> categoryService.deleteCategory(categoryId));
    }
}
