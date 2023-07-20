package com.etz.nipinward.repo;

import com.etz.nipinward.model.NIBSSBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NIBSSBlockRepo extends JpaRepository<NIBSSBlock, Long> {
}
