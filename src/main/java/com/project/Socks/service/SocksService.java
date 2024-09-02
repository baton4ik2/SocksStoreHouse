package com.project.Socks.service;

import com.project.Socks.entity.Socks;
import com.project.Socks.repository.SocksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocksService {
    Logger logger = LoggerFactory.getLogger(SocksService.class);

    @Autowired
    private SocksRepository socksRepository;

    //Изменение кол-ва носков на складе
    public Socks changeSocks(Socks socks) {
        return socksRepository.save(socks);
    }

    // Поиск носков по цвету и проценту хлопка
    public Socks findByColorAndCottonPart(String color, int cottonPart){
        return socksRepository.findByColorIgnoreCaseAndCottonPart(color, cottonPart);
    }

    // Поиск носков по цвету
    public List<Socks> findByColor(String color) {
        return socksRepository.findByColorIgnoreCase(color);
    }

    public List<Socks> findByCottonPartGreaterTHan(String color, int cottonPartCompare){
        return socksRepository.findByColorIgnoreCaseAndCottonPartGreaterThan(color, cottonPartCompare);
    }

    public List<Socks> findByCottonPartLessThan(String color, int cottonPartCompare){
        return socksRepository.findByColorIgnoreCaseAndCottonPartLessThan(color, cottonPartCompare);
    }

    public List<Socks> findByCottonPartEquals(String color, int cottonPartCompare){
        return socksRepository.findByColorIgnoreCaseAndCottonPartEquals(color, cottonPartCompare);
    }

}
