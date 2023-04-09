package com.deljanin.restfulApiSFG.bootstrap;

import com.deljanin.restfulApiSFG.domain.Category;
import com.deljanin.restfulApiSFG.domain.Customer;
import com.deljanin.restfulApiSFG.domain.Vendor;
import com.deljanin.restfulApiSFG.repositories.CategoryRepository;
import com.deljanin.restfulApiSFG.repositories.CustomerRepository;
import com.deljanin.restfulApiSFG.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Loaded: " + categoryRepository.count());
    }

    private void loadCustomers(){
        Customer customer1 = new Customer(1L,"Mike","Tyson");
        customerRepository.save(customer1);

        Customer customer2 = new Customer(2L,"Dwayne","Johnson");
        customerRepository.save(customer2);

        System.out.println("Customers loaded: " + customerRepository.count());
    }

    private void loadVendors(){
        for (int i = 1; i < 7; i++) {
            vendorRepository.save(new Vendor(Long.valueOf(i),"Creative Vendor " + i));
        }
//        Vendor vendor1 = new Vendor(1L,"Creative Vendor Name 1");
//        vendorRepository.save(vendor1);
//
//        Vendor vendor2 = new Vendor(2L,"Creative Vendor Name 2");
//        vendorRepository.save(vendor2);
    }
}
