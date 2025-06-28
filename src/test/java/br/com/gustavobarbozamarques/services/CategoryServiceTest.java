package br.com.gustavobarbozamarques.services;

import br.com.gustavobarbozamarques.dto.CategoryDTO;
import br.com.gustavobarbozamarques.entities.Category;
import br.com.gustavobarbozamarques.mocks.CategoryDTOMock;
import br.com.gustavobarbozamarques.mocks.CategoryMock;
import br.com.gustavobarbozamarques.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void testListAll() {
        var categoriesFromDatabase = List.of(CategoryMock.get());
        when(categoryRepository.findAll()).thenReturn(categoriesFromDatabase);

        var categories = categoryService.listAll();

        assertThat(categories)
                .isNotEmpty()
                .hasSize(categoriesFromDatabase.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testListByIdShouldReturnCategoryWhenFound() {
        var category = CategoryMock.get();
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(category));

        var result = categoryService.listById(1);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(category.getName());
        verify(categoryRepository, times(1)).findById(anyInt());
    }

    @Test
    void testListByIdShouldThrowExceptionWhenNotFound() {
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> categoryService.listById(1));

        verify(categoryRepository, times(1)).findById(anyInt());
    }

    @Test
    void testSave() {
        var categoryToSave = CategoryMock.get();
        when(categoryRepository.save(any(Category.class))).thenReturn(categoryToSave);

        var savedCategory = categoryService.save(CategoryDTOMock.get());

        assertThat(savedCategory).isNotNull();
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testUpdateShouldUpdateSuccessfullyWhenCategoryExists() {
        var existingCategory = CategoryMock.get();
        var categoryDetails = CategoryDTOMock.get();
        categoryDetails.setName("Updated Name");

        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(existingCategory);

        var updatedCategory = categoryService.update(1, categoryDetails);

        assertThat(updatedCategory).isNotNull();
        assertThat(updatedCategory.getName()).isEqualTo("Updated Name");
        verify(categoryRepository, times(1)).findById(anyInt());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testUpdateShouldThrowExceptionWhenCategoryNotFound() {
        var categoryDetails = CategoryDTOMock.get();
        categoryDetails.setName("Updated Name");

        when(categoryRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> categoryService.update(1, categoryDetails));

        verify(categoryRepository, times(1)).findById(anyInt());
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    void testDelete() {
        var category = CategoryMock.get();
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).deleteById(anyInt());
        categoryService.delete(1);
        verify(categoryRepository, times(1)).deleteById(anyInt());
    }
}
