-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2020. Nov 18. 19:21
-- Kiszolgáló verziója: 10.4.11-MariaDB
-- PHP verzió: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `telekocsi`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `rides`
--

CREATE TABLE `rides` (
  `id` int(10) NOT NULL,
  `carid` int(10) NOT NULL,
  `departure` varchar(30) NOT NULL,
  `arrival` varchar(30) NOT NULL,
  `departuretime` datetime NOT NULL,
  `arrivaltime` datetime NOT NULL,
  `price` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `rides`
--

INSERT INTO `rides` (`id`, `carid`, `departure`, `arrival`, `departuretime`, `arrivaltime`, `price`) VALUES
(1, 1, 'tucsok', 'anyád', '2020-10-10 10:10:00', '2020-10-10 10:10:00', 20),
(2, 1, 'tucsok', 'anyád', '2020-10-10 10:10:00', '2020-10-10 10:10:00', 20),
(3, 1, 'sdf', 'sdf', '2020-10-10 10:10:00', '2020-10-10 10:10:00', 10),
(4, 3, 'dfgsdfgsd', 'dfbgvdfgb', '2020-10-11 12:12:00', '2020-10-11 12:13:00', 1000);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `rides`
--
ALTER TABLE `rides`
  ADD PRIMARY KEY (`id`),
  ADD KEY `carid` (`carid`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `rides`
--
ALTER TABLE `rides`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `rides`
--
ALTER TABLE `rides`
  ADD CONSTRAINT `rides_ibfk_1` FOREIGN KEY (`carid`) REFERENCES `cars` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
