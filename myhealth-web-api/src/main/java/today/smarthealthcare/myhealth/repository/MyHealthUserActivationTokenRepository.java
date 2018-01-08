package today.smarthealthcare.myhealth.repository;


import today.smarthealthcare.myhealth.entity.MyHealthUserActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyHealthUserActivationTokenRepository extends JpaRepository<MyHealthUserActivationToken, Long>{
    MyHealthUserActivationToken findByToken(String token);
}
