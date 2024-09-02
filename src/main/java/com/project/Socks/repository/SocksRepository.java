package com.project.Socks.repository;



import com.project.Socks.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {

    Socks findByColorIgnoreCaseAndCottonPart(String color, int cottonPart); // поиск по цвету(Игнорируя регистр) и процента хлопка

    List<Socks> findByColorIgnoreCase(String color);

    List<Socks> findByColorIgnoreCaseAndCottonPartGreaterThan(String color, int cottonPartCompare);

    List<Socks> findByColorIgnoreCaseAndCottonPartLessThan(String color, int cottonPartCompare);

    List<Socks> findByColorIgnoreCaseAndCottonPartEquals(String color, int cottonPartCompare);
}