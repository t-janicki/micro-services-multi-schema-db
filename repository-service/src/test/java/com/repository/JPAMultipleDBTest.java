package com.repository;

import com.config.CustomerRepositoryConfig;
import com.domain.customer.Customer;
import com.repository.customer.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CustomerRepositoryConfig.class})
@Transactional
public class JPAMultipleDBTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Transactional("customerTransactionManager")
    public void whenCreatingCustomer_thenCreated() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setSurname("john@test.com");
        customer.setPesel("292929292");
        customer = customerRepository.save(customer);

        assertNotNull(customerRepository.findById(customer.getId()));
    }


//    @Test
//    @Transactional("productTransactionManager")
//    public void whenCreatingProduct_thenCreated() {
//        Product product = new Product();
//        product.setName("Book");
//        product.setId(2);
//        product.setPrice(20);
//        product = productRepository.save(product);
//
//        assertNotNull(productRepository.findOne(product.getId()));
//    }
}