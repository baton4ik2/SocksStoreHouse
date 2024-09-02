package com.project.Socks.controller;

import com.project.Socks.entity.Socks;
import com.project.Socks.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socks")
public class SocksController {



    @Autowired
    private SocksService socksService;

    @PostMapping("/income")    // Регистрирует приход носков на склад
    public ResponseEntity<Socks> incomeSocks(@RequestParam String color,
                                             @RequestParam int cottonPart,
                                             @RequestParam int quantity) {
        Socks socks = socksService.findByColorAndCottonPart(color, cottonPart); // Ищем носки по цвету и процену хлопка
        int result = socks.getQuantity() + quantity; // Складываем кол-во носков которые у нас на складе и кол-во носков прибывшее на склад
        socks.setQuantity(result); // Меняем значение кол-ва носков на складе
        Socks incomingSocks = socksService.changeSocks(socks); // перезаписываем значение в базе данных
        return ResponseEntity.ok(incomingSocks);
    }

    @PostMapping("/outcome")    // Регистрирует отпуск носков со склада
    public ResponseEntity<Socks> outcomeSocks(@RequestParam String color,
                                              @RequestParam int cottonPart,
                                              @RequestParam int quantity) {
        Socks socks = socksService.findByColorAndCottonPart(color, cottonPart); // Ищем носки по цвету и процену хлопка
        int result = socks.getQuantity() - quantity; // Вычитаем кол-во носков
        socks.setQuantity(result); // Меняем значение кол-ва носков на складе
        Socks incomingSocks = socksService.changeSocks(socks); // перезаписываем значение в базе данных
        return ResponseEntity.ok(incomingSocks);
    }

    // поиск носков по цвету
    @GetMapping("get-by-color")
    public ResponseEntity<List<Socks>> findByColor(@RequestParam String color) {
        List<Socks> socks = socksService.findByColor(color);
        return ResponseEntity.ok(socks);
    }

    // поиск носков по проценту хлопка
    @GetMapping
    public ResponseEntity<List<Socks>> findByCottonPart(@RequestParam String color,
                                                        @RequestParam String operation, // здесь вписываем('>', '<', '=')
                                                        @RequestParam int cottonPartCompare) {
        // Если введен знак > , то ищем носки цвета: color, у которых процент хлопка больше чем cottonPartCompare
        if (operation.equals(">")) {
            List<Socks> socks = socksService.findByCottonPartGreaterTHan(color, cottonPartCompare);
            return ResponseEntity.ok(socks);
        }
        // Если введен знак < , то ищем носки цвета: color, у которых процент хлопка меньше чем cottonPartCompare
        if (operation.equals("<")) {
            List<Socks> socks = socksService.findByCottonPartLessThan(color, cottonPartCompare);
            return ResponseEntity.ok(socks);
        }
        // Если введен знак = , то ищем носки цвета: color, у которых процент хлопка равен cottonPartCompare
        if (operation.equals("=")) {
            List<Socks> socks = socksService.findByCottonPartEquals(color, cottonPartCompare);
            return ResponseEntity.ok(socks);
        }
        // Если в поле operation введен знак. отличающийся от выше перечисленных, то ошибка 404
        return ResponseEntity.notFound().build();
    }

}
