-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 10 Kas 2023, 12:54:37
-- Sunucu sürümü: 8.0.31
-- PHP Sürümü: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `agent`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hotel`
--

CREATE TABLE `hotel` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `star` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `property` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hotel`
--

INSERT INTO `hotel` (`id`, `name`, `star`, `property`, `address`, `phone`, `email`) VALUES
(12, 'Side Star Elegance', '*****', 'Ücretsiz Otopark\nÜcretsiz WiFi\nYüzme Havuzu\nFitness Center\nHotel Concierge\nSPA\n7/24 Oda Servisi', 'Side, Süleyman Demirel Blv. No:3, 07330 Manavgat / Antalya', '(0242) 753 18 30', 'info@starelegance.com'),
(13, 'Barut B Suites', '****', 'Ücretsiz Otopark\nÜcretsiz WiFi\nYüzme Havuzu\n', 'Yeni Turizm Yolu Evrenseki Mahallesi 24.Sokak No:4 07604\nEvrenseki / Manavgat / Antalya', '(242) 763 00 03', 'info@barutbsuites.com'),
(14, 'Kappadoks Cave Hotel', '****', 'Ücretsiz Otopark\nÜcretsiz WiFi\nHotel Concierge\n7/24 Oda Servisi', 'Aşağı Mah. Gedik Sok. No:43\nUçhisar 50240 Kapadokya / Nevşehir', '(0384) 219 21 23', 'info@kappadoks.com'),
(15, 'Turan Otel', '****', 'Ücretsiz Otopark\nÜcretsiz WiFi\nHotel Concierge\nSPA\n7/24 Oda Servisi', 'Merkez, Camikebir Mahallesi 659. bulvarı, 81100 Düzce', '(0380) 514 27 81', 'info@turanotel.com'),
(17, 'Erdi Otel', '****', 'SPA', 'Bahçelievler İstanbul', '034562345', 'erdi@gmail.com'),
(18, 'New Gate Hotel', '****', 'Ücretsiz Otopark\nÜcretsiz WiFi', 'Eti, Gazi Mustafa Kemal Blv. No. 92, 06570 Çankaya / Ankara', '(530) 395 40 40', 'info@newgate.com'),
(19, 'Maxx Royal Belek Golf Resort', '*****', 'Ücretsiz Otopark\nÜcretsiz WiFi\nYüzme Havuzu\nFitness Center\nHotel Concierge\nSPA\n7/24 Oda Servisi', 'Belek / Antalya', '444 55 66', 'info@maxxroyal.com'),
(20, 'Yozgat Çamlık Otel', '****', 'Ücretsiz Otopark \nÜcretsiz Wifi', 'Develik, Çamlık Cd. No:45 D:101, 66100 Merkez / Yozgat', '(0354) 217 53 00', 'info@camlikotel.com');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `reservation_info`
--

