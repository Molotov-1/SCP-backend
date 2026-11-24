package com.scp.api.Repository;

import com.scp.api.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUsuario extends JpaRepository<Usuario, Integer> {
}
