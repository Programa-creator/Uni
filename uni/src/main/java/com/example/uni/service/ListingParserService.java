package com.example.uni.service;

import com.example.uni.model.Listing;
import com.example.uni.repository.ListingRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListingParserService {
    private final ListingRepository listingRepository;

    public ListingParserService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    private static final String SOURCE_URL = "https://www.olx.ua/d/nedvizhimost/";

    @Scheduled(fixedRate = 3600000)
    public void parseListings() {
        try {
            Document doc = Jsoup.connect(SOURCE_URL).get();
            Elements elements = doc.select("div.css-l9drzq");
            List<Listing> listings = new ArrayList<>();
            for (Element el : elements) {
                String title = el.select("h4").text(); // Витягуємо заголовок
                String url = el.select("a").attr("href"); // Витягуємо посилання
                String cost = el.select("p").text();

                // Друкуємо в консоль, щоб перевірити
                System.out.println("Заголовок: " + title);
                System.out.println("Посилання: " + "https://www.olx.ua/"+url);
                System.out.println("Цена: " + cost);
            }
            for (Element el : elements) {
                String title = el.select("h4").text();
                String url = el.select("a").attr("href");

                // Змінено пошук ціни
                Element priceElement = el.select("p.css-6j1qjp").first();
                String priceText = priceElement != null ?
                        priceElement.text().replaceAll("[^0-9]", "") : "0";

                BigDecimal price = priceText.isEmpty() ?
                        BigDecimal.ZERO : new BigDecimal(priceText);

                Listing listing = new Listing();
                listing.setTitle(title);
                listing.setUrl("https://www.olx.ua/" + url);
                listing.setPrice(price);
                listing.setCurrency("UAH");

                listings.add(listing);
            }

            listingRepository.saveAll(listings);
            System.out.println("Парсинг завершено! Додано " + listings.size() + " оголошень.");

        } catch (IOException e) {
            System.err.println("Помилка парсингу: " + e.getMessage());
        }
    }
}
