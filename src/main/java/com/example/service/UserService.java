package com.example.service;

import com.example.domain.*;
import com.example.dto.AvailableDiscountsDTO;
import com.example.dto.ConsumeProductDTO;
import com.example.dto.FinalPriceDTO;
import com.example.dto.ProductDTO;
import com.example.dto.body.ConsumeProductsBody;
import com.example.dto.body.UserBody;
import com.example.exception.*;
import com.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UsernameRepository usernameRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final MembershipRepository membershipRepository;
    private final BrandRepository brandRepository;
    private final DiscountRepository discountRepository;
    private final StockRepository stockRepository;

    private final MemberService memberService;

    @Autowired
    public UserService(UserRepository userRepository, UsernameRepository usernameRepository, ProductRepository productRepository,
                       MemberRepository memberRepository, MembershipRepository membershipRepository, BrandRepository brandRepository,
                       DiscountRepository discountRepository, StockRepository stockRepository, MemberService memberService) {
        this.userRepository = userRepository;
        this.usernameRepository = usernameRepository;
        this.productRepository = productRepository;
        this.memberRepository = memberRepository;
        this.membershipRepository = membershipRepository;
        this.brandRepository = brandRepository;
        this.discountRepository = discountRepository;
        this.stockRepository = stockRepository;
        this.memberService = memberService;
    }

    public User findUser(Long id) {
        Optional<User> user =  this.userRepository.findById(new UserID(id));

        if (user.isEmpty()) {
            throw new NotFoundException("Unable to find any user with the informed id");
        } else {
            return user.get();
        }
    }

    public User addNewUser(UserBody user) {

        if (user.getUsername() == null)
            throw new MissingUsernameException("Please inform an username");

        if (this.usernameRepository.findByIdUsername(user.getUsername()).isPresent())
            throw new TakenUsernameException("Username is already taken");

        Long latestUserID = this.userRepository.count() + 1;

        UserID userID = new UserID(latestUserID);

        User newUser = new User();
        newUser.setId(userID);
        newUser.setFullName(user.getFullName());
        newUser.setBirthDate(user.getBirthDate());
        newUser.setSignInDate(LocalDate.now());

        newUser = this.userRepository.save(newUser);

        UsernameId usernameId = new UsernameId(latestUserID, user.getUsername());
        Username username = new Username();
        username.setId(usernameId);
        this.usernameRepository.save(username);

        if (user.getMembership() == null)
            user.setMembership(0L);

        this.memberService.addNewMember(latestUserID, user.getMembership());

        return newUser;
    }

    public AvailableDiscountsDTO findAvailableDiscounts(String username) {

        User user = this.findUserByUsername(username);

        Membership membership = this.findMembershipByUser(user);

        AvailableDiscountsDTO discountsDTO = new AvailableDiscountsDTO(user.getFullName(), membership.getMembershipName());

        List<ProductDTO> products = new ArrayList<>();

        this.discountRepository.findAllByIdMembershipType(membership.getId().getMembershipId()).forEach(discount -> {
            Optional<Product> optionalProduct = this.productRepository.findById(new ProductID(discount.getId().getProductId()));

            if (optionalProduct.isEmpty())
                throw new NotFoundException("Product not found");

            Product product = optionalProduct.get();

            Optional<Brand> optionalBrand = this.brandRepository.findById(new BrandID(product.getProductBrand()));

            if (optionalBrand.isEmpty())
                throw new NotFoundException("Brand not found");

            Brand brand = optionalBrand.get();

            Double finalPrice = product.getPrice() * (100 - discount.getPercentage())/100;

            ProductDTO productDTO = new ProductDTO(brand.getBrandName(), product.getProductName(), product.getPrice(), discount.getPercentage(), finalPrice);
            products.add(productDTO);
        });

        discountsDTO.setProducts(products);

        return discountsDTO;
    }

    public FinalPriceDTO consumeProducts(ConsumeProductsBody body) {
        double finalPrice = 0D;

        User user = this.findUserByUsername(body.getUsername());
        Membership membership = this.findMembershipByUser(user);

        List<Product> requestedProducts = this.productRepository.findAllByProductNameIn(body.getProducts().stream().map(ConsumeProductDTO::getProductName).toList());
        List<Discount> discounts = this.discountRepository.findAllByIdMembershipType(membership.getId().getMembershipId());

        for (Product product : requestedProducts) {
            Optional<Discount> discount = discounts.stream().filter(discount1 -> discount1.getId().getProductId().equals(product.getId().getProductId())).findAny();

            double pricePerProduct = discount.isEmpty() ? product.getPrice() : product.getPrice() * (100 - discount.get().getPercentage())/100;

            Optional<Stock> optionalStock = this.stockRepository.findById(new StockID(product.getId().getProductId()));
            if (optionalStock.isEmpty())
                throw new MissingRegisterException("No stock register found for this product");

            Stock stock = optionalStock.get();
            if (stock.getQuantity() == 0)
                throw new UnavailableStockException("No stock left for this particular product");

            Optional<Long> optionalQuantity = body.getProducts().stream().filter(consumeProductDTO -> consumeProductDTO.getProductName().equals(product.getProductName())).map(ConsumeProductDTO::getQuantity).findAny();
            Long quantity = optionalQuantity.orElse(0L);
            quantity = quantity < stock.getQuantity() ? quantity : stock.getQuantity();

            finalPrice += pricePerProduct * quantity;

            stock.setQuantity(stock.getQuantity() - quantity);
            this.stockRepository.save(stock);
        }

        return new FinalPriceDTO(finalPrice);
    }

    private Membership findMembershipByUser(User user) {
        Optional<Member> optionalMember = this.memberRepository.findByUserId(user.getId().getUserID());

        if (optionalMember.isEmpty() || optionalMember.get().getMembershipStatus().equals("I"))
            throw new NoActiveMembershipException("User does not have an active membership");

        Optional<Membership> optionalMembership = this.membershipRepository.findById(new MembershipID(optionalMember.get().getMembershipType()));

        if (optionalMembership.isEmpty())
            throw new NotFoundException("Membership not found");

        return optionalMembership.get();
    }

    private User findUserByUsername(String username) {
        if (username == null)
            throw new MissingUsernameException("No username informed");

        Optional<Username> optionalUsername = this.usernameRepository.findByIdUsername(username);

        if (optionalUsername.isEmpty())
            throw new NotFoundException("Username " + username + " not found");

        Optional<User> optionalUser = this.userRepository.findById(new UserID(optionalUsername.get().getId().getUserId()));

        if (optionalUser.isEmpty())
            throw new NotFoundException("User not found with username: " + username);

        return optionalUser.get();
    }
}