CREATE TABLE `reservation_info` (
  `id` int NOT NULL,
  `client_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `client_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `client_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `client_note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `room_id` int NOT NULL,
  `check_in` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `check_out` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `adult_numb` int NOT NULL,
  `child_numb` int NOT NULL,
  `total_price` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `reservation_info`
--

INSERT INTO `reservation_info` (`id`, `client_name`, `client_phone`, `client_email`, `client_note`, `room_id`, `check_in`, `check_out`, `adult_numb`, `child_numb`, `total_price`) VALUES
(12, 'Erdi Salgın', '05469788023', 'erdisalgin@yandex.com', 'Oda temizleme istemiyor.', 15, '02/06/2024', '10/06/2024', 2, 1, 152000),
(13, 'Nihat Salgın', '05425208579', 'nsalgin@hotmail.com', 'yok', 16, '06/03/2024', '10/03/2024', 2, 1, 32000),
(14, 'Ekrem Taha Ünlü', '05078523452', 'ekremtahaunlu@gmail.com', 'oda temizleme hizmeti istemiyor.', 19, '02/06/2024', '12/06/2024', 1, 2, 50000),
(21, 'Caner Soner Şeker', '05348654237', 'canersonerseker@gmail.com', 'oda temizleme istemiyor.', 30, '02/07/2024', '06/07/2024', 1, 1, 6800);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `room`
--

CREATE TABLE `room` (
  `id` int NOT NULL,
  `room_type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `stock` int NOT NULL,
  `season_id` int NOT NULL,
  `adult_price` int NOT NULL,
  `child_price` int NOT NULL,
  `hotel_type_id` int NOT NULL,
  `hotel_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `room`
--

INSERT INTO `room` (`id`, `room_type`, `stock`, `season_id`, `adult_price`, `child_price`, `hotel_type_id`, `hotel_id`) VALUES
(15, 'Double', 97, 23, 7000, 5000, 25, 12),
(16, 'Double', 48, 25, 3000, 2000, 28, 13),
(17, 'Double', 19, 27, 4250, 2000, 29, 14),
(18, 'Double', 20, 24, 4500, 2000, 26, 12),
(19, 'Single', 24, 29, 3000, 1000, 32, 15),
(20, 'Single', 30, 25, 3500, 3000, 28, 13),
(21, 'Suit', 30, 24, 4000, 2500, 25, 12),
(22, 'Single', 40, 23, 4000, 3000, 26, 12),
(24, 'Single', 30, 33, 2000, 1000, 38, 17),
(25, 'Double', 39, 35, 2000, 1500, 40, 18),
(26, 'Single', 40, 36, 1600, 1200, 41, 18),
(27, 'Suit', 20, 26, 4000, 2000, 27, 13),
(28, 'Suit', 20, 37, 17000, 12000, 42, 19),
(29, 'Double', 49, 38, 25000, 17000, 43, 19),
(30, 'Single', 29, 39, 1200, 500, 44, 20);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `room_properties`
--

CREATE TABLE `room_properties` (
  `id` int NOT NULL,
  `property` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `room_id` int NOT NULL,
  `bed` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `area` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `room_properties`
--

INSERT INTO `room_properties` (`id`, `property`, `room_id`, `bed`, `area`) VALUES
(10, 'Televizyon ', 10, '2', 50),
(11, 'Televizyon ', 11, '3', 40),
(12, 'Televizyon ', 12, '3', 50),
(13, 'Televizyon ', 13, '2', 50),
(14, 'Televizyon ', 14, '56', 40),
(15, 'Televizyon \nMinibar \nKasa\nProjeksiyon', 15, '3', 40),
(16, 'Televizyon \nMinibar \nKasa\nProjeksiyon', 16, '3', 20),
(17, 'Televizyon \nMinibar ', 17, '3', 40),
(18, 'Televizyon \nMinibar \nKasa', 18, '3', 60),
(19, 'Televizyon \nMinibar ', 19, '2', 30),
(20, 'Televizyon \nMinibar ', 20, '3', 40),
(21, 'Televizyon \nMinibar ', 21, '3', 40),
(22, 'Televizyon \nMinibar ', 22, '3', 60),
(23, 'Televizyon \nMinibar ', 23, '2', 30),
(24, 'Televizyon \nMinibar \nProjeksiyon', 24, '2', 30),
(25, 'Televizyon \nMinibar ', 25, '2', 20),
(26, 'Televizyon \nMinibar ', 26, '2', 20),
(27, 'Televizyon \nMinibar ', 27, '3', 45),
(28, 'Televizyon \nMinibar \nOyun Konsolu\nKasa\nProjeksiyon', 28, '3', 30),
(29, 'Televizyon \nMinibar \nOyun Konsolu\nKasa\nProjeksiyon', 29, '4', 70),
(30, 'Televizyon ', 30, '2', 20);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `season`
--

CREATE TABLE `season` (
  `id` int NOT NULL,
  `season_start` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `season_end` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `season`
--

INSERT INTO `season` (`id`, `season_start`, `season_end`, `hotel_id`) VALUES
(13, '15/12/2022', '16/03/2023', 7),
(14, '17/03/2023', '14/12/2023', 7),
(15, '16/01/2022', '20/01/2022', 8),
(16, '21/01/2022', '25/01/2022', 8),
(17, '15/01/2022', '15/05/2022', 9),
(18, '16/05/2022', '15/10/2022', 9),
(19, '17/01/2022', '29/01/2022', 10),
(20, '12/03/2023', '12/10/2023', 10),
(21, '01/01//2022', '31/01/2022', 11),
(22, '01/02/2023', '31/03/2023', 11),
(23, '01/05/2024', '01/11/2024', 12),
(24, '02/11/2024', '31/04/2025', 12),
(25, '05/03/2024', '05/11/2024', 13),
(26, '06/11/2024', '04/03/2025', 13),
(27, '01/01/2024', '01/06/2024', 14),
(28, '02/06/2024', '31/12/2024', 14),
(29, '01/06/2024', '31/12/2024', 15),
(30, '01/01/2025', '31/05/2025', 15),
(31, '01/01/2024', '01/06/2024', 16),
(32, '01/07/2024', '01/12/2024', 16),
(33, '01/01/2024', '01/06/2024', 17),
(34, '02/06/2024', '31/12/2024', 17),
(35, '10/11/2023', '05/05/2024', 18),
(36, '06/05/2024', '09/11/2024', 18),
(37, '01/11/2023', '27/04/2024', 19),
(38, '28/04/2024', '30/10/2024', 19),
(39, '01/06/2024', '01/10/2024', 20),
(40, '02/10/2024', '31/05/2025', 20);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `type_hotel`
--

CREATE TABLE `type_hotel` (
  `id` int NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `type_hotel`
--

INSERT INTO `type_hotel` (`id`, `type`, `hotel_id`) VALUES
(25, 'Ultra Herşey Dahil', 12),
(26, 'Herşey Dahil', 12),
(27, 'Ultra Herşey Dahil', 13),
(28, 'Herşey Dahil', 13),
(29, 'Herşey Dahil', 14),
(30, 'Oda Kahvaltı', 14),
(31, 'Herşey Dahil', 15),
(32, 'Oda Kahvaltı', 15),
(33, 'Herşey Dahil', 16),
(34, 'Tam Pansiyon', 16),
(35, 'Yarım Pansiyon', 16),
(36, 'Ultra Herşey Dahil', 17),
(37, 'Herşey Dahil', 17),
(38, 'Oda Kahvaltı', 17),
(39, 'Tam Pansiyon', 17),
(40, 'Oda Kahvaltı', 18),
(41, 'Tam Pansiyon', 18),
(42, 'Ultra Herşey Dahil', 19),
(43, 'Herşey Dahil', 19),
(44, 'Oda Kahvaltı', 20),
(45, 'Tam Pansiyon', 20);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `uname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `pass` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `user`
--

INSERT INTO `user` (`id`, `name`, `uname`, `pass`, `type`) VALUES
(1, 'patika', 'patika', '123', 'admin'),
(2, 'Erdi Salgın', 'erdi', '123', 'employee'),
(6, 'Ali Yılmaz', 'ali', '123', 'Employee'),
(7, 'Ekrem Taha Ünlü', 'ekrem', '123', 'Admin');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `reservation_info`
--
ALTER TABLE `reservation_info`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `room_properties`
--
ALTER TABLE `room_properties`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `season`
--
ALTER TABLE `season`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `type_hotel`
--
ALTER TABLE `type_hotel`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Tablo için AUTO_INCREMENT değeri `reservation_info`
--
ALTER TABLE `reservation_info`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Tablo için AUTO_INCREMENT değeri `room`
--
ALTER TABLE `room`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Tablo için AUTO_INCREMENT değeri `room_properties`
--
ALTER TABLE `room_properties`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Tablo için AUTO_INCREMENT değeri `season`
--
ALTER TABLE `season`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- Tablo için AUTO_INCREMENT değeri `type_hotel`
--
ALTER TABLE `type_hotel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- Tablo için AUTO_INCREMENT değeri `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
