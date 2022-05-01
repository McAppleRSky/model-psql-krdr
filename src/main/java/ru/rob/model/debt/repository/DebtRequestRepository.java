package ru.rob.model.debt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rob.model.debt.entity.DebtRequest;

import java.util.List;

@Repository
public interface DebtRequestRepository extends JpaRepository<DebtRequest, String> {

    List<DebtRequest> findAll();

}
