package cst8277.gabe.lee.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepo extends JpaRepository<Login, Long> {
}
