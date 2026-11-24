package com.scp.api.Repository;

import com.scp.api.Model.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCarritoItem extends JpaRepository<CarritoItem, Integer> {
}
