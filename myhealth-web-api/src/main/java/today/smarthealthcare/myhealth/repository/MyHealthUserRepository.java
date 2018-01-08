package today.smarthealthcare.myhealth.repository;

import today.smarthealthcare.myhealth.entity.MyHealthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyHealthUserRepository extends JpaRepository<MyHealthUser, Long> {
    MyHealthUser findByEmail(String email);
}
