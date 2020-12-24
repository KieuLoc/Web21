package application.controller.web;


import application.data.model.*;
import application.data.repository.OrderRepository;
import application.data.service.CategoryService;
import application.data.service.OrderService;
import application.data.service.ProductService;
import application.data.service.UserService;
import application.model.viewmodel.admin.*;
import application.model.viewmodel.common.CategoryVM;
import application.model.viewmodel.common.ChartDataVM;
import application.model.viewmodel.common.ProductImageVM;
import application.model.viewmodel.common.ProductVM;
//import application.model.viewmodel.common.UserVM;
import application.model.viewmodel.order.OrderVM;
import application.model.viewmodel.user.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;
    @GetMapping("")
    public String admin(Model model) {

        HomeAdminVM vm = new HomeAdminVM();
        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());

        model.addAttribute("vm", vm);
        return "/admin/home";
    }

    @GetMapping("/user")
    public String user(Model model,
                           @Valid @ModelAttribute("username") UserVM userName,
                           @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                           @RequestParam(name = "size", required = false, defaultValue = "8") Integer size
    ) {
        AdminProductVM vm = new AdminProductVM();
//        List<User> userList = userService.getListAllUsers();
////        List<application.model.viewmodel.common.UserVM> userVMList = new ArrayList<>();
//        List<UserVM> userVMList = new ArrayList<>();
//        Pageable pageable = new PageRequest(page, size);
//
//        Page<User> userPage = null;
//
//        for (User user : userList) {
//            UserVM userVM = new UserVM();
//            userVM.setId(user.getId());
//            userVM.setName(user.getName());
//            userVM.add(userVM);
//        }
//        if(userName.getName() != null && !userName.getName().isEmpty()){
//            userPage = userService.getListAllUsers(pageable, null);
//            vm.setKeyWord("Find with key: " + userName.getName());
//        } else {
//            userPage = userService.getListAllUsers(pageable, null);
//        }
//        for(User user: userPage.getContent()){
//            UserVM userVM = new UserVM();
//            userVM.setId(user.getId());
//            userVM.setName(user.getName());
//            userVM.setPhoneNumber(user.getPhoneNumber());
//            userVM.setAddress(user.getAddress());
//            userVM.setEmail(user.getEmail());
//            userVMList.add(userVM);
//        }
//        if (userVMList.size() == 0) {
//            vm.setKeyWord("Not found any user");
//        }
//
        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
//        vm.setUserVMList(userVMList);
        model.addAttribute("vm", vm);
//        model.addAttribute("page", userPage);
        return "/admin/user";
    }

    @GetMapping("/product")
    public String product(Model model,
                          @Valid @ModelAttribute("productname") ProductVM productName,
                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                          @RequestParam(name = "size", required = false, defaultValue = "8") Integer size
    ) {
        AdminProductVM vm = new AdminProductVM();


        /**
         * set list categoryVM
         */
        List<Category> categoryList = categoryService.getListAllCategories();
        List<CategoryVM> categoryVMList = new ArrayList<>();

        for (Category category : categoryList) {
            CategoryVM categoryVM = new CategoryVM();
            categoryVM.setId(category.getId());
            categoryVM.setName(category.getName());
            categoryVMList.add(categoryVM);
        }


        Pageable pageable = new PageRequest(page, size);

        Page<Product> productPage = null;

        if (productName.getName() != null && !productName.getName().isEmpty()) {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable, null, productName.getName().trim());
            vm.setKeyWord("Find with key: " + productName.getName());
        } else {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable, null, null);
        }


        List<ProductVM> productVMList = new ArrayList<>();

        for (Product product : productPage.getContent()) {
            ProductVM productVM = new ProductVM();
            if (product.getCategory() == null) {
                productVM.setCategoryName("Unknown");
            } else {
                productVM.setCategoryName(product.getCategory().getName());
            }
            productVM.setId(product.getId());
            productVM.setName(product.getName());
            productVM.setMainImage(product.getMainImage());
            productVM.setPrice(product.getPrice());
            productVM.setShortDesc(product.getShortDesc());
            productVM.setCreatedDate(product.getCreatedDate());

            productVMList.add(productVM);
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setCategoryVMList(categoryVMList);
        vm.setProductVMList(productVMList);
        if (productVMList.size() == 0) {
            vm.setKeyWord("Not found any product");
        }


        model.addAttribute("vm", vm);
        model.addAttribute("page", productPage);

        return "/admin/product";
    }


    @GetMapping("/category")
    public String category(Model model,
                          @Valid @ModelAttribute("categoryname") CategoryVM categoryName,
                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                          @RequestParam(name = "size", required = false, defaultValue = "8") Integer size
    ) {
        AdminCategoryVM vm = new AdminCategoryVM();


        Pageable pageable = new PageRequest(page, size);

        Page<Category> categoryPage =  null;


        if (categoryName.getName() != null && !categoryName.getName().isEmpty()) {
            categoryPage = categoryService.getListCategoryByCategoryNameContaining(pageable, categoryName.getName().trim());
            vm.setKeyWord("Find with key: " + categoryName.getName());
        } else categoryPage = categoryService.getListCategoryByCategoryNameContaining(pageable, null);


        List<CategoryVM> categoryVMList = new ArrayList<>();

        for (Category category : categoryPage.getContent()) {
            CategoryVM categoryVM = new CategoryVM();

            categoryVM.setId(category.getId());
            categoryVM.setName(category.getName());
            categoryVM.setShortDesc(category.getShortDesc());
            categoryVM.setCreatedDate(category.getCreatedDate());

            categoryVMList.add(categoryVM);
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setCategoryVMList(categoryVMList);
        if (categoryVMList.size() == 0) {
            vm.setKeyWord("Not found any category");
        }

//
        model.addAttribute("vm", vm);
        model.addAttribute("page", categoryPage);
//
        return "/admin/category";
    }

    @GetMapping("/chart")
    public String chart(Model model) {

        ChartVM vm = new ChartVM();

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());

        List<ChartDataVM> chartDataVMS = new ArrayList<>();

        List<Category> categories = categoryService.getListAllCategories();

        for(Category category : categories) {
            chartDataVMS.add(new ChartDataVM(category.getName(), (long) category.getListProducts().size()));
        }

        vm.setChartDataVMS(chartDataVMS);

        model.addAttribute("vm", vm);

        return "/admin/chart";
    }

    @GetMapping("/product-image/{productId}")
    public String product(Model model, @PathVariable int productId) {
        AdminProductImageVM vm = new AdminProductImageVM();

        Product productEntity = productService.findOne(productId);


        List<ProductImageVM> productImageVMS = new ArrayList<>();

        for (ProductImage productImage : productEntity.getProductImageList()) {
            ProductImageVM productImageVM = new ProductImageVM();
            productImageVM.setId(productImage.getId());
            productImageVM.setLink(productImage.getLink());
            productImageVM.setCreatedDate(productImage.getCreatedDate());
            productImageVMS.add(productImageVM);
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setProductImageVMS(productImageVMS);
        vm.setProductId(productId);


        model.addAttribute("vm", vm);

        return "/admin/product-image";
    }
    @GetMapping("/order")
    public String Order(Model model,
                        @Valid @ModelAttribute("phonenumber") OrderVM phoneNumber,
                        @RequestParam(name="page" ,required = false,defaultValue = "0") Integer page,
                        @RequestParam(name="size",required = false,defaultValue = "10") Integer size){

        AdminOrderVM vm=new AdminOrderVM();

        /*
        set list category
        * */
//        List<CategoryVM> categoryVMList=categoryService.getListCategories();

        Pageable pageable=new PageRequest(page,size);

//        Page<OrderVM> orderPage=null;
        Page<Order> orderPage=null;

        if(phoneNumber.getPhoneNumber() != null && !phoneNumber.getPhoneNumber().isEmpty()){
            orderPage=orderRepository.getListOrdersByPhoneNumberContaining(pageable,phoneNumber.getPhoneNumber());
            vm.setKeyword("Find with key: " + phoneNumber.getPhoneNumber());
        }else{
            orderPage=orderRepository.getListOrdersByPhoneNumberContaining(pageable,null);
        }

        List<OrderVM> orderVMS=new ArrayList<>();

        for(Order order:orderPage.getContent()){
            OrderVM orderVM = new OrderVM();
            orderVM.setId(order.getId());
            orderVM.setCustomerName(order.getCustomerName());
            orderVM.setCreatedDate(order.getCreatedDate());
            orderVM.setEmail(order.getEmail());
            orderVM.setPhoneNumber(order.getPhoneNumber());
            orderVM.setStatus(order.getStatus());
            orderVM.setPrice(Double.toString(order.getPrice()));
            orderVM.setAddress(order.getAddress());

            orderVMS.add(orderVM);
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setOrderVMS(orderVMS);
        if (orderVMS.size() == 0) {
            vm.setKeyword("Not found any order");
        }

        model.addAttribute("vm", vm);
        model.addAttribute("page", orderPage);

        return "/admin/order";
    }
}
