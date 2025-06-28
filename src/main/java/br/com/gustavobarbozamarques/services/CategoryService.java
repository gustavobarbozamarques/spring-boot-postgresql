package br.com.gustavobarbozamarques.services;

import br.com.gustavobarbozamarques.dto.CategoryDTO;
import br.com.gustavobarbozamarques.entities.Category;
import br.com.gustavobarbozamarques.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> listAll() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO listById(Integer id) {
        Category category = findById(id);
        return toDTO(category);
    }

    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = toEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return toDTO(savedCategory);
    }

    public CategoryDTO update(Integer id, CategoryDTO categoryDTO) {
        Category category = findById(id);
        category.setName(categoryDTO.getName());
        Category updatedCategory = categoryRepository.save(category);
        return toDTO(updatedCategory);
    }

    public void delete(Integer id) {
        findById(id); // Check if category exists before deleting
        categoryRepository.deleteById(id);
    }

    private Category findById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
    }

    private Category toEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }

    private CategoryDTO toDTO(Category entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
