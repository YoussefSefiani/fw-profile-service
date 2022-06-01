package fw.profileservice.service;

import fw.profileservice.model.Brand;
import fw.profileservice.model.Influencer;
import fw.profileservice.model.RegisterRequest;
import fw.profileservice.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository){
        this.brandRepository = brandRepository;
    }

    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrand(Long brandId) {

        return brandRepository.findById(brandId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Brand with id %s does not exist", brandId))
                );
    }

    public void registerBrand(RegisterRequest registerRequest) {
        try {
            System.out.println("USER ID " + registerRequest.getUserId());
            Brand brand = new Brand(
                    (Long) null,
                    registerRequest.getUserId()
            );
            brandRepository.save(brand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error during register request" );
        }
    }

    public void deleteBrand(Long brandId) {
        boolean exists = brandRepository.existsById(brandId);

        if (!exists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Brand with id %s does not exist", brandId));
        }
        brandRepository.deleteById(brandId);
    }

    @Transactional
    public void updateBrand(Long brandId, Brand newBrand) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new IllegalStateException(
                        "brand with id " + brandId + "does not exist"
                ));

//        String description = newInfluencer.getDescription();
//        String headTitle = newInfluencer.getHeadTitle();
//        String ibanNumber = newInfluencer.getIbanNumber();
//        String offers = newInfluencer.getOffers();
//        String partnerships = newInfluencer.getPartnerships();
//
//        if (description != null && description.length() > 0 && !Objects.equals(influencer.getDescription(), description))
//            influencer.setDescription(description);
//
//        if (headTitle != null && headTitle.length() > 0 && !Objects.equals(influencer.getHeadTitle(), headTitle))
//            influencer.setDescription(description);
//
//        if (ibanNumber != null && ibanNumber.length() > 0 && !Objects.equals(influencer.getIbanNumber(), ibanNumber))
//            influencer.setDescription(description);
//
//        if (offers != null && offers.length() > 0 && !Objects.equals(influencer.getOffers(), offers))
//            influencer.setDescription(description);
//
//        if (partnerships != null && partnerships.length() > 0 && !Objects.equals(influencer.getPartnerships(), partnerships))
//            influencer.setDescription(description);


    }
}
