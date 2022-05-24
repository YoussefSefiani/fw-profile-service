package fw.profileservice.controller;

import fw.profileservice.model.*;
import fw.profileservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/brand")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<Brand> getBrands() {
        return brandService.getBrands();
    }

    @GetMapping(path = "{brandId}")
    public Brand getBrand(@PathVariable("brandId") Long brandId) {
        return brandService.getBrand(brandId);
    }

    @PostMapping
    public void registerBrand(@RequestBody RegisterRequest registerRequest) {
        brandService.registerBrand(registerRequest);
    }

    @DeleteMapping(path = "{brandId}")
    public void deleteBrand(@PathVariable("brandId") Long brandId) {
        brandService.deleteBrand(brandId);
    }

    @PutMapping(path = "{brandId}")
    public void updateBrand(@PathVariable("brandId") Long brandId, @RequestBody Brand brand) {
       brandService.updateBrand(brandId, brand);
    }
}

