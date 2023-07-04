package com.ecommerce.admin.user;

import com.ecommerce.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository repository;

    @Test
    public void testCreateFirstRole() {
        //given
        Role admin = new Role("Admin", "Manage everything");

        //when
        Role savedRole = repository.save(admin);

        //then
        assertThat(savedRole.getId()).isGreaterThan(0);
        assertThat(savedRole.getName()).isEqualTo("Admin");
    }

    @Test
    public void testCreateRestRoles() {
        //given
        Role salesperson = new Role("Salesperson", "Manage product price, customers, shipping, orders and sales report");
        Role editor = new Role("Editor", "Manage categories, brands, products, articles and menus");
        Role shipper = new Role("Shipper", "View products, orders and update order status");
        Role assistant = new Role("Assistant", "Manage questions and reviews");

        //when
        List<Role> roles = List.of(salesperson, editor, shipper, assistant);
        repository.saveAll(roles);

        //then
        assertThat(roles.size()).isEqualTo(4);
    }
}
