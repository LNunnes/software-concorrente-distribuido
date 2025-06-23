package com.ufg.inf.inventoryService.config;

import com.ufg.inf.inventoryService.model.StockItem;
import com.ufg.inf.inventoryService.repository.StockItemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {

    private final StockItemRepository repository;

    public DataInitializer(StockItemRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        if (repository.count() == 0) {
            List<StockItem> items = Arrays.asList(
                new StockItem("Smartphone Galaxy S23", "Smartphone Samsung Galaxy S23 com 128GB, 8GB RAM, câmera de 50MP", new BigDecimal("2999.99"), "https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=400&h=300&fit=crop", "Eletrônicos", 15, new BigDecimal("4.5")),
                new StockItem("Notebook Dell Inspiron", "Notebook Dell Inspiron 15\" Intel i5, 8GB RAM, SSD 256GB", new BigDecimal("3499.99"), "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=400&h=300&fit=crop", "Eletrônicos", 8, new BigDecimal("4.2")),
                new StockItem("Smart TV LG 55\"", "Smart TV LG 55\" 4K UHD com WebOS e HDR", new BigDecimal("2499.99"), "https://images.unsplash.com/photo-1593359677879-a4bb92f829d1?w=400&h=300&fit=crop", "Eletrônicos", 12, new BigDecimal("4.6")),
                new StockItem("Tablet iPad Air", "Tablet Apple iPad Air 10.9\" com chip M1, 64GB", new BigDecimal("3999.99"), "https://images.unsplash.com/photo-1544244015-0df4b3ffc6b0?w=400&h=300&fit=crop", "Eletrônicos", 18, new BigDecimal("4.7")),
                new StockItem("Fone de Ouvido Sony WH-1000XM4", "Fone de ouvido sem fio com cancelamento de ruído ativo", new BigDecimal("1299.99"), "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400&h=300&fit=crop", "Acessórios", 25, new BigDecimal("4.8")),
                new StockItem("Mouse Gamer Logitech G502", "Mouse gamer com sensor óptico de alta precisão, 25.600 DPI", new BigDecimal("299.99"), "https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?w=400&h=300&fit=crop", "Acessórios", 30, new BigDecimal("4.4")),
                new StockItem("Teclado Mecânico Corsair K70", "Teclado mecânico com switches Cherry MX Red, RGB", new BigDecimal("599.99"), "https://images.unsplash.com/photo-1541140532154-b024d705b90a?w=400&h=300&fit=crop", "Acessórios", 20, new BigDecimal("4.6")),
                new StockItem("Webcam Logitech C920", "Webcam HD 1080p com microfone integrado", new BigDecimal("399.99"), "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400&h=300&fit=crop", "Acessórios", 35, new BigDecimal("4.3")),
                new StockItem("Câmera Canon EOS R", "Câmera mirrorless Canon EOS R com sensor full-frame", new BigDecimal("8999.99"), "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?w=400&h=300&fit=crop", "Fotografia", 5, new BigDecimal("4.9")),
                new StockItem("Lente Canon RF 24-70mm f/2.8", "Lente zoom profissional para Canon RF", new BigDecimal("3499.99"), "https://images.unsplash.com/photo-1606983340126-99ab4feaa64a?w=400&h=300&fit=crop", "Fotografia", 8, new BigDecimal("4.8")),
                new StockItem("Tripé Manfrotto MT055", "Tripé profissional com cabeça 3D", new BigDecimal("899.99"), "https://images.unsplash.com/photo-1578662996442-48f60103fc96?w=400&h=300&fit=crop", "Fotografia", 15, new BigDecimal("4.5")),
                new StockItem("Flash Canon Speedlite 600EX II-RT", "Flash sem fio com controle remoto", new BigDecimal("1299.99"), "https://images.unsplash.com/photo-1578662996442-48f60103fc96?w=400&h=300&fit=crop", "Fotografia", 12, new BigDecimal("4.7")),
                new StockItem("SSD Samsung 970 EVO Plus 1TB", "SSD NVMe de alta velocidade, 1TB", new BigDecimal("599.99"), "https://images.unsplash.com/photo-1597872200969-74b1bb21b35e?w=400&h=300&fit=crop", "Informática", 40, new BigDecimal("4.9")),
                new StockItem("Memória RAM Corsair Vengeance 16GB", "Kit de memória DDR4 3200MHz, 16GB (2x8GB)", new BigDecimal("299.99"), "https://images.unsplash.com/photo-1597872200969-74b1bb21b35e?w=400&h=300&fit=crop", "Informática", 50, new BigDecimal("4.6")),
                new StockItem("Placa de Vídeo RTX 4070", "Placa de vídeo NVIDIA RTX 4070 12GB GDDR6X", new BigDecimal("3999.99"), "https://images.unsplash.com/photo-1597872200969-74b1bb21b35e?w=400&h=300&fit=crop", "Informática", 10, new BigDecimal("4.8")),
                new StockItem("Fonte Corsair RM750x", "Fonte modular 750W 80 Plus Gold", new BigDecimal("699.99"), "https://images.unsplash.com/photo-1597872200969-74b1bb21b35e?w=400&h=300&fit=crop", "Informática", 25, new BigDecimal("4.7")),
                new StockItem("iPhone 15 Pro", "iPhone 15 Pro 128GB, chip A17 Pro, câmera tripla", new BigDecimal("6999.99"), "https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=400&h=300&fit=crop", "Smartphones", 20, new BigDecimal("4.9")),
                new StockItem("Xiaomi Redmi Note 13", "Smartphone com câmera de 108MP, 8GB RAM, 256GB", new BigDecimal("1499.99"), "https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=400&h=300&fit=crop", "Smartphones", 30, new BigDecimal("4.4")),
                new StockItem("Motorola Edge 40", "Smartphone com tela 6.55\", 8GB RAM, 256GB", new BigDecimal("1999.99"), "https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=400&h=300&fit=crop", "Smartphones", 18, new BigDecimal("4.5")),
                new StockItem("Console PlayStation 5", "Console PlayStation 5 com controle DualSense", new BigDecimal("3999.99"), "https://images.unsplash.com/photo-1606813907291-d86efa9b94db?w=400&h=300&fit=crop", "Gaming", 15, new BigDecimal("4.9")),
                new StockItem("Console Xbox Series X", "Console Xbox Series X 1TB", new BigDecimal("3799.99"), "https://images.unsplash.com/photo-1606813907291-d86efa9b94db?w=400&h=300&fit=crop", "Gaming", 12, new BigDecimal("4.8")),
                new StockItem("Nintendo Switch OLED", "Nintendo Switch com tela OLED de 7\"", new BigDecimal("2499.99"), "https://images.unsplash.com/photo-1606813907291-d86efa9b94db?w=400&h=300&fit=crop", "Gaming", 25, new BigDecimal("4.7")),
                new StockItem("Headset Gamer HyperX Cloud II", "Headset gamer com som surround 7.1", new BigDecimal("399.99"), "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400&h=300&fit=crop", "Gaming", 35, new BigDecimal("4.6"))
            );

            repository.saveAll(items);
            System.out.println("Produtos de exemplo inseridos no banco.");
        } else {
            System.out.println("Banco já populado.");
        }
    }
}
