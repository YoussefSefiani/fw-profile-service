package fw.profileservice.controller;

import fw.profileservice.model.*;
import fw.profileservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public List<UserAndBrandWrapper> getBrands(@RequestHeader("Authorization") String token) {
        return brandService.getBrands(token);
    }

    @GetMapping(path = "{userId}")
    public UserAndBrandWrapper getBrand(@PathVariable("userId") Long userId, @RequestHeader("Authorization") String token) {
        return brandService.getBrandByUserId(userId, token);
    }

    @PostMapping
    public void registerBrand(@RequestBody RegisterRequest registerRequest) {
        brandService.registerBrand(registerRequest);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteBrand(@PathVariable("userId") Long userId) {
        brandService.deleteBrand(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateBrand(@PathVariable("userId") Long userId, @RequestBody Brand brand) {
       brandService.updateBrand(userId, brand);
    }
}

