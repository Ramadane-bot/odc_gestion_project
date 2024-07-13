package com.odcspring.web_app.repository;

import com.odcspring.web_app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
