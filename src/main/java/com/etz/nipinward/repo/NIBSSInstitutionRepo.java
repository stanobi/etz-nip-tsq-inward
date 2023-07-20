package com.etz.nipinward.repo;

import com.etz.nipinward.model.NIBSSInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NIBSSInstitutionRepo extends JpaRepository<NIBSSInstitution, Long> {
}
