package fw.profileservice.service;

import fw.profileservice.model.Brand;
import fw.profileservice.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
   // private final UserService userService;

    @Autowired
    public BrandService(BrandRepository brandRepository /*, UserService userService */){
        this.brandRepository = brandRepository;
       //this.userService = userService;
    }

    public void registerBrand(Brand brand) {


        System.out.println("in brand register method");
//        if(userService.checkUsername(brand.getUser()))
         //brandRepository.save(brand);
    }

}
