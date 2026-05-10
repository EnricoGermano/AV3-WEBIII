package com.autobots.automanager.repositorios;

import com.autobots.automanager.entidades.CredencialAcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredencialAcessoRepositorio extends JpaRepository<CredencialAcesso, Long> {
}
