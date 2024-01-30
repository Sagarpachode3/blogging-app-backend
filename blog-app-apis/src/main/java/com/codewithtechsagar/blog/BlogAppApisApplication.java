package com.codewithtechsagar.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.codewithtechsagar.blog.config.AppConstants;
import com.codewithtechsagar.blog.entities.Role;
import com.codewithtechsagar.blog.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordencoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordencoder.encode("xyz"));
		try {

			Role role = new Role();
			role.setRoleId(AppConstants.ADMIN_USER);
			role.setRoleName("ADMIN_USER");

			Role role2 = new Role();
			role2.setRoleId(AppConstants.NORMAL_USER);
			role2.setRoleName("NORMAL_USER");

			List<Role> roles = List.of(role, role2);
			List<Role> result = this.roleRepo.saveAll(roles);

			result.forEach(r -> {
				System.out.println(r.getRoleName());
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
