-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2020. Nov 18. 19:20
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
-- Tábla szerkezet ehhez a táblához `cars`
--

CREATE TABLE `cars` (
  `id` int(10) NOT NULL,
  `ownerid` int(20) NOT NULL,
  `plate_number` varchar(10) NOT NULL,
  `manufacturer` varchar(15) NOT NULL,
  `type` varchar(20) NOT NULL,
  `seats` int(3) NOT NULL,
  `color` varchar(20) NOT NULL,
  `year` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `cars`
--

INSERT INTO `cars` (`id`, `ownerid`, `plate_number`, `manufacturer`, `type`, `seats`, `color`, `year`) VALUES
(1, 1, 'XXX-420', 'apád', 'anyád', 5, 'szar', 2000),
(2, 1, 'XXX-420', 'apád', 'anyád', 5, 'szar', 2000),
(3, 1, 'WTF-666', 'opel', 'astra', 6, 'rózsaszín', 2009),
(4, 1, 'dsffds', 'dfgerge', 'rthrthrthrt', 4, 'wggrge', 1990),
(5, 3, 'sdfllsjd', 'lófasz', 'kecske', 8, 'szarszinű', 1997);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ownerid` (`ownerid`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `cars`
--
ALTER TABLE `cars`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `cars`
--
ALTER TABLE `cars`
  ADD CONSTRAINT `cars_ibfk_1` FOREIGN KEY (`ownerid`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
