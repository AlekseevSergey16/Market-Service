package alekseev.market.controller;

import alekseev.market.dto.CategoryDTO;
import alekseev.market.dto.CategoryWithProductDTO;
import alekseev.market.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryWithProductDTO>> getCategory() {
        List<CategoryWithProductDTO> categories = categoryService.getAllCategories();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryWithProductDTO> getCategories(@PathVariable int id) {
        CategoryWithProductDTO category = categoryService.getCategory(id);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.saveCategory(categoryDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable int id) {
        categoryService.updateCategory(id, categoryDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id)  {
        categoryService.deleteCategory(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
