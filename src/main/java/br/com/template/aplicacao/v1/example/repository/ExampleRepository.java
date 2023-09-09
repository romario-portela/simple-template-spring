package br.com.template.aplicacao.v1.example.repository;

import br.com.template.aplicacao.v1.example.domain.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {
}
