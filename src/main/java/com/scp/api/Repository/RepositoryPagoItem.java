package com.scp.api.Repository;

import com.scp.api.Model.PagoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPagoItem extends JpaRepository<PagoItem, Integer> {
}
